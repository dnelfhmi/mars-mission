/**
 * HabitabilityMeter class to handle all habitat score calculation and logic.
 * Constructors:
 * - Set up a map for entity char and string name
 * - Set up a map for entity string name and score
 * - Initialize habitat score and entity count map
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import entities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitabilityMeter {

    //instance fields
    // Map to hold the entity representation and its corresponding name
    private static final Map<Character, String> ENTITY_MAP = new HashMap<>();
    // Map to hold the entity representation and its corresponding name
    private static final Map<String, Integer> SCORE_MAP = new HashMap<>();
    // Map to hold current entity count
    private Map<String, Integer> entityCount;
    private int habitabilityScore;

    //constructor

    /**
     * Constructor of HabitabilityMeter object
     * Initialise Hash Map for entity char to entity string name map
     * Initialise Hash Map for entity string name to entity score map
     * Create a new entityCount map for printing purposes
     * Set habitability score to zero
     */
    public HabitabilityMeter(){
        int initialHabitatScore = 0;
        int vegetationScore = 2;
        int earthAnimalScore = 5;
        int plainRockScore = 1;
        int mineralScore = 2;

        habitabilityScore = initialHabitatScore;

        entityCount = new HashMap<>();

        ENTITY_MAP.put('P', "POTATO");
        ENTITY_MAP.put('T', "TOMATO");
        ENTITY_MAP.put('O', "ONION");
        ENTITY_MAP.put('R', "ROSE");
        ENTITY_MAP.put('L', "LILY");
        ENTITY_MAP.put('A', "APPLE");
        ENTITY_MAP.put('B', "BANANA");
        ENTITY_MAP.put('E', "EUCALYPTUS");
        ENTITY_MAP.put('C', "COW");
        ENTITY_MAP.put('G', "GOAT");
        ENTITY_MAP.put('S', "SHEEP");
        ENTITY_MAP.put('D', "DOG");
        ENTITY_MAP.put('@', "ROCK");
        ENTITY_MAP.put('*', "MINERAL");

        SCORE_MAP.put("POTATO", vegetationScore);
        SCORE_MAP.put("TOMATO", vegetationScore);
        SCORE_MAP.put("ONION", vegetationScore);
        SCORE_MAP.put("ROSE", vegetationScore);
        SCORE_MAP.put("LILY", vegetationScore);
        SCORE_MAP.put("APPLE", vegetationScore);
        SCORE_MAP.put("BANANA", vegetationScore);
        SCORE_MAP.put("EUCALYPTUS", vegetationScore);
        SCORE_MAP.put("COW", earthAnimalScore);
        SCORE_MAP.put("GOAT", earthAnimalScore);
        SCORE_MAP.put("SHEEP", earthAnimalScore);
        SCORE_MAP.put("DOG", earthAnimalScore);
        SCORE_MAP.put("ROCK", plainRockScore);
        SCORE_MAP.put("MINERAL", mineralScore);
    }

    //setter
    /**
     * Set habitability score of a habitat
     * @param habitabilityScore score of a Mars habitat
     */
    public void setHabitabilityScore(int habitabilityScore) {
        this.habitabilityScore = habitabilityScore;
    }

    //getter
    /**
     * Retrieve habitability score of a habitat
     * @return score of a Mars habitat
     */
    public int getHabitabilityScore() {
        return habitabilityScore;
    }

    /**
     * Retrieve entityCount map that stores entity with its count mapping
     * @return entity with its count Hash map
     */
    public Map<String, Integer> getEntityCount() {
        return entityCount;
    }

    //method for printing Habitability Status
    /**
     * Prints a report of the entity counts and habitability score.
     * Loops through the entityCount map, printing each entry
     * except for ROCK.
     */
    public void printEntities() {
        int zeroScore = 0;
        System.out.println("Habitability Status");
        System.out.println("======================");
        for (Map.Entry<String, Integer> entry : entityCount.entrySet()) {
            if (!entry.getKey().equals("ROCK")) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
        if (habitabilityScore==zeroScore){
            System.out.println("No Record found.");
        } else {
            System.out.println("\nTotal Habitability Score: " + habitabilityScore);
        }
    }

    //method to count all entities in a file
    /**
     * count all entities in a file and add each entity in the entityCount map
     * @param fileHandler file
     * @return map of entity with their count
     */
    public Map<String, Integer> countEntities(FileHandler fileHandler) {
        entityCount.clear();
        List<String> map = fileHandler.getDataLines();

        int incrementCount = 1;
        int firstTimeCount = 0;

        for (String line : map) {
            for (char c : line.toCharArray()) {
                String entity = ENTITY_MAP.get(c);
                if (entity != null) {
                    int prevCount = entityCount.getOrDefault(entity, firstTimeCount);
                    entityCount.put(entity, prevCount + incrementCount);
                }
            }
        }
        return entityCount;
    }

    //method to calculate habitability score based on entities counted by countEntities method
    /**
     * Calculates the habitability score based on entity counts.
     * Calculates the score by:
     * - Streaming the entrySet of the count map, count.entrySet().stream()
     * - Filtering out MINERAL and ROCK entries, filter(entry -> !entry.getKey().equals("MINERAL") !entry.getKey().equals("ROCK"))
     * - Mapping each entry to:
     *   - Get the score for the entity from SCORE_MAP, entry -> SCORE_MAP.getOrDefault(entry.getKey(), 0)
     *   - Multiply it by the count for that entity, entry.getValue()
     * - Summing the mapped values, sum()
     *
     * Saves the result to the habitabilityScore field.
     */
    public void calculateHabitabilityScore(FileHandler fileHandler) {
        int firstTimeCount = 0;
        Map<String, Integer> count = countEntities(fileHandler);
        int score = count.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("MINERAL") && !entry.getKey().equals("ROCK"))
                .mapToInt(entry -> SCORE_MAP.getOrDefault(entry.getKey(), firstTimeCount) * entry.getValue())
                .sum();
        this.habitabilityScore = score;
    }

    //add entity to entityCount map
    /**
     * add entity to entityCount map
     * @param entity
     */
    public void addEntity(String entity) {
        int incrementCount = 1;
        int firstTimeCount = 0;
        int prevCount = entityCount.getOrDefault(entity, firstTimeCount);
        entityCount.put(entity, prevCount + incrementCount);
    }

    //increase score based on entity name
    /**
     * increase score based on entity name
     * @param entity
     */
    public void increaseHabitability(String entity) {
        Integer increaseAmount = SCORE_MAP.get(entity);
        if (increaseAmount != null) {
            addEntity(entity);
            habitabilityScore += increaseAmount;
        } else {
            System.out.println("No score defined for entity " + entity);
        }
    }

    //increase score based on entity interaction
    /**
     * increase score based on entity interaction
     * @param event interaction between entities
     * @return true if valid event, false if otherwise
     */
    public boolean increaseHabitabilityEvent(Entity event) {
        int notMineralIncrement = 1;
        int isMineralIncrement = 2;
        int eliminateHostileIncrement = 7;

        if (event instanceof Vegetation) {
            habitabilityScore += notMineralIncrement;
        } else if (event instanceof Cattle) {
            habitabilityScore += notMineralIncrement;
        } else if (event instanceof Mineral){
            habitabilityScore += isMineralIncrement;
        } else if (event instanceof PlainRock){
            habitabilityScore += notMineralIncrement;
        } else if (event instanceof MartianAnimal){
            habitabilityScore += eliminateHostileIncrement;
        } else {
            System.out.println("Invalid command");
            return true;
        }
        return false;
    }

    //decrease score based on entity interaction

    /**
     * decrease score based on entity interaction
     * @param event interaction between entities
     */
    public void decreaseHabitabilityEvent(Entity event) {
        int plantDecrementAmount = 2;
        int animalDecrementAmount = 5;

        if (event instanceof Vegetation) {
            habitabilityScore -= plantDecrementAmount;
            decrementEntityCount(event);
        } else if (event instanceof Cattle) {
            habitabilityScore -= animalDecrementAmount;
            decrementEntityCount(event);
        } else if (event instanceof Dog){
            habitabilityScore -= animalDecrementAmount;
            decrementEntityCount(event);
        }else {
            System.out.println("Invalid command");
        }
    }

    //remove entity from entityCount map
    /**
     * get entity and decrement its count
     * @param entity target entity
     */
    public void decrementEntityCount(Entity entity) {
        int zeroEntityCount = 0;
        char entitySymbol = entity.getSymbol();
        String entityName = ENTITY_MAP.get(entitySymbol);

        if (entityName == null) {
            return;
        }

        if (entityCount.containsKey(entityName)) {
            int currentCount = entityCount.get(entityName);
            if (currentCount > zeroEntityCount) {
                currentCount--;
            } else {
                entityCount.remove(entityName);
            }
            entityCount.put(entityName, currentCount);
        }
    }
}

/*
 * New habitability score need to append to the log file, not rewrite the log file(like wipe out the previous history)
 */