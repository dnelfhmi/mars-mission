/**
 * FileHandler class to handle loading and saving map files.
 * Constructors:
 * - Default to load default map file
 * - Accept filename to load specific file
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import java.io.*;
import java.util.*;

public class FileHandler {

    //instance fields
    private static final String DEFAULT_MAP_FILE = "resources/default.in";
    private static final String DEFAULT_LOG_FILE = "resources/habitability.log";
    //setting set of a valid symbol in the maps.
    private Set<Character> validSymbols = new HashSet<>(Arrays.asList('#','.','Z','X','H','J','P','T','R','L','O','A','B','E','C','G','S','D','@','*'));
    private List<String> dataLines = new ArrayList<>();
    private List<String> logDataLines = new ArrayList<>();
    private File file;
    private File logFile;

    //constructor
    /**
     * Create FileHandler object with default map file set as filepath
     */
    public FileHandler(){
        this.file = new File(DEFAULT_MAP_FILE);
        this.logFile = new File(DEFAULT_LOG_FILE);
    }

    /**
     * Create FileHandler object with given filepath in fileName string argument
     * @param fileName target filepath string array
     */
    public FileHandler(String[] fileName){
        int argsLength = 2;
        int mapFileArgs = 1;
        int logFileArgs = 3;
        this.file = new File(fileName[mapFileArgs]);
        if (fileName.length > argsLength){
            this.logFile = new File(fileName[logFileArgs]);
        } else {
            this.logFile = new File(DEFAULT_LOG_FILE);
        }
    }

    /**
     * Create FileHandler object with given filepath in fileName string
     * @param fileName target filepath
     */
    public FileHandler(String fileName){
        this.file = new File(fileName);
        this.logFile = new File(DEFAULT_LOG_FILE);
    }

    //getter

    /**
     * Retrieve list of string where each string represent each row of a file content
     * @return list of string in the file
     */
    public List<String> getDataLines() {
        return dataLines;
    }

    //loading file method
    /**
     * Function primarily to load file initialized by constructor.
     * Go line by line amd add each line as string in dataLines array list.
     * @return dataLines array list
     * @throws FileNotFoundException If no file existed/found
     * @throws InvalidFileException If invalid map formatting in file content
     * @throws UnknownEntityException If unknown entity present in file content
     */
    public List<String> loadFile() throws FileNotFoundException, InvalidFileException, UnknownEntityException {
        int lineLength = -1;
        try {
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                validateLine(line, lineLength);
                lineLength = line.length();
                dataLines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File Not Found, aborting mission.");
        } catch (InvalidFileException e) {
            throw new InvalidFileException("Invalid file content, Aborting mission.");
        } catch (UnknownEntityException e) {
            throw new UnknownEntityException("An unknown item found in Martian land. Aborting mission.");
        }
        return dataLines;
    }

    //saving file method
    /**
     * Saves the given list of data lines to a file.
     *
     * @param data The list of strings to save
     * @param filename The file path to save to
     *
     * It creates a new File object with the given filename.
     * If the file doesn't exist yet, it creates a new empty file.
     * It then opens a FileWriter on that file which will overwrite existing contents.
     * It loops through each line in the data list and writes it to the file
     * with a newline character.
     */
    public void saveFile(List<String> data, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
        }
    }

    //appending and reading log file method
    /**
     * Append the habitability log with the current habitability status
     * @param meter habitability meter
     */
    public void appendToHabitabilityLog(HabitabilityMeter meter, int currentRun) throws IOException {
        Map<String,Integer> entityCount = meter.getEntityCount();
        if (!logFile.exists()) {
            if (!logFile.createNewFile()) {
                throw new IOException("Cannot create file for Habitability Status Log.");
            }
        }
        try (FileWriter writer = new FileWriter(logFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write("Program Run :" + currentRun);
            bufferedWriter.newLine();
            bufferedWriter.write("Habitability Status");
            bufferedWriter.newLine();
            bufferedWriter.write("======================");
            bufferedWriter.newLine();

            for (Map.Entry<String, Integer> entry : entityCount.entrySet()) {
                bufferedWriter.write(entry.getKey() + " = " + entry.getValue());
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Total Habitability Score: " + meter.getHabitabilityScore());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new IOException("Cannot write Habitability Status Log in a file.", e);
        }
    }

    /**
     * Method to determine the current run number for log file
     * @return current run number
     */
    public int getCurrentRunNumberLog() {
        if (!logFile.exists()) {
            return 1;
        }
        int runNumber = 0;
        try (Scanner logScan = new Scanner(logFile)) {
            while (logScan.hasNextLine()) {
                String line = logScan.nextLine();
                if (line.startsWith("Program Run :")) {
                    runNumber++;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read Habitability Status Log file.");
        }
        return runNumber + 1; // return next run number
    }

    /**
     * Method for loading log file and return it as list of string
     * @return log file list of string
     */
    protected List<String> loadLogFile(){
        try{
            if (!logFile.exists()) {
                if (!logFile.createNewFile()) {
                    throw new IOException("Cannot create file for Habitability Status Log. ");
                }
            }
            Scanner fileScanner = new Scanner(logFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                logDataLines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file for Habitability Status Log.");
        }
        return logDataLines;
    }

    /**
     * Method for printing log file
     */
    protected void displayLogFile() {
        List<String> logDataLines = loadLogFile();
        for (String line : logDataLines) {
            System.out.println(line);
        }
        System.out.println("");
    }

    //validating file method
    /**
     * validate a single line of the loaded file.
     * @param line
     * @param lineLength
     * @throws InvalidFileException If invalid map formatting in file content
     * @throws UnknownEntityException If unknown entity present in file content
     */
    private void validateLine(String line, int lineLength) throws InvalidFileException, UnknownEntityException {
        if (lineLength != -1 && line.length() != lineLength) {
            throw new InvalidFileException("Invalid file content, Aborting mission.");
        }
        if (!line.startsWith("#") || !line.endsWith("#")){
            throw new InvalidFileException("Invalid file content, Aborting mission.");
        }
        for (char symbol: line.toCharArray()){
            if(!isValidSymbol(symbol)){
                throw new UnknownEntityException("An unknown item found in Martian land. Aborting mission." +
                        "The unknown symbol detected: " + symbol);
            }
        }
    }

    /**
     * Check if a character is valid or not
     * @param symbol character
     * @return true if symbol is allowed/valid, false otherwise
     */
    private boolean isValidSymbol(char symbol){
        return validSymbols.contains(symbol);
    }
}