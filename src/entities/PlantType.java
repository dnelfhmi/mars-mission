/**
 * Represents plant type entities in the game.
 * Extends Vegetation class.
 * Has a static set of allowed plant type names.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlantType extends Vegetation {

    //instance fields
    //utilise a set to restrict PlantType creation to certain species
    private static final Set<String> PLANTS_ALLOWED = new HashSet<>(Arrays.asList("ROSE","LILY","EUCALYPTUS"));

    //constructor
    /**
     * Create PlantType object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public PlantType(String name, int x, int y) {
        super(name, x, y);
        if (!PLANTS_ALLOWED.contains(name)){
            throw new IllegalArgumentException("Invalid Plant Type!");
        }
    }

    //other methods
    /**
     * PlantType interaction with other entities
     * @param entity entity interacted with
     */
    @Override
    public void interact(Entity entity) {
        if (entity instanceof SpaceRobot) {
            //watered by SpaceRobot
        }
    }

    /**
     * Print PlantType name and its coordinate in habitat
     * @return PlantType information in string
     */
    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve PlantType char representation
     * @return PlantType char representation
     */
    public char getSymbol(){
        char firstChar = 0;
        return name.charAt(firstChar);
    }
}
