/**
 * Represents mineral entities in the game.
 * Extends Rocks class.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public class Mineral extends Rocks{

    //constructor
    /**
     * Create Mineral object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Mineral(String name, int x, int y) {
        super(name,x,y);
    }

    //other methods
    /**
     * Print Mineral name and its coordinate in habitat
     * @return Mineral information in string
     */
    @Override
    public String toString() {
        return "Mineral at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve Mineral char representation
     * @return Mineral char representation
     */
    @Override
    public char getSymbol() {
        return '*';
    }
}
