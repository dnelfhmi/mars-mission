/**
 * Represents cattle entities in the game.
 * Extends EarthAnimal class.
 *
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public class Cattle extends EarthAnimal {

    //constructor
    public Cattle(String name, int x, int y) {
        super(name, x, y);
    }

    public Cattle(int x, int y) {
        super(x, y);
    }

    //other method
    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }

    public char getSymbol(){
        int firstChar = 0;
        return name.charAt(firstChar);
    }
}

