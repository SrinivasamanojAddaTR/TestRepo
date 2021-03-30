package com.thomsonreuters.pageobjects.common;

import com.thomsonreuters.pageobjects.exceptions.ExcelException;
import com.thomsonreuters.utils.TimeoutUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public final class ExcelFileReader {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelFileReader.class);
    private static final String IOEXCEPTION = "IOException while getting value from excel ";
    private static final String COBALT_USERS_XLSX = System.getProperty("cobalt.users.xls.path");
    private static final int USERS_EMAIL_COLUMN_NUMBER = 6;

    private static final int USERS_NAME_COLUMN_NUMBER = 0;
    private static final int USERS_PASSWORD_COLUMN_NUMBER = 1;
    private static final int EMAILS_PASSWORD_COLUMN_NUMBER = 1;
    private static final int TIMEOUT_SECONDS_TO_READ_EXCEL = 2;

    private static final String USERS_SHEET_NAME = "Users";
    private static final String EMAILS_SHEET_NAME = "Emails";

    private static List<Row> excelPasswords;

    public enum AdditionalUserColumns {

        ADDITIONAL_USER("useAdditionalUser", 7, 8),
        PROD_USER("useProdUser", 9, 10);

        private String systemPropertyName;
        private int userNameColumnNumber;
        private int userEmailColumnNumber;

        AdditionalUserColumns(String systemPropertyName, int userNameColumnNumber, int userEmailColumnNumber) {
            this.systemPropertyName = systemPropertyName;
            this.userNameColumnNumber = userNameColumnNumber;
            this.userEmailColumnNumber = userEmailColumnNumber;
        }

        public static boolean isUseAdditionalUserEnabled() {
            return getEnabledInCli().
                    map(item -> BooleanUtils.toBoolean(System.getProperty(item.systemPropertyName))).
                    orElse(false);
        }

        public static int getAdditionalUserNameColumnNumber() {
            return getEnabledInCli().map(AdditionalUserColumns::getUserNameColumnNumber).
                    orElse(USERS_NAME_COLUMN_NUMBER);
        }

        public static int getAdditionalUserEmailColumnNumber() {
            return getEnabledInCli().map(AdditionalUserColumns::getUserEmailColumnNumber).
                    orElse(USERS_EMAIL_COLUMN_NUMBER);
        }

        private static Optional<AdditionalUserColumns> getEnabledInCli() {
            return Arrays.stream(values()).
                    filter(item -> StringUtils.isNotEmpty(System.getProperty(item.systemPropertyName))).
                    findFirst();
        }

        public int getUserEmailColumnNumber() {
            return userEmailColumnNumber;
        }

        public int getUserNameColumnNumber() {
            return userNameColumnNumber;
        }
    }

    public static String getCobaltEmail(String username) {
        loadDataIntoMAP();
        try {
            String defaultValue = getRowByUsername(username).getCell(USERS_EMAIL_COLUMN_NUMBER).getStringCellValue().trim();
            return AdditionalUserColumns.isUseAdditionalUserEnabled() ? StringUtils.defaultIfBlank(
                    getCellValue(getRowByUsername(username), AdditionalUserColumns.getAdditionalUserEmailColumnNumber()), defaultValue).trim()
                    : defaultValue;
        } catch (Exception e) {
            return EMPTY;
        }
    }

    public static String getCobaltPassword(String username) {
        loadDataIntoMAP();
        try {
            return getRowByUsername(username).getCell(USERS_PASSWORD_COLUMN_NUMBER).getStringCellValue();
        } catch (Exception e) {
            LOG.warn(String.format("No password has found in excel sheet for the given name:%s . Error: %s", username,
                    e.getMessage()));
            return EMPTY;
        }
    }

    private static void loadDataIntoMAP() {
        for (int i = 0; i < 2; i++) {
            try {
                if (excelPasswords == null || excelPasswords.isEmpty()) {
                    excelPasswords = getDataFromExcel();
                }
                break;
            } catch (Exception e) {
                LOG.error("Error reading data from excel", e);
                TimeoutUtils.sleepInSeconds(TIMEOUT_SECONDS_TO_READ_EXCEL);
            }
        }
    }

    public static synchronized List<Row> getDataFromExcel(String sheetName, InputStream inputStream) {
        List<Row> passwords = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowsCount = sheet.getLastRowNum();
            for (int i = 1; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                passwords.add(row);
            }
        } catch (IOException e) {
            LOG.error(IOEXCEPTION, e);
            passwords = new ArrayList<>();
        }
        return passwords;
    }

    public static synchronized List<Row> getDataFromExcel() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FilenameUtils.getName(COBALT_USERS_XLSX));
        return getDataFromExcel(USERS_SHEET_NAME, inputStream);
    }

    private static final String DEFAULT_USER = "topsecret1234";

    public static String getDefaultUser() {
        return DEFAULT_USER;
    }

    public static String getEmailPassword(String email) {
        return getDataFromCobaltUsers(EMAILS_SHEET_NAME, email, EMAILS_PASSWORD_COLUMN_NUMBER);
    }

    public static String getAdditionalUserName(String userName) {
        return AdditionalUserColumns.isUseAdditionalUserEnabled()
                ? getDataFromCobaltUsers(USERS_SHEET_NAME, userName, AdditionalUserColumns.getAdditionalUserNameColumnNumber()) : EMPTY;
    }

    private static String getDataFromCobaltUsers(String sheetName, String firstColumnValue, int columnNumber) {
        for (int i = 0; i < 2; i++) {
            try {
                return getData(sheetName, firstColumnValue, columnNumber);
            } catch (Exception e) {
                LOG.error("Error reading data from excel", e);
                TimeoutUtils.sleepInSeconds(TIMEOUT_SECONDS_TO_READ_EXCEL);
            }
        }
        return EMPTY;
    }

    private static synchronized String getData(String sheetName, String firstColumnValue, int columnNumber) {
        String dataFromCell = null;
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FilenameUtils.getName(COBALT_USERS_XLSX));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowsCount = sheet.getLastRowNum();
            for (int i = 0; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(firstColumnValue)
                        || (AdditionalUserColumns.isUseAdditionalUserEnabled() && getCellValue(row, AdditionalUserColumns.getAdditionalUserNameColumnNumber())
                        .equalsIgnoreCase(firstColumnValue))) {
                    dataFromCell = Optional.ofNullable(row.getCell(columnNumber)).map(Object::toString).orElse(EMPTY);
                    return dataFromCell;
                }
            }
        } catch (IOException e) {
            LOG.error(IOEXCEPTION, e);
        }
        return dataFromCell;

    }

    private static Row getRowByUsername(String userName) {
        for (Row row : excelPasswords) {
            if (row.getCell(0).getStringCellValue().equalsIgnoreCase(userName) || (AdditionalUserColumns.isUseAdditionalUserEnabled()
                    && getCellValue(row, AdditionalUserColumns.getAdditionalUserNameColumnNumber()).equalsIgnoreCase(userName))) {
                return row;
            }
        }
        throw new ExcelException(String.format("No row in users excel for user '%s'", userName));
    }

    public static String getCellValue(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum);
        Optional.ofNullable(cell).ifPresent(item -> item.setCellType(CELL_TYPE_STRING));
        return Optional.ofNullable(cell).map(Cell::getStringCellValue).orElse(EMPTY);
    }
}