/**
 * Represents cattle entities in the game.
 * Extends EarthAnimal class.
 *
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public class Cattle extends EarthAnimal {

    //instance fields
    private final int NUTRITION_VALUE = 2;
    private final int nutrition;

    //constructor
    /**
     * Create Cattle object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Cattle(String name, int x, int y) {
        super(name, x, y);
        this.nutrition = NUTRITION_VALUE;
    }

    //other method
    /**
     * Print Cattle name and its coordinate in habitat
     * @return Cattle information in string
     */
    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve Cattle char representation
     * @return Cattle char representation
     */
    public char getSymbol(){
        int firstChar = 0;
        return name.charAt(firstChar);
    }

    /**
     * Provide nutritional value of cattle
     * @return nutrition value
     */
    public int getNutrition(){
        return nutrition;
    }
}

