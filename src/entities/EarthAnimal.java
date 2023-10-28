/**
 * Abstract class representing Earth animal entities.
 * Extends the Entity class. Direct subclasses are Cattle and Dog.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class EarthAnimal extends Entity {

    //constructor

    /**
     * Create Earth Animal object with position x and y in habitat
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public EarthAnimal(int x, int y) {
        super(x, y);
    }

    /**
     * Overloading constructor
     * Create Earth Animal object with name,position x and y in habitat
     * @param name animal type name
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public EarthAnimal(String name, int x, int y) {
        super(name, x, y);
    }
}
