/**
 * Abstract base class for all entities in the Mars.
 * Direct subclasses are Vegetation, Rocks, Earth Animals and Martian Animals.
 *
 *
 * Has two constructors:
 * - One to set name, x, and y
 * - One to just set x and y
 *
 * Also has:
 * - getName() and setName() methods
 * - Abstract toString() and getSymbol() methods that subclasses must implement
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */


package entities;

public abstract class Entity {
    protected String name;
    protected int x;
    protected int y;

    /**
     * Constructor of entity but since it is abstract, cant create new object of Entity type
     * However, subclass can do reference variable of Entity type
     * Specify super for subclasses
     * @param name entity type name
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Entity(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of entity but since it is abstract, cant create new object of Entity type
     * However, subclass can do reference variable of Entity type
     * Specify super for subclasses
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieve x-coordinate of entity
     * @return x-coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Retrieve y-coordinate of entity
     * @return y-coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * Set x-coordinate of entity
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y-coordinate of entity
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieve entity type string
     * @return entity type string
     */
    public String getName() {
        return name;
    }

    /**
     * Set entity type string
     * @param name entity type string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Abstract method for printing string related to entity
     * Implementation up to subclasses
     * @return string statement
     */
    public abstract String toString();

    /**
     * Abstract method for getting entity char representation
     * Implementation up to subclasses
     * @return entity char representation
     */
    public abstract char getSymbol();
}
