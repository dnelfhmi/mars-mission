/**
 * SpaceRobot --- a class that represents robot entities in the Mars habitat that can move around the habitat,
 * assign and interact with cattle and vegetation, and affect the overall habitability.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import util.Movable;
import util.Collidable;
import util.HabitabilityMeter;
import util.MarsHabitat;
import util.ScannerSingleton;
import java.util.Scanner;

public class SpaceRobot extends Entity implements Movable, Collidable {

    //instance fields
    Scanner scanner = ScannerSingleton.getScannerInstance();
    private final int STEP_SIZE = 1;
    private final int MIN_X_Y = 0; //map low boundary
    private final int MAX_X; //map high boundary
    private final int MAX_Y; //map high boundary
    private int prevX;
    private int prevY;
    private boolean recentlyCollided;

    //constructor
    /**
     * Create SpaceRobot with its previous and current coordinate,map boundary information and collision flag
     * @param x initial x-coordinate of the robot
     * @param y initial y-coordinate of the robot
     * @param mapWidth width of the habitat map
     * @param mapHeight height of the habitat map
     */
    public SpaceRobot(int x, int y, int mapWidth, int mapHeight) {

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
        return 'Z';
    }

    /**
     * Retrieve char representation
     * @return char representation
     */
    @Override
    public char getSymbol() {
        return 'Z';
    }

    //printing entity position
    /**
     * Print SpaceRobot coordinate in habitat
     * @return SpaceRobot information in string
     */
    @Override
    public String toString() {
        return "Space Robot at position (" + getY() + ", " + getX() + ")";
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
        if (newX < MIN_X_Y || newX >= MAX_X || newY < MIN_X_Y || newY >= MAX_Y) {
            System.out.println("Invalid movement. Out of boundary!");
            return false;
        }
        return true;
    }

    /**
     * Handles the movement of the robot by updating its previous and current position if  @link{isCollisionAtPosition} is not true
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
     * Moves the robot north by decreasing its x-coordinate by the step size
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
     * Moves the robot south by decreasing its y-coordinate by the step size
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
     * Moves the robot east by increasing its x-coordinate by the step size
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
     * Moves the robot west by decreasing its x-coordinate by the step size
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
     * Moves the robot north-east by increasing its x-coordinate and decreasing its y-coordinate by the step size
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
     * Moves the robot north-west by decreasing its x-coordinate and decreasing its y-coordinate by the step size
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
     * Moves the robot north-east by increasing its x-coordinate and increasing its y-coordinate by the step size
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
     * Moves the robot north-east by decreasing its x-coordinate and increasing its y-coordinate by the step size
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
            return true;  // Collision detected
        }
        recentlyCollided = false;
        return false; // No collision detected
    }

    /**
     * Handles collision logic when this robot collides with another entity.
     *
     * @param other The Entity object that was collided with
     * @param habitat The MarsHabitat representing the environment
     * @param meter The HabitabilityMeter to track habitability
     *
     * - If it is Vegetation, calls handleVegetationInteraction()
     * - If it is Cattle, calls handleEarthAnimalInteraction()
     * - Otherwise prints that the location is invalid
     */
    @Override
    public void onCollide(Entity other, MarsHabitat habitat, HabitabilityMeter meter) {
        // Handle collision with other entity
        if (other instanceof Vegetation) {
            Vegetation vegetation = (Vegetation) other;
            handleVegetationInteraction(vegetation,meter);
        } else if (other instanceof Cattle) {
            Cattle animal = (Cattle) other;
            handleCattleInteraction(animal,meter);
        } else if (other instanceof Dog) {
            Dog dog = (Dog) other;
            handleDogInteraction(dog);
        }else {
            System.out.println("You cannot move to this location.");
        }
    }

    //interaction logic
    /**
     * Handles the interaction when the robot collides with vegetation.
     *
     * @param vegetation The Vegetation object that was collided with.
     * @param meter The HabitabilityMeter object to track habitability changes.
     */
    private void handleVegetationInteraction(Vegetation vegetation, HabitabilityMeter meter) {
        boolean plantWateringLoop = true;
        while(plantWateringLoop) {
            System.out.println("Do you want to water the plant?Enter Y for yes, N for No ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                //vegetation.water();
                plantWateringLoop = meter.increaseHabitabilityEvent(vegetation);
                System.out.println("You watered a plant. It will grow");
            } else if (choice.equals("N")) {
                System.out.println("You do nothing to the plant");
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    /**
     * Handles the interaction when the robot collides with cattle.
     *
     * @param animal The Cattle object that was collided with.
     * @param meter The HabitabilityMeter object to track habitability changes.
     */
    private void handleCattleInteraction(Cattle animal, HabitabilityMeter meter) {
        boolean feedCattleLoop = true;
        while(feedCattleLoop) {
            System.out.println("Do you want to feed the cattle?Enter Y for yes, N for No ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                //vegetation.water();
                feedCattleLoop = meter.increaseHabitabilityEvent(animal);
                System.out.println("You have fed the cattle. It will grow");
            } else if (choice.equals("N")) {
                System.out.println("You do nothing to the cattle");
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    private void handleDogInteraction(Dog dog){
        int dogFoodNutrition = 1;
        while(true) {
            System.out.println("Do you want to feed the dog?Enter Y for yes, N for No ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                dog.increaseHealth(dogFoodNutrition);
                System.out.println("You have fed the dog. It smiles to you.");
                return;
            } else if (choice.equals("N")) {
                System.out.println("You do nothing to the dog. Poor dog.");
                return;
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    //if left position empty methods
    /**
     * Handles planting vegetation based on user input.
     *
     * @param habitat The MarsHabitat to plant vegetation on
     * @param meter The HabitabilityMeter to update when planting
     * @return Returns false if a valid choice was selected, true otherwise.
     *
     * This takes in a choice from the user and plants the corresponding
     * vegetation type at the robot's current x,y position, offset left by 1.
     */
    public boolean handleVegetationPlanting(MarsHabitat habitat, HabitabilityMeter meter) {
        int choice = scanner.nextInt();
        int leftPositionAdjustment = 1;
        scanner.nextLine();
        switch (choice) {
            case 1:
                habitat.plantOnMap("POTATO", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("POTATO");
                System.out.println("A Potato has been planted.");
                break;
            case 2:
                habitat.plantOnMap("TOMATO", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("TOMATO");
                System.out.println("A Tomato has been planted.");
                break;
            case 3:
                habitat.plantOnMap("ONION", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("ONION");
                System.out.println("An Onion has been planted.");
                break;
            case 4:
                habitat.plantOnMap("APPLE", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("APPLE");
                System.out.println("An Apple tree has been planted.");
                break;
            case 5:
                habitat.plantOnMap("BANANA", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("BANANA");
                System.out.println("A Banana tree has been planted.");
                break;
            case 6:
                habitat.plantOnMap("LILY", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("LILY");
                System.out.println("A Lily has been planted.");
                break;
            case 7:
                habitat.plantOnMap("ROSE", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("ROSE");
                System.out.println("A Rose has been planted.");
                break;
            case 8:
                habitat.plantOnMap("EUCALYPTUS", getX(), getY() - leftPositionAdjustment);
                meter.increaseHabitability("EUCALYPTUS");
                System.out.println("A Eucalyptus tree has been planted.");
                break;
            default:
                System.out.println("Invalid choice.");
                return true;
        }
        return false;
    }

    /**
     * Handles placing cattle based on user input.
     *
     * @param habitat The MarsHabitat to place cattle on
     * @param meter The HabitabilityMeter to update when placing cattle
     * @return Returns false if a valid choice was selected, true otherwise.
     *
     * This takes in a choice from the user and places the corresponding
     * cattle type at the robot's current x,y position, offset left by 1.
     */
    public boolean handleCattlePlacement(MarsHabitat habitat, HabitabilityMeter meter) {
        int choice = scanner.nextInt();
        int leftPositionAdjustment = 1;
        scanner.nextLine();
        switch(choice) {
            case 1:
                habitat.cattleOnMap("GOAT", getX(), getY()-leftPositionAdjustment);
                meter.increaseHabitability("GOAT");
                System.out.println("A Goat has been added.");
                break;
            case 2:
                habitat.cattleOnMap("SHEEP", getX(), getY()-leftPositionAdjustment);
                meter.increaseHabitability("SHEEP");
                System.out.println("A Sheep has been added.");
                break;
            case 3:
                habitat.cattleOnMap("COW", getX(), getY()-leftPositionAdjustment);
                meter.increaseHabitability("COW");
                System.out.println("An Cow has been added.");
                break;
            case 4:
                habitat.cattleOnMap("DOG", getX(), getY()-leftPositionAdjustment);
                meter.increaseHabitability("DOG");
                System.out.println("An Dog has been added.");
                break;
            default:
                System.out.println("Invalid choice.");
                return true;
        }
        return false;
    }
}

