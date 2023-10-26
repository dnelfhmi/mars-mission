/**
 * MarsHabitatApplication class is the entry point of the code and the program.
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

import util.MainMenu;
import util.ScannerSingleton;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class MarsHabitatApplication {
    public static Scanner keyboard;
    private static MainMenu menu;

    //constructor instantiate scanner object
    public MarsHabitatApplication() {
        keyboard = ScannerSingleton.getScannerInstance();
        menu = new MainMenu();
    }

    public static void main(String[] args) throws FileNotFoundException {

        MarsHabitatApplication engine = new MarsHabitatApplication();
        engine.startProgram(args);
        //TODO: implement your code here
    }

    /**
     * This method prints the starting welcome message. Do not change this code
     */
    private void displayMessage() {
        System.out.println("         __\n" +
                " _(\\    |@@|\n" +
                "(__/\\__ \\--/ __\n" +
                "   \\___|----|  |   __\n" +
                "       \\ }{ /\\ )_ / _\\\n" +
                "       /\\__/\\ \\__O (_COMP90041\n" +
                "      (--/\\--)    \\__/\n" +
                "      _)(  )(_\n" +
                "     `---''---`");
        System.out.println(
                " /$$      /$$ /$$                    /$$                           /$$      /$$                              \n" +
                        "| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              \n" +
                        "| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$\n" +
                        "| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/\n" +
                        "| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$      | $$  $$$| $$  /$$$$$$$| $$  \\__/|  $$$$$$ \n" +
                        "| $$\\  $ | $$| $$ \\____  $$\\____  $$| $$| $$  | $$| $$  | $$      | $$\\  $ | $$ /$$__  $$| $$       \\____  $$\n" +
                        "| $$ \\/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \\/  | $$|  $$$$$$$| $$       /$$$$$$$/\n" +
                        "|__/     |__/|__/|_______/|_______/ |__/ \\______/ |__/  |__/      |__/     |__/ \\_______/|__/      |_______/ ");

        System.out.println();
    }

    //control flow for menu display and selection
    private void startProgram(String[] args) {
        boolean loop = true;
        int argsLength = 2;
        int firstArgs = 0;
        displayMessage();
        if (args.length == argsLength && "--f".equals(args[firstArgs])) {
            menu.handleArgumentInput(args);
        } else {
            menu.displayLoadMenu();
            menu.handleUserLoadFileInput();
        }
        while (loop) {
            menu.displayMainMenu();
            loop = menu.handleUserMenuInput();
        }
    }
}