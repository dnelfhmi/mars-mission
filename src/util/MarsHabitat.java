/**
 * MarsHabitat class handles all the map layout, position update for all entities.
 * Any addition and removal of entity from the map is done here.
 * Work with map stored in 2-dimensional char array and string list of the line in the map.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import entities.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class MarsHabitat {

    //instance fields
    private List<Entity> entity;
    private List<String> dataLines;
    private char[][] marsMap;

    //constructor
    public MarsHabitat() {
        this.entity = new ArrayList<Entity>();
    }

    //getter
    public char[][] getMarsMap() {
        return marsMap;
    }

    public List<Entity> getEntity() {
        return entity;
    }

    //main method for processing map

    /**
     * Main method for processing map passed by fileHandler
     * Invoke method for displaying map, converting list of string to 2-dimensional char array
     * And parsing the map
     * @param fileHandler
     */
    protected void processMap(FileHandler fileHandler){
        try{
            dataLines = fileHandler.getDataLines(); //set dataLines field. map representation in List<String>
            displayDefaultMap(fileHandler);
            convertToChar(fileHandler.getDataLines()); //set marsMap field. map representation in char[][]
            parseDataLines(fileHandler);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (InvalidFileException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (UnknownEntityException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Method for displaying map by taking list of string (dataLines)
     * @param fileHandler
     * @throws FileNotFoundException If no file existed/found
     * @throws InvalidFileException If invalid map formatting in file content
     * @throws UnknownEntityException If unknown entity present in file content
     */
    protected void displayDefaultMap(FileHandler fileHandler) throws InvalidFileException, UnknownEntityException, FileNotFoundException {
        List<String> mapData = fileHandler.loadFile();
        System.out.println("Here is a layout of Martian land.\n");
        for (String line : mapData) {
            System.out.println(line);
        }System.out.println("");
    }

    /**
     * Print map based on 2-dimensional char array
     */
    public void printMarsMap() {
        System.out.println("Here is a layout of Martian land.\n");
        for (int i = 0; i < marsMap.length; i++) {
            for (int j = 0; j < marsMap[i].length; j++) {
                System.out.print(marsMap[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Convert map layout from list of string to 2-dimensional char array
     * @param dataLines
     */
    protected void convertToChar(List<String> dataLines) {
        int rows = dataLines.size();
        int cols = dataLines.get(0).length();

        marsMap = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                marsMap[i][j] = dataLines.get(i).charAt(j);
            }
        }
    }

    /**
     * convert map layout from 2-dimensional char array to list of string
     * @return list of strings for displaying the map layout
     */
    protected List<String> convertToLine() {
        dataLines = new ArrayList<>();
        for (char[] row : marsMap) {
            dataLines.add(new String(row));
        }
        return dataLines;
    }

    /**
     * Parse each element/cell in 2-dimensional char array
     * Check and add entities to the entity list
     * @param fileHandler
     */
    protected void parseDataLines(FileHandler fileHandler) { //to add entity from file to entityList
        int mapOffset = 1;
        for (int i = 0; i < marsMap.length; i++) {
            for (int j = 0; j < marsMap[i].length; j++)
                switch (marsMap[i][j]) {
                    case 'Z':
                        entity.add(new SpaceRobot(i, j,marsMap.length-mapOffset,marsMap[i].length-mapOffset));
                        break;
                    case 'X':
                        entity.add(new SpaceRover(i,j,marsMap.length-mapOffset,marsMap[i].length-mapOffset));
                        break;
                    case 'H':
                        entity.add(new Heebie(i, j,marsMap.length-mapOffset,marsMap[i].length-mapOffset));
                        break;
                    case 'J':
                        entity.add(new Jeebie(i, j,marsMap.length-mapOffset,marsMap[i].length-mapOffset));
                        break;
                    case 'C':
                        entity.add(new Cattle("COW",i, j));
                        break;
                    case 'G':
                        entity.add(new Cattle("GOAT",i, j));
                        break;
                    case 'S':
                        entity.add(new Cattle("SHEEP",i, j));
                        break;
                    case 'D':
                        entity.add(new Dog("DOG",i, j));
                        break;
                    case 'R':
                        entity.add(new PlantType("ROSE",i, j));
                        break;
                    case 'L':
                        entity.add(new PlantType("LILY",i, j));
                        break;
                    case 'E':
                        entity.add(new PlantType("EUCALYPTUS",i, j));
                        break;
                    case 'P':
                        entity.add(new VegetableType("POTATO",i, j));
                        break;
                    case 'T':
                        entity.add(new VegetableType("TOMATO",i, j));
                        break;
                    case 'O':
                        entity.add(new VegetableType("ONIONS",i, j));
                        break;
                    case 'A':
                        entity.add(new VegetableType("APPLE",i, j));
                        break;
                    case 'B':
                        entity.add(new VegetableType("BANANA",i, j));
                        break;
                    case '@':
                        entity.add(new PlainRock("PLAINROCK",i, j));
                        break;
                    case '*':
                        entity.add(new Mineral("MINERAL",i, j));
                        break;
                }
        }
    }

    /**
     * Update the current and previous position of a Movable entity
     * @param entity
     */
    public void updateEntityPosition(Movable entity) {
        // Clear the previous position
        marsMap[entity.getPrevX()][entity.getPrevY()] = '.';

        // Set the new position
        marsMap[entity.getX()][entity.getY()] = entity.getMapSymbol();
    }

    /**
     * Generic used to filter entity based on the entity Class type
     * @param entityType Class Type of interested entity
     * @return list of entity with the specified Class type
     * @param <T> Generic Type
     */
    protected <T extends Entity> List<T> filterEntities(Class<T> entityType) {
        List<Entity> entities = getEntity();
        List<T> filteredEntities = new ArrayList<>();

        for (Entity entity : entities) {
            if (entityType.isInstance(entity)) {
                filteredEntities.add(entityType.cast(entity));
            }
        }
        return filteredEntities;
    }

    /**
     * Generic used to list all the filtered/interested entities for printing entity list during entity selection
     * @param entityType Class Type of interested entity
     * @param entityName
     * @param <T> Generic Type
     */
    protected <T extends Entity> void listEntity(Class<T> entityType, String entityName) {
        List<T> filteredEntities = filterEntities(entityType);
        int listOffset = 1;

        System.out.println("There are " + filteredEntities.size() + " " + entityName + " found. Select");
        for (int i = 0; i < filteredEntities.size(); i++) {
            System.out.println("[" + (i + listOffset) + "] for " + filteredEntities.get(i).toString());
        }
    }

    /**
     * Check character at position i and j
     * @param i x-coordinate
     * @param j y-coordinate
     * @return character at position i and j
     */
    public boolean isPositionEmpty(int i, int j) {
        //System.out.println("Character to the left: " + marsMap[i][j]);
        return marsMap[i][j] == '.'||marsMap[i][j] == 'Z';
    }

    /**
     * Check if left of updated position is empty '.'
     * @param x updated x-coordinate
     * @param y updated y-coordinate offset by 1
     * @return true if empty, false otherwise
     */
    public boolean isLeftOfPositionEmpty(int x, int y) {
        int positionOffset = 1;
        return isPositionEmpty(x , y-positionOffset);
    }

    /**
     * Retrieve entity from entity list given the x and y coordinates of the entity
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Entity at x and y-coordinate
     */
    public Entity getEntityAtPosition(int x, int y) {
        for (Entity e : entity) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        return null;
    }

    /**
     * Method to perform planting by adding plant to map and entity list
     * @param plant plant name
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void plantOnMap(String plant, int x, int y) {
        switch(plant) {
            case "POTATO":
                VegetableType potato = new VegetableType("POTATO",x,y);
                entity.add(potato);
                marsMap[potato.getX()][potato.getY()] = 'P';
                break;
            case "TOMATO":
                VegetableType tomato = new VegetableType("TOMATO",x,y);
                entity.add(tomato);
                marsMap[tomato.getX()][tomato.getY()] = 'T';
                break;
            case "ONION":
                VegetableType onion = new VegetableType("ONION", x, y);
                entity.add(onion);
                marsMap[onion.getX()][onion.getY()] = 'O';
                break;
            case "APPLE":
                VegetableType appleTree = new VegetableType("APPLE", x, y);
                entity.add(appleTree);
                marsMap[appleTree.getX()][appleTree.getY()] = 'A';
                break;
            case "BANANA":
                VegetableType bananaTree = new VegetableType("BANANA", x, y);
                entity.add(bananaTree);
                marsMap[bananaTree.getX()][bananaTree.getY()] = 'B';
                break;
            case "LILY":
                PlantType lily = new PlantType("LILY", x, y);
                entity.add(lily);
                marsMap[lily.getX()][lily.getY()] = 'L';
                break;
            case "ROSE":
                PlantType rose = new PlantType("ROSE", x, y);
                entity.add(rose);
                marsMap[rose.getX()][rose.getY()] = 'R';
                break;
            case "EUCALYPTUS":
                PlantType eucalyptusTree = new PlantType("EUCALYPTUS", x, y);
                entity.add(eucalyptusTree);
                marsMap[eucalyptusTree.getX()][eucalyptusTree.getY()] = 'E';
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Method to perform planting by adding plant to map and entity list
     * @param cattle cattle name
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void cattleOnMap(String cattle, int x, int y) {
        switch(cattle) {
            case "GOAT":
                Cattle goat = new Cattle("GOAT",x,y);
                entity.add(goat);
                marsMap[goat.getX()][goat.getY()] = 'G';
                break;
            case "SHEEP":
                Cattle sheep = new Cattle("SHEEP",x,y);
                entity.add(sheep);
                marsMap[sheep.getX()][sheep.getY()] = 'S';
                break;
            case "COW":
                Cattle cow = new Cattle("COW",x,y);
                entity.add(cow);
                marsMap[cow.getX()][cow.getY()] = 'C';
                break;
            case "DOG":
                Dog dog = new Dog("DOG",x,y);
                entity.add(dog);
                marsMap[dog.getX()][dog.getY()] = 'C';
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Method for removing entity at specified x and y-coordinate from the map and entity list.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void removeOnMap(int x, int y){
        for (int i = entity.size() - 1; i >= 0; i--) {
            Entity currentEntity = entity.get(i);

            if ((currentEntity instanceof Rocks || currentEntity instanceof Vegetation
                    || currentEntity instanceof Cattle) && currentEntity.getX() == x && currentEntity.getY() == y) {
                entity.remove(i);
                marsMap[x][y] = '.';
                break;
            }
        }
    }


}
