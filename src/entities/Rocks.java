/**
 * Abstract class representing rock entities.
 * Extends the Entity class. Direct subclasses are Mineral and PlainRock.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class Rocks extends Entity{

    //constructor
    public Rocks(String name, int x, int y) {
        super(name,x,y);
    }
}
