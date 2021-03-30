package com.thomsonreuters.pageobjects.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class FileActions {

    protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(FileActions.class);

    public File findFile(String fileName, String folderPath) throws IOException {
        File dir = new File(folderPath);
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, null);
        for (File file : files) {
            if (file.getName().contains(fileName)) {
                return file;
            }
        }
        return null;
    }

    public File findFile(String fileName, String extension, String folderPath) throws IOException {
        File dir = new File(folderPath);
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, null);
        for (File file : files) {
            if (file.getName().contains(fileName) && file.getName().contains(extension)) {
                return file;
            }
        }
        return null;
    }

    public void createTestFile(String path) {
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch (IOException e) {
            LOG.info("Exception creating file", e.getMessage());
        }
    }

    public void deleteFile(File fileToDelete) {
        if (fileToDelete != null && fileToDelete.exists()) {
            fileToDelete.delete();
        } else {
            LOG.info("Specified file does not exist");
        }
    }

    /**
     * Check if given file exists. If so - append file name to make it unique and return result file.
     *
     * @param inputFile   Input file to be checked
     * @param incrementBy Number which will be added to file name to make it unique.
     *                    Example: file with name "someFile.txt" was given as inputFile.
     *                    If it exists and incrementBy == 1 then result file will be with name "someFile_1.txt".
     *                    If it will be exists too, then result file will be with name "someFile_2.txt" and so on
     *                    until free number will not be found (starts with incrementBy value) to make file name non-exists
     * @return Non-exists file for necessary operations
     */
    public File incrementFileNameIfExists(File inputFile, int incrementBy) {
        /** We need some work to do if file exists */
        if (inputFile.exists()) {
            String inputFileName = inputFile.getName();
            /** Just pre-build our result file name */
            String resultFileName;
            /** If our file has extension then appendix should inserted between file name without extension and extension */
            if (inputFileName.contains(".")) {
                /** Reverse file name and replace first dot occurrence with necessary appendix.
                 This operation is needed if file contains few dots in name. */
                String reversedResultFileName = new StringBuilder(inputFileName).reverse().toString().replaceFirst("\\.", "." + incrementBy + "_");
                resultFileName = new StringBuilder(reversedResultFileName).reverse().toString();
            } else {
                /** Just add appendix to our result file name if it has no extension */
                resultFileName = inputFileName + "_" + incrementBy;
            }
            /** Build file with parent directory of input file and result file name */
            File resultFile = new File(inputFile.getParentFile().getAbsolutePath(), resultFileName);
            /** If result file is exists - call method again but increase appendix by 1 */
            if (resultFile.exists()) {
                return incrementFileNameIfExists(inputFile, ++incrementBy);
            } else {
                /** if not exists - return result file */
                return resultFile;
            }
        } else {
            return inputFile;
        }
    }

    /**
     * Check if given file exists. If so - append file name to make it unique and return result file.
     * Example: file with name "someFile.txt" was given as inputFile.
     * If it exists then result file will be with name "someFile_1.txt".
     * If it will be exists too, then result file will be with name "someFile_2.txt" and so on
     * until free number will not be found (starts with incrementBy value) to make file name non-exists
     *
     * @param inputFile Input file to be checked
     * @return Non-exists file for necessary operations
     */
    public File incrementFileNameIfExists(File inputFile) {
        return incrementFileNameIfExists(inputFile, 1);
    }

    /**
     * Get text content of the file
     *
     * @param file Text file
     * @return Text content of the given file
     */
    public String getTextFromFile(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            LOG.info("Unable to read file: ", e);
            return "";
        }
    }

}
