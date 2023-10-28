/**
* Abstract class representing vegetation entities.
* Extends the Entity class. Direct subclasses are PlantType and VegetableType.
* @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
*/

package entities;

public abstract class Vegetation extends Entity{

    //instance_fields
    private int NUTRITION_VALUE = 2;
    private int nutrition;

    //constructor
    /**
     * Constructor of Vegetation but since it is abstract, cant create new object of Vegetation type
     * However, subclass can do reference variable of Vegetation type
     * Specify super for subclasses
     * @param name Vegetation type name string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Vegetation(String name, int x, int y) {
        super(name,x,y);
        this.nutrition = NUTRITION_VALUE;
    }

    //other methods
    /**
     * Print entity name and its coordinate in habitat
     * @return Vegetation information in string
     */
    @Override
    public String toString() {
        return name+" is located at ["+getX()+", "+getY()+"]";
    }

    /**
     * Provide nutritional value of cattle
     * @return nutrition value
     */
    public int getNutrition(){
        return nutrition;
    }

    /**
     * Abstract method for Vegetation type interaction with other entity
     * @param entity entity interacted with
     */
    public abstract void interact(Entity entity);
}
