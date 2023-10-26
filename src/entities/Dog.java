/**
 * Represents dog entities in the game.
 * Extends EarthAnimal class and implement Attackable interface.
 * Has health to combat with hostile entities such as Heebie and Jeebie.
 * Is feedable by Space Robot with plants to increase health.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import util.Attackable;

public class Dog extends EarthAnimal implements Attackable {
    //instance fields
    private final int MAX_HEALTH = 10;
    private int health;
    //constructor
    public Dog(String name, int x, int y) {
        super(name, x, y);
        this.health = MAX_HEALTH;
    }
    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }
    @Override
    public char getSymbol() {
        return 'D';
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void decreaseHealth(int amount) {
        health -= amount;
    }

}

