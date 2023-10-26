/**
 * FileHandler class to handle loading and saving map files.
 * Constructors:
 * - Default to load default map file
 * - Accept filename to load specific file
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    //instance fields
    private static final String DEFAULT_MAP_FILE = "resources/default.in";
    //setting set of a valid symbol in the maps.
    private Set<Character> validSymbols = new HashSet<>(Arrays.asList('#','.','Z','X','H','J','P','T','R','L','O','A','B','E','C','G','S','D','@','*'));
    private List<String> dataLines = new ArrayList<>();
    private File file;

    //constructor
    public FileHandler(){
        this.file = new File(DEFAULT_MAP_FILE);
    }

    public FileHandler(String fileName){
        this.file = new File(fileName);
    }

    //getter
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
