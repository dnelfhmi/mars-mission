/**
 * Represents mineral entities in the game.
 * Extends Rocks class.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public class Mineral extends Rocks{

    //constructor
    public Mineral(String name, int x, int y) {
        super(name,x,y);
    }

    //other methods
    @Override
    public String toString() {
        return "Mineral at position (" + getY() + ", " + getX() + ")";
    }

    @Override
    public char getSymbol() {
        return '*';
    }
}
