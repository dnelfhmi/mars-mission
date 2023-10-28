/**
 * Represents vegetable type entities in the game.
 * Extends Vegetation class.
 * Has a static set of allowed vegetable type names.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VegetableType extends Vegetation{

    //instance fields
    //utilise a set to restrict VegetableType creation to certain species
    private static final Set<String> VEGETABLE_ALLOWED = new HashSet<>(Arrays.asList("POTATO","TOMATO","ONIONS","APPLE","BANANA"));

    //constructor
    /**
     * Create VegetableType object with name type and coordinate
     * @param name name type string
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public VegetableType(String name, int x, int y) {
        super(name, x, y);
        if (!VEGETABLE_ALLOWED.contains(name)){
            throw new IllegalArgumentException("Invalid Vegetable Type!");
        }
    }

    //other methods
    /**
     * VegetableType interaction with other entities
     * @param entity entity interacted with
     */
    @Override
    public void interact(Entity entity) {
        if (entity instanceof SpaceRobot) {
            //water it
        }else if(entity instanceof Cattle){
            //eat it
        }else if(entity instanceof MartianAnimal){
            //destroy it
        }
    }

    /**
     * Print VegetableType name and its coordinate in habitat
     * @return VegetableType information in string
     */
    @Override
    public String toString() {
        return name +" at position (" + getY() + ", " + getX() + ")";
    }

    /**
     * Retrieve VegetableType char representation
     * @return VegetableType char representation
     */
    public char getSymbol(){
        char firstChar = 0;
        return name.charAt(firstChar);
    }
}
