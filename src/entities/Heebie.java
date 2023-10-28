/**
 * Heebie --- a class that represents a species of Martian animal entities in the Mars habitat that can move around the habitat,
 * kill cattle, destroy vegetation, and fight with dogs, and affect the overall habitability.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import util.Movable;
import util.Collidable;
import util.Attackable;
import util.Feedable;
import util.HabitabilityMeter;
import util.MarsHabitat;
import util.ScannerSingleton;
import java.util.Scanner;

public class Heebie extends MartianAnimal implements Movable, Collidable, Attackable, Feedable {

    //instance fields
    Scanner scanner = ScannerSingleton.getScannerInstance();
    private final int MIN_HEALTH = 0;
    private final int MAX_HEALTH = 15;
    private final int INITIAL_POWER = 2;
    private final int STEP_SIZE = 1;
    private final int MIN_X_Y = 0; //map low boundary
    private final int MAX_X; //map high boundary
    private final int MAX_Y; //map high boundary
    private int prevX;
    private int prevY;
    private boolean recentlyCollided;
    private int health;
    private int attackPower;

    //constructor
    /**
     * Create Heebie, a Martian animal species with its previous and current coordinate,map boundary information and collision flag
     * @param x x-coordinate
     * @param y y-coordinate
     * @param mapWidth map boundary
     * @param mapHeight map boundary
     */
    public Heebie(int x, int y, int mapWidth, int mapHeight) {

        super(x, y);
        this.MAX_X = mapWidth;
        this.MAX_Y = mapHeight;
        this.prevX = x;
        this.prevY = y;
        this.recentlyCollided = false;
        this.health = MAX_HEALTH;
        this.attackPower = INITIAL_POWER;
    }

    //setter
    /**
     * Set previous x-coordinate
     * @param prevX previous x-coordinate
     */
    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    /**
     * Set previous y-coordinate
     * @param prevY previous y-coordinate
     */
    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    /**
     * Set collision flag
     * @param recentlyCollided collision flag
     */
    public void setRecentlyCollided(boolean recentlyCollided) {
        this.recentlyCollided = recentlyCollided;
    }

    /**
     * Set health
     * @param health health point
     */
    public void setHealth(int health) {
        this.health = health;
    }

    //getter
    /**
     * Retrieve previous x-coordinate
     * @return previous x-coordinate
     */
    public int getPrevX() {
        return prevX;
    }

    /**
     * Retrieve previous y-coordinate
     * @return previous y-coordinate
     */
    public int getPrevY() {
        return prevY;
    }

    /**
     * Retrieve collision flag
     * @return true if recently collided, false if otherwise
     */
    public boolean isRecentlyCollided() {
        return recentlyCollided;
    }

    /**
     * Retrieve current health
     * @return current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Retrieve char representation
     * @return char representation
     */
    @Override
    public char getMapSymbol() {
        return 'H';
    }

    /**
     * Retrieve char representation
     * @return char representation
     */
    @Override
    public char getSymbol() {
        return 'H';
    }

    /**
     * Retrieve attack power
     * @return attack power
     */
    public int getAttackPower() {return attackPower;}

    /**
     * Decrease health point by specified amount
     * @param amount reduction amount
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

    //printing entity position
    /**
     * Print Heebie coordinate in habitat
     * @return Heebie information in string
     */
    @Override
    public String toString() {
        return "HEEBIE at position (" + getY() + ", " + getX() + ")";
    }

    //movement logic
    /**
     * Check if the move is valid and within the Mars habitat boundary
     * @param newX target x-coordinate
     * @param newY target y-coordinate
     * @return true if the movement is valid, false otherwise
     */
    @Override
    public boolean isMoveValid(int newX, int newY) {
        if (newX < MIN_X_Y || newX > MAX_X || newY < MIN_X_Y || newY > MAX_Y) {
            System.out.println("Invalid movement. Out of boundary!");
            return false;
        }
        return true;
    }

    /**
     * Handles the movement of the Heebie by updating its previous and current position
     * @param newX target x-coordinate
     * @param newY target y-coordinate
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    private void handleMovement(int newX, int newY, MarsHabitat habitat, HabitabilityMeter meter) {
        if(!isCollisionAtPosition(newX, newY, habitat, meter)){
            prevX = x;
            prevY = y;
            this.x = newX;
            this.y = newY;
        }
    }

    /**
     * Moves the Heebie north by decreasing its x-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveNorth(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x-STEP_SIZE;
        int newY = y;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat, meter);
        }
    }

    /**
     * Moves the Heebie south by decreasing its y-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveSouth(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x+STEP_SIZE;
        int newY = y;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie east by increasing its x-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveEast(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x;
        int newY = y+STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie west by decreasing its x-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveWest(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x;
        int newY = y-STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie north-east by increasing its x-coordinate and decreasing its y-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveNorthEast(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x-STEP_SIZE;
        int newY = y+STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie north-west by decreasing its x-coordinate and decreasing its y-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveNorthWest(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x-STEP_SIZE;
        int newY = y-STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie north-east by increasing its x-coordinate and increasing its y-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveSouthEast(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x+STEP_SIZE;
        int newY = y+STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    /**
     * Moves the Heebie north-east by decreasing its x-coordinate and increasing its y-coordinate by the step size
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    @Override
    public void moveSouthWest(MarsHabitat habitat, HabitabilityMeter meter) {
        int newX = x+STEP_SIZE;
        int newY = y-STEP_SIZE;
        if (isMoveValid(newX,newY)) {
            handleMovement(newX,newY,habitat,meter);
        }
    }

    //collision logic
    /**
     * Checks if there is a collision at the given x,y position.
     *
     * @param newX The new x position to check
     * @param newY The new y position to check
     * @param habitat The MarsHabitat object representing the environment
     * @param meter The HabitabilityMeter object to track habitability
     *
     * This calls the onCollide() method if there is an entity at the new position,
     * passing it the entity, habitat, and meter. It sets recentlyCollided to true.
     *
     * @return boolean True if there is a collision, false otherwise.
     */
    @Override
    public boolean isCollisionAtPosition(int newX, int newY, MarsHabitat habitat, HabitabilityMeter meter) {
        Entity entityAtNewPosition = habitat.getEntityAtPosition(newX, newY);
        if (entityAtNewPosition != null) {
            //System.out.println("Colliding with " + entityAtNewPosition);
            this.onCollide(entityAtNewPosition,habitat,meter);
            recentlyCollided = true;
            return true;
        }
        recentlyCollided = false;
        return false;
    }

    /**
     * Handles collision logic when this Heebie collides with another entity.
     *
     * @param other The Entity object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     *
     * This checks the type of the other entity and calls the appropriate interaction handler method:
     * - If it is Cattle, calls handleCattleInteraction()
     * - If it is Vegetation, calls handleVegetationInteraction()
     * - If it is a Dog, calls handleDogInteraction()
     * - Otherwise prints that the location is invalid
     */
    @Override
    public void onCollide(Entity other, MarsHabitat habitat, HabitabilityMeter meter) {
        // Handle collision with other entity
        if (other instanceof Cattle) {
            Cattle cattle = (Cattle) other;
            handleCattleInteraction(cattle,habitat,meter);
        } else if(other instanceof Vegetation) {
            Vegetation vegetation = (Vegetation) other;
            handleVegetationInteraction(vegetation,habitat,meter);
        } else if(other instanceof Dog){
            Dog dog = (Dog) other;
            handleDogInteraction(dog,habitat,meter);
        }else{
            System.out.println("You cannot move to this location.");
        }
    }

    /**
     * Handles the interaction when Heebie collides with Cattle.
     *
     * @param cattle The Cattle object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     */
    private void handleCattleInteraction(Cattle cattle, MarsHabitat habitat,HabitabilityMeter meter) {
        System.out.println("The cattle are killed by the martian animals.");
        increaseHealth(cattle.getNutrition());
        habitat.removeOnMap(cattle.getX(), cattle.getY());
        meter.decreaseHabitabilityEvent(cattle);
    }

    /**
     * Handles the interaction when Heebie collides with Vegetation.
     *
     * @param vegetation The Vegetation object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     */
    private void handleVegetationInteraction(Vegetation vegetation, MarsHabitat habitat,HabitabilityMeter meter) {
        System.out.println("The plantation are eaten by the martian animals.");
        increaseHealth(vegetation.getNutrition());
        habitat.removeOnMap(vegetation.getX(), vegetation.getY());
        meter.decreaseHabitabilityEvent(vegetation);
    }

    /**
     * Handles the combat when Heebie collides with Dog
     * @param dog dog stumbled on
     * @param habitat the habitat where combat takes place
     * @param meter habitability meter
     */
    private void handleDogInteraction(Dog dog, MarsHabitat habitat,HabitabilityMeter meter) {
        System.out.println("Martian animal and Dog has entered a fight");

        while(health >= MIN_HEALTH && dog.getHealth() >= MIN_HEALTH){
            if (health <= MIN_HEALTH){
                System.out.println("Martian Animal died");
                break;
            }
            if (dog.getHealth() <= MIN_HEALTH){
                System.out.println("Dog died");
                break;
            }
            dog.decreaseHealth(attackPower);
            System.out.println("Martian Animal attacked dog. Health of dog reduced by "+attackPower+", Present Health: "+dog.getHealth());
            if(dog.getHealth() > MIN_HEALTH){
                decreaseHealth(dog.getAttackPower());
                System.out.println("Dog attacked Martian Animal. Martian Animal's health reduced by "+dog.getAttackPower()+", Present Health: "+health);
            }
        }
    }


}
