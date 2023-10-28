/**
 * Singleton class to provide global access to a Scanner instance.
 * This class manages a single static Scanner instance that can be accessed
 * globally to read user input.
 *
 * The constructor is private to prevent instantiation.
 *
 * The getScannerInstance() method returns the singleton Scanner instance,
 * creating it if it doesn't yet exist.
 *
 * @author Daniel Fahmi, mmdnasrein@student.unimelb.edu.au, 1497325
 */

package util;

import java.util.Scanner;

public class ScannerSingleton {

    private static Scanner scannerInstance;

    /**
     * This constructor will not instantiate anything to prevent multiple Scanner object creation.
     */
    private ScannerSingleton(){
    }

    /**
     * If no scanner object, create one. If scanner exist, use the existing one.
     * @return existing scanner
     */
    public static Scanner getScannerInstance(){
        if(scannerInstance == null){
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }

    /**
     * Close the existing scanner
     */
    public static void closeScannerInstance(){
        if(scannerInstance != null){
            scannerInstance.close();
            scannerInstance = null;
        }
    }

}