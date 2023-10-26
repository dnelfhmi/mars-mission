/**
 * Custom exception class for invalid move attempts.
 * @param message  The error message
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

public class InvalidMoveException extends Exception{

    //constructor
    public InvalidMoveException(String message) {
        super(message);
    }
}
