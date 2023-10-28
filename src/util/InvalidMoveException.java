/**
 * Custom exception class for invalid move attempts.
 * @param message  The error message
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

public class InvalidMoveException extends Exception{

    //constructor

    /**
     * Custom exception creation intended for invalid move attempted by entity
     * @param message custom message
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}
