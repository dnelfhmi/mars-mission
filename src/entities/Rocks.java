/**
 * Abstract class representing rock entities.
 * Extends the Entity class. Direct subclasses are Mineral and PlainRock.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class Rocks extends Entity{

    //constructor

    /**
     * Constructor of entity but since it is abstract, cant create new object of Rocks type
     * However, subclass can do reference variable of Rocks type
     * Specify super for subclasses
     * @param name rocks type name
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Rocks(String name, int x, int y) {
        super(name,x,y);
    }
}
