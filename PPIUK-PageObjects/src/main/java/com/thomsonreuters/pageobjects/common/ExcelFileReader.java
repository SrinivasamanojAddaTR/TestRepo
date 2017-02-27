package com.thomsonreuters.pageobjects.common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

public final class ExcelFileReader {

    private final static Logger LOG = LoggerFactory.getLogger(ExcelFileReader.class);
    private static final String IOEXCEPTION = "IOException while getting value from excel ";
    private final static String COBALT_USERS_XLSX = System.getProperty("cobalt.users.xls.path");
    private static final int USERS_EMAIL_COLUMN_NUMBER = 6;
    private static final int USERS_PASSWORD_COLUMN_NUMBER = 1;
    private static final int EMAILS_PASSWORD_COLUMN_NUMBER = 1;

    private static final String USERS_SHEET_NAME = "Users";
    private static final String EMAILS_SHEET_NAME = "Emails";
    protected static Map<String, Row> excelPasswords;

    private static RandomAccessFile raf = null;

    private static FileLock acquireLock() throws IOException, InterruptedException {
        FileLock fileLock;
        int counter = 0;
        raf = new RandomAccessFile(COBALT_USERS_XLSX, "rw");
        FileChannel fileChannel = raf.getChannel();
        do {
            fileLock = fileChannel.tryLock();
            counter++;
            Thread.sleep(1000);
        }
        while (null != fileLock && counter > 10);
        return fileLock;
    }

    private static void releaseLock(FileLock fileLock) throws IOException {
        if (null != fileLock) {
            fileLock.release();
        }
    }

    public static String getCobaltEmail(String username) {
        loadDataIntoMAP();
        try{
            return excelPasswords.get(username).getCell(USERS_EMAIL_COLUMN_NUMBER).getStringCellValue();
        }catch(Exception e){
            return "";
        }
        //return getDataFromCobaltUSers(USERS_SHEET_NAME, username, USERS_EMAIL_COLUMN_NUMBER);
    }

    public static String getCobaltPassword(String username) {
        loadDataIntoMAP();
        try{
            return excelPasswords.get(username).getCell(USERS_PASSWORD_COLUMN_NUMBER).getStringCellValue();
        }catch(Exception e){
            LOG.warn("No password has found in excel sheet for the given name:"+username +e.getMessage());
            return "";
        }
        //return getDataFromCobaltUSers(USERS_SHEET_NAME, username, USERS_PASSWORD_COLUMN_NUMBER);
    }

    private static void loadDataIntoMAP() {
        for (int i = 0; i < 5; i++) {
            try {
                if (excelPasswords == null || excelPasswords.size() == 0) {
                    //LOG.info("Calling Excel File Reader to read the password from excel file.");
                    excelPasswords = getDataFromExcel();
                }
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static synchronized Map<String, Row> getDataFromExcel() {
        //LOG.info("Reading passwords into map as key value pairs");
        Map<String, Row> passwords = new HashMap<String, Row>();
        String dataFromCell = null;
        try {
            FileInputStream file = new FileInputStream(COBALT_USERS_XLSX);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(USERS_SHEET_NAME);
            int rowsCount = sheet.getLastRowNum();
            for (int i = 1; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                passwords.put(row.getCell(0).getStringCellValue(), row);
            }
        } catch (IOException e) {
            LOG.error(IOEXCEPTION, e);
            passwords = new HashMap<String, Row>();
        }
        return passwords;
    }

    public static String getDefaultUser() /*throws IOException, InterruptedException*/ {
        return "topsecret1234";
    }

    public static void unlockUser(String username) /*throws IOException, InterruptedException*/ {
        LOG.debug("Unlock user code commented out");
    }


    public static String getEmailPassword(String email) {
        return getDataFromCobaltUSers(EMAILS_SHEET_NAME, email, EMAILS_PASSWORD_COLUMN_NUMBER);
    }

    private static String getDataFromCobaltUSers(String sheetName, String firstColumnValue, int columnNumber) {
        for (int i = 0; i < 5; i++) {
            try {
                return getData(sheetName, firstColumnValue, columnNumber);
            } catch (Exception e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return "";
    }

    private static synchronized String getData(String sheetName, String firstColumnValue, int columnNumber) {
        String dataFromCell = null;
        try {
            FileInputStream file = new FileInputStream(COBALT_USERS_XLSX);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowsCount = sheet.getLastRowNum();
            for (int i = 0; i <= rowsCount; i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(firstColumnValue)) {
                    dataFromCell = row.getCell(columnNumber).toString();
                    return dataFromCell;
                }
            }
        } catch (IOException e) {
            LOG.error(IOEXCEPTION, e);
        }
        return dataFromCell;

    }
}