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
    public PlantType(String name, int x, int y) {
        super(name, x, y);
        if (!PLANTS_ALLOWED.contains(name)){
            throw new IllegalArgumentException("Invalid Plant Type!");
        }
    }

    //other methods
    @Override
    public void interact(Entity entity) {
        if (entity instanceof SpaceRobot) {
            //water it
        }
    }

    @Override
    public String toString() {
        return name + " at position (" + getY() + ", " + getX() + ")";
    }

    public char getSymbol(){
        char firstChar = 0;
        return name.charAt(firstChar);
    }
}
