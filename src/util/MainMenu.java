/**
 * MainMenu class to handle all user interaction with the program and control flow of the program.
 * When initialised, it will bound a new file, habitat, its habitability score to the main menu user interface.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import entities.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class MainMenu {

    //instance fields
    private Scanner scanner;
    private FileHandler fileHandler;
    private MarsHabitat habitat;
    private HabitabilityMeter meter;

    //constructor
    public MainMenu(){
        scanner = ScannerSingleton.getScannerInstance();
        habitat = new MarsHabitat();
        meter = new HabitabilityMeter();
        fileHandler = null;
    }

    //loading file to program
    /**
     * One way to load file is by taking user input after booting program
     */
    public void handleUserLoadFileInput(){
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.println("Enter a file name to setup Martian Land Map");
                System.out.print("> ");
                String filePath = scanner.nextLine();
                fileHandler = new FileHandler(filePath);
                habitat.processMap(fileHandler);
                displayHabitatScore();
            }
            case 2 -> {
                fileHandler = new FileHandler();
                habitat.processMap(fileHandler);
                displayHabitatScore();
            }
        }
    }

    //loading file by user input before booting program through terminal
    /**
     * Another way to load file is through terminal with --f flag
     * @param args filename
     */
    public void handleArgumentInput(String[] args){
        fileHandler = new FileHandler(args[1]);
        habitat.processMap(fileHandler);
        displayHabitatScore();
    }

    //method for exiting the program.
    /**
     *  It will save file and append current habitability status to log file.
     */
    private void exitProgram() {
        System.out.println("Enter a filename for saving Martian Land Map");
        System.out.print("> ");
        String filename = scanner.nextLine();

        List<String> saveMap = habitat.convertToLine();
        try {
            fileHandler.saveFile(saveMap, filename);
            meter.appendToHabitabilityLog(saveMap);
        } catch (IOException e) {
            System.out.println("Cannot create file for Martian Land Map.");
        }
        System.out.println("Terminating the mission for now. See you next time.");
    }

    //main menu method
    public void displayMainMenu(){
        System.out.println("Please enter");
        System.out.println("[1] to move Space Robot");
        System.out.println("[2] to move Space Rover");
        System.out.println("[3] to move Martian animals");
        System.out.println("[4] to print the current habitability stats");
        System.out.println("[5] to print the old habitability stats");
        System.out.println("[6] to exit");
    }

    public boolean handleUserMenuInput() {
        System.out.print("> ");
        int choice = scanner.nextInt();
        System.out.println("MainMenu> "+choice);
        scanner.nextLine();
        switch(choice) {
            case 1:
                moveSpaceRobot();
                break;
            case 2:
                moveSpaceRover();
                break;
            case 3:
                moveMartianAnimal();
                break;
            case 4:
                meter.printEntities();
                break;
            case 5:
                habitat.printMarsMap();
                break;
            case 6 :case 0:
                exitProgram();
                scanner.close();
                return false;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }return true;
    }

    public void displayLoadMenu(){
        System.out.println("Please enter");
        System.out.println("[1] to load Martian map from a file");
        System.out.println("[2] to load default Martian map");
    }

    protected void displayHabitatScore(){
        meter.calculateHabitabilityScore(fileHandler);
        meter.countEntities(fileHandler);
        meter.printEntities();
    }

    public void displayRobotMenu(){
        System.out.println("SpaceRobot can move in following directions");
        System.out.println("[1] to move north.");
        System.out.println("[2] to move west.");
        System.out.println("[3] to move east.");
        System.out.println("[4] to move south.");
        System.out.println("[5] to move north-west.");
        System.out.println("[6] to move south-west.");
        System.out.println("[7] to move north-east.");
        System.out.println("[8] to move south-east.");
        System.out.println("[0] to go back to main menu");

        System.out.println("Please enter a direction.");
        System.out.print("> ");
    }

    private void displayRobotSubMenu(){
        System.out.println("Please select");
        System.out.println("[1] to plant a tree");
        System.out.println("[2] to rear cattle");
        System.out.println("[0] to go back to previous menu");
        System.out.print("> ");
    }

    private void displayRobotPlantingMenu(){
        System.out.println("Let's Plant something");
        System.out.println("[1] to plant a potato");
        System.out.println("[2] to plant a tomato");
        System.out.println("[3] to plant an onion");
        System.out.println("[4] to plant an apple tree");
        System.out.println("[5] to plant a banana tree");
        System.out.println("[6] to plant a lily");
        System.out.println("[7] to plant a rose");
        System.out.println("[8] to plant a eucalyptus tree");
        System.out.println("[0] to go back to previous menu");
    }

    private void displayRobotCattleMenu(){
        System.out.println("Let's add some cattle");
        System.out.println("[1] to add a goat");
        System.out.println("[2] to add a sheep");
        System.out.println("[3] to add cow");
        System.out.println("[4] to add a dog");
        System.out.println("[0] to go back to previous menu");
    }

    private void displayRoverMenu(){
        System.out.println("SpaceRover can move in following directions");
        System.out.println("[1] to move north.");
        System.out.println("[2] to move west.");
        System.out.println("[3] to move east.");
        System.out.println("[4] to move south.");
        System.out.println("[5] to move north-west.");
        System.out.println("[6] to move south-west.");
        System.out.println("[7] to move north-east.");
        System.out.println("[8] to move south-east.");
        System.out.println("[0] to go back to main menu");

        System.out.println("Please enter a direction.");
        System.out.print("> ");
    }

    private void displayMartianAnimalMenu(){
        System.out.println("MartianAnimal can move in following directions");
        System.out.println("[1] to move north.");
        System.out.println("[2] to move west.");
        System.out.println("[3] to move east.");
        System.out.println("[4] to move south.");
        System.out.println("[5] to move north-west.");
        System.out.println("[6] to move south-west.");
        System.out.println("[7] to move north-east.");
        System.out.println("[8] to move south-east.");
        System.out.println("[0] to go back to main menu");

        System.out.println("Please enter a direction.");
        System.out.print("> ");
    }

    //main movement method for Movable entities
    private void moveSpaceRobot() {
        SpaceRobot selectedRobot = selectRobotFromList();
        if (selectedRobot == null) {
            System.out.println("There is no robot in Mars!");
            return;
        }
        moveEntity(selectedRobot);
    }

    private void moveSpaceRover() {
        SpaceRover selectedRover = selectRoverFromList();
        if (selectedRover == null){
            System.out.println("There is no rover in Mars!");
            return;
        }
        moveEntity(selectedRover);
    }

    private void moveMartianAnimal() {
        MartianAnimal selectedMartianAnimal = selectMartianAnimalFromList();
        if (selectedMartianAnimal == null) {
            System.out.println("There is no Martian animal in Mars!");
            return;
        }
        else if (selectedMartianAnimal instanceof Heebie){
            Heebie heebie = (Heebie) selectedMartianAnimal;
            moveEntity(heebie);
        } else if (selectedMartianAnimal instanceof Jeebie) {
            Jeebie jeebie = (Jeebie) selectedMartianAnimal;
            moveEntity(jeebie);
        }
    }

    //selecting Movable entities

    /**
     * Method is for selecting a robot from robots in the habitat entity List
     * @return a robot selected by user
     */
    private SpaceRobot selectRobotFromList() {
        List<SpaceRobot> robots = habitat.filterEntities(SpaceRobot.class);
        int pickRobot;
        while (true) {
            habitat.listEntity(SpaceRobot.class, "Space Robot");
            System.out.print("> ");
            try {
                pickRobot = scanner.nextInt();
                scanner.nextLine();
                //System.out.println("Selected Robot: "+pickRobot);
                return robots.get(pickRobot - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such robot existed! Please reselect.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number from the list.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Method is for selecting a rover from rovers in the habitat entity List
     * @return a rover selected by user
     */
    private SpaceRover selectRoverFromList() {
        List<SpaceRover> rovers = habitat.filterEntities(SpaceRover.class);
        int pickRover;
        while (true) {
            habitat.listEntity(SpaceRover.class, "Space Rover");
            System.out.print("> ");
            try {
                pickRover = scanner.nextInt();
                scanner.nextLine();
                return rovers.get(pickRover - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such rover existed! Please reselect.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number from the list.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Method is for selecting a Martian animal from animal in the habitat entity List
     * @return a Martian animal selected by user
     */
    private MartianAnimal selectMartianAnimalFromList() {
        List<MartianAnimal> martianAnimals = habitat.filterEntities(MartianAnimal.class);
        int pickmartianAnimals;
        while (true) {
            habitat.listEntity(MartianAnimal.class, "Martian animal");
            System.out.print("> ");
            try {
                pickmartianAnimals = scanner.nextInt();
                scanner.nextLine();
                return martianAnimals.get(pickmartianAnimals - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such martian animal existed! Please reselect.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number from the list.");
                scanner.nextLine();
            }
        }
    }

    //moving the selected entities
    /**
     * Method takes entity that implements Movable interface and check for entity type.
     * Entity movement flow is assign based on entity type
     * @param entity
     */
    private void moveEntity(Movable entity) {
        int direction = 0;
        boolean loopMovement;
        while (true) {
            //SpaceRobot movement logic
            if (entity instanceof SpaceRobot) {
                displayRobotMenu();
                try {
                    direction = scanner.nextInt();
                    //System.out.println("Direction: "+direction);
                    scanner.nextLine();
                    loopMovement = executeEntityMovement(entity, direction);
                    if (!loopMovement) break;

                    //check if SpaceRobot does not collide with any entities
                    if(!(((SpaceRobot) entity).isRecentlyCollided())) {
                        //check if left position is empty
                        if (habitat.isLeftOfPositionEmpty(entity.getX(), entity.getY())) {
                            handleRobotAction(entity);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Out of bound. Please reselect direction.");
                    System.out.println(e.getMessage());
                }
            //SpaceRover movement logic
            }else if (entity instanceof SpaceRover) {
                displayRoverMenu();
                try {
                    direction = scanner.nextInt();
                    //System.out.println("Direction: "+direction);
                    scanner.nextLine();
                    loopMovement = executeEntityMovement(entity, direction);
                    if (!loopMovement) break;
                } catch (Exception e) {
                    System.out.println("Out of bound. Please reselect direction.");
                }
            //MartianAnimal movement logic
            } else if (entity instanceof MartianAnimal) {
                displayMartianAnimalMenu();
                try {
                    direction = scanner.nextInt();
                    //System.out.println("Direction: "+direction);
                    scanner.nextLine();
                    loopMovement = executeEntityMovement(entity, direction);
                    if (!loopMovement) break;

                } catch (Exception e) {
                    System.out.println("Out of bound. Please reselect direction.");
                }
            }
            habitat.updateEntityPosition(entity);
            habitat.printMarsMap();
            System.out.println("");
        }
    }

    //allowed direction for movement
    /**
     * All the direction available for moving entity
     * @param entity entity that needs to move
     * @param direction movement direction
     * @return true if valid move, false if otherwise
     */
    private boolean executeEntityMovement(Movable entity, int direction) {
        switch (direction) {
            case 1:
                entity.moveNorth(habitat, meter);
                break;
            case 2:
                entity.moveWest(habitat, meter);
                break;
            case 3:
                entity.moveEast(habitat, meter);
                break;
            case 4:
                entity.moveSouth(habitat, meter);
                break;
            case 5:
                entity.moveNorthWest(habitat, meter);
                break;
            case 6:
                entity.moveSouthWest(habitat, meter);
                break;
            case 7:
                entity.moveNorthEast(habitat, meter);
                break;
            case 8:
                entity.moveSouthEast(habitat, meter);
                break;
            case 0:
                return false;
            default:
                System.out.println("Invalid direction choice. Please enter again.");
                return true;
        }
        return true;
    }

    /**
     * handling Robot action if left position empty after moving
     * @param entity entity that needs to move
     */
    private void handleRobotAction(Movable entity){
        int action;
        SpaceRobot robot = (SpaceRobot)entity;
        while(true) {
            displayRobotSubMenu();
            action = scanner.nextInt();
            //System.out.println("Action: "+action);
            scanner.nextLine();
            switch (action) {
                case 1:
                    boolean loopPlantingMenu = true;
                    while (loopPlantingMenu) {
                        displayRobotPlantingMenu();
                        loopPlantingMenu = robot.handleVegetationPlanting(habitat, meter);
                    }
                    break;
                case 2:
                    boolean loopCattleMenu = true;
                    while (loopCattleMenu) {
                        displayRobotCattleMenu();
                        loopCattleMenu = robot.handleCattlePlacement(habitat, meter);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please reselect.");
                    break;
            }
        }
    }


}
