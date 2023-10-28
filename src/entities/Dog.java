/**
 * Represents dog entities in the game.
 * Extends EarthAnimal class and implement Attackable interface.
 * Has health to combat with hostile entities such as Heebie and Jeebie.
 * Is feedable by Space Robot with plants to increase health.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import util.Attackable;
import util.Feedable;

public class Dog extends EarthAnimal implements Attackable, Feedable {
    //instance fields
    private final int MIN_HEALTH = 0;
    private final int MAX_HEALTH = 10;
    private final int INITIAL_POWER = 2;
    private int health;
    private int attackPower;

    //constructor
    /**
     * Create Dog object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Dog(String name, int x, int y) {
        super(name, x, y);
        this.health = MAX_HEALTH;
        this.attackPower = INITIAL_POWER;
    }

    /**
     * Print Dog name and its coordinate in habitat
     * @return Dog information in string
     */
    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve Dog char representation
     * @return Dog char representation
     */
    @Override
    public char getSymbol() {
        return 'D';
    }

    /**
     * Retrieve Dog current health
     * @return Dog current health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Retrieve attack power
     * @return attack power
     */
    public int getAttackPower() {return attackPower;}

    /**
     * Deduct Dog current health
     * @param amount deduction amount
     */
    @Override
    public void decreaseHealth(int amount) {
        health -= amount;
    }

    /**
     * Increase health point by specified amount
     * @param amount increment amount
     */
    public void increaseHealth(int amount){
        health += amount;
    }

}

