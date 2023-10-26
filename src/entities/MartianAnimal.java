/**
 * Abstract class representing Martian animal entities.
 * Extends the Entity class. Direct subclasses are Heebie and Jeebie.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class MartianAnimal extends Entity {

    //constructor
    public MartianAnimal(int x, int y) {
        super(x, y);
    }

    public MartianAnimal(String name, int x, int y) {
        super(name, x, y);
    }
}
