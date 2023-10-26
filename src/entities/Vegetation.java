/**
* Abstract class representing vegetation entities.
* Extends the Entity class. Direct subclasses are PlantType and VegetableType.
* @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
*/

package entities;

public abstract class Vegetation extends Entity{

    //constructor
    public Vegetation(String name, int x, int y) {
        super(name,x,y);
    }

    //other methods
    @Override
    public String toString() {
        return name+" is located at ["+getX()+", "+getY()+"]";
    }
    public abstract void interact(Entity entity);
}
