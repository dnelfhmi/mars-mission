/**
 * Custom exception class for unknown entity in file content.
 * @param message  The error message
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

public class UnknownEntityException extends Exception{

    //constructor

    /**
     * Custom exception creation intended for file that has unknown entity in its content
     * @param message custom message
     */
    public UnknownEntityException(String message) {
        super(message);
    }
}
