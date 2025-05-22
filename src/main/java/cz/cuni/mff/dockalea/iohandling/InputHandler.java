package cz.cuni.mff.dockalea.iohandling;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.items.Item;

import java.util.Scanner;

/**
 * Handles user input and displays menus and prompts to the player.
 * Provides utility methods for user interactions during the game.
 */
public class InputHandler {
    /**
     * Scanner used to receive user input from the console.
     */
    private static Scanner input_scanner = new Scanner(System.in);

    /**
     * Length of the visual separator line printed in menus.
     */
    public static int sepLength = 40;


    /**
     * Prompts the user to make a choice between numbered options.
     *
     * @param actionChoices The number of available choices.
     * @param prompt        The text prompt to display before input.
     * @return The validated user choice.
     */
    public static int getNextChoice(int actionChoices, String prompt) {
        int choice = -1;

        System.out.println(prompt);
        while (choice < 1 || choice > actionChoices) {
            try {
                System.out.println("Please select a number from 1 to " + actionChoices);
                choice = Integer.parseInt(input_scanner.next());
            } catch (NumberFormatException e) {
                choice = -1;
                System.out.println("Wrong input. Please select a number from 1 to " + actionChoices);
            }
        }

        return choice;
    }

    /**
     * Displays the character's current stats including health, XP, level, and inventory.
     *
     * @param player The player whose stats are displayed.
     */
    public static void printCharacterStats(Player player) {
        printSeparator();
        System.out.println("CHARACTER INFO:");
        printSeparator();
        System.out.println("Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("XP: " + player.getXp());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Equipped Weapon: " + player.getInventory().getEquippedWeapon().getName() +
                " (Damage: " + player.getInventory().getEquippedWeapon().getDamage() + ")");
        System.out.println("Inventory: " + player.getInventory().getSize() + "/" + player.getInventory().getMaxSize());

        if (!player.getInventory().isEmpty()) {
            System.out.println("Items:");
            for (Item item : player.getInventory().getItems()) {
                System.out.println("  - " + item.getName() + " (" + item.getType() + ")");
            }
        }
        System.out.println();
        waitForEnter();
    }

    /**
     * Waits for the user to press Enter before continuing.
     */
    public static void waitForEnter() {
        System.out.println("Press Enter to continue...");
        input_scanner.nextLine();
    }

    /**
     * Displays the menu of available actions in a room.
     */
    public static void printRoomMenu() {
        System.out.println("\nChoose an action:");
        printSeparator();
        System.out.println("1. Move to another room");
        System.out.println("2. Explore current room");
        System.out.println("3. Character info");
        System.out.println("4. Manage inventory");
        System.out.println("5. Exit");
    }

    /**
     * Prints a separator line of '=' characters.
     */
    public static void printSeparator() {
        for (int i = 0; i < sepLength; ++i) {
            System.out.print('=');
        }
        System.out.println();
    }

    /**
     * Displays the available directions to move in the current room and gets the player's choice.
     *
     * @param currentRoomObj The current room object.
     * @return The chosen direction or stay option.
     */
    public static int moveInCurrentRoom(Room currentRoomObj) {
        if (currentRoomObj == null) {
            System.out.println("Error: Current room not found in map!");
            return 0;
        }

        printRoom(currentRoomObj);
        System.out.println("\nAvailable directions:");

        int directionCount = 0;

        if (currentRoomObj.North != null) {
            directionCount++;
            System.out.println(directionCount + ". North (to " + currentRoomObj.North + ")");
        }

        if (currentRoomObj.South != null) {
            directionCount++;
            System.out.println(directionCount + ". South (to " + currentRoomObj.South + ")");
        }

        if (currentRoomObj.East != null) {
            directionCount++;
            System.out.println(directionCount + ". East (to " + currentRoomObj.East + ")");
        }

        if (currentRoomObj.West != null) {
            directionCount++;
            System.out.println(directionCount + ". West (to " + currentRoomObj.West + ")");
        }

        directionCount++;
        System.out.println(directionCount + ". Stay here");

        return getNextChoice(directionCount, "Where you want to go: ");
    }

    /**
     * Prints the name, description, and available directions of the specified room to the console.
     *
     * @param currentRoomObj The {@code Room} object whose details are to be printed.
     */
    public static void printRoom(Room currentRoomObj){
        System.out.println("\n=== " + currentRoomObj.Name + " ===");
        System.out.println(currentRoomObj.Description);

    }
}
