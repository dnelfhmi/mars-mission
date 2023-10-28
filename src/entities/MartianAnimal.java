/**
 * Abstract class representing Martian animal entities.
 * Extends the Entity class. Direct subclasses are Heebie and Jeebie.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class MartianAnimal extends Entity {

    //constructor
    /**
     * Constructor of MartianAnimal but since it is abstract, cant create new object of MartianAnimal type
     * However, subclass can do reference variable of MartianAnimal type
     * Specify super for subclasses
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public MartianAnimal(int x, int y) {
        super(x, y);
    }

    /**
     * Constructor of Martian animal but since it is abstract, cant create new object of Martian animal type
     * However, subclass can do reference variable of Martian animal type
     * Specify super for subclasses
     * @param name Martian animal species string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public MartianAnimal(String name, int x, int y) {
        super(name, x, y);
    }
}
