/**
 * Abstract class representing Earth animal entities.
 * Extends the Entity class. Direct subclasses are Cattle and Dog.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

public abstract class EarthAnimal extends Entity {

    //constructor
    public EarthAnimal(int x, int y) {
        super(x, y);
    }

    public EarthAnimal(String name, int x, int y) {
        super(name, x, y);
    }
}
