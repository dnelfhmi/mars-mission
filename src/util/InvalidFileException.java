/**
 * Custom exception class for invalid file such as non-uniform length of rows in file content.
 * @param message  The error message
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

public class InvalidFileException extends Exception{

    //constructor

    /**
     * Custom exception creation intended for invalid file formatting
     * @param message custom message
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
