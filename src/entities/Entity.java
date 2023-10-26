/**
 * Abstract base class for all entities in the Mars.
 * Direct subclasses are Vegetation, Rocks, Earth Animals and Martian Animals.
 *
 * Contains x and y position fields, along with getters and setters.
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

    public Entity(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public abstract String toString();
    public abstract char getSymbol();
}
