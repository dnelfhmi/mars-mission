/**
 * SpaceRover --- a class that represents rover entities in the Mars habitat that can move around the habitat,
 * interact with rocks and minerals, and affect the overall habitability.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import util.Movable;
import util.Collidable;
import util.HabitabilityMeter;
import util.MarsHabitat;

public class SpaceRover extends Entity implements Movable, Collidable {

    //instance fields
    private final int STEP_SIZE = 1;
    private final int MIN_X_Y = 0; //map low boundary
    private final int MAX_X; //map high boundary
    private final int MAX_Y; //map high boundary
    private int prevX;
    private int prevY;
    private boolean recentlyCollided;

    //constructor
    /**
     * Create SpaceRover with its previous and current coordinate,map boundary information and collision flag
     * @param x initial x-coordinate of the robot
     * @param y initial y-coordinate of the robot
     * @param mapWidth width of the habitat map
     * @param mapHeight height of the habitat map
     */
    public SpaceRover(int x, int y, int mapWidth, int mapHeight) {

        super(x, y);
        this.MAX_X = mapWidth;
        this.MAX_Y = mapHeight;
        this.prevX = x;
        this.prevY = y;
        this.recentlyCollided = false;
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
     * Retrieve char representation
     * @return char representation
     */
    @Override
    public char getMapSymbol() {
        return 'X';
    }

    /**
     * Retrieve char representation
     * @return char representation
     */
    @Override
    public char getSymbol() {
        return 'X';
    }

    //printing entity position
    /**
     * Print SpaceRover coordinate in habitat
     * @return SpaceRover information in string
     */
    @Override
    public String toString() {
        return "Space Rover at position (" + getY() + ", " + getX() + ")";
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
     * Handles the movement of the rover by updating its previous and current position
     * @param newX target x-coordinate
     * @param newY target y-coordinate
     * @param habitat reference to the MarsHabitat object
     * @param meter reference to the HabitabilityMeter object
     */
    private void handleMovement(int newX, int newY, MarsHabitat habitat, HabitabilityMeter meter) {
        isCollisionAtPosition(newX, newY, habitat, meter);
        prevX = x;
        prevY = y;
        this.x = newX;
        this.y = newY;
    }

    /**
     * Moves the rover north by decreasing its x-coordinate by the step size
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
     * Moves the rover south by decreasing its y-coordinate by the step size
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
     * Moves the rover east by increasing its x-coordinate by the step size
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
     * Moves the rover west by decreasing its x-coordinate by the step size
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
     * Moves the rover north-east by increasing its x-coordinate and decreasing its y-coordinate by the step size
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
     * Moves the rover north-west by decreasing its x-coordinate and decreasing its y-coordinate by the step size
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
     * Moves the rover north-east by increasing its x-coordinate and increasing its y-coordinate by the step size
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
     * Moves the rover north-east by decreasing its x-coordinate and increasing its y-coordinate by the step size
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
     * Handles collision logic when this rover collides with another entity.
     *
     * @param other The Entity object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     *
     * - If it is a Mineral, calls handleMineralInteraction()
     * - If it is a PlainRock, calls handlePlainRockInteraction()
     * - Otherwise prints that the location is invalid
     */
    @Override
    public void onCollide(Entity other, MarsHabitat habitat, HabitabilityMeter meter) {
        // Handle collision with other entity
        if (other instanceof Mineral) {
            Mineral mineral = (Mineral) other;
            handleMineralInteraction(mineral,habitat,meter);
        } else if(other instanceof PlainRock) {
            PlainRock plainRock = (PlainRock) other;
            handlePlainRockInteraction(plainRock,habitat,meter);
        }else{
            System.out.println("You cannot move to this location.");
        }
    }

    /**
     * Handles the interaction when the rover collides with a Mineral.
     *
     * @param mineral The Mineral object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     */
    private void handleMineralInteraction(Mineral mineral, MarsHabitat habitat,HabitabilityMeter meter) {
        System.out.println("We found a mineral, Rover will collect it now.");
        habitat.removeOnMap(mineral.getX(), mineral.getY());
        meter.increaseHabitabilityEvent(mineral);
    }

    /**
     * Handles the interaction when the rover collides with a PlainRock.
     *
     * @param plainRock The PlainRock object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     */
    private void handlePlainRockInteraction(PlainRock plainRock, MarsHabitat habitat,HabitabilityMeter meter) {
        System.out.println("We found a plain rock, Rover will destroy it now.");
        habitat.removeOnMap(plainRock.getX(), plainRock.getY());
        meter.increaseHabitabilityEvent(plainRock);
    }
}
