/**
 * Represents plain rock entities in the game.
 * Extends Rocks class.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public class PlainRock extends Rocks{

    //constructor
    /**
     * Create PlainRock object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PlainRock(String name, int x, int y) {
        super(name,x,y);
    }

    //other methods
    /**
     * Print PlainRock name and its coordinate in habitat
     * @return PlainRock information in string
     */
    @Override
    public String toString() {
        return "PlainRock at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve PlainRock char representation
     * @return PlainRock char representation
     */
    @Override
    public char getSymbol() {
        return '@';
    }
}
