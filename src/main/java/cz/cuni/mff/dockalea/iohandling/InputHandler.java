package cz.cuni.mff.dockalea.iohandling;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.items.Item;

import java.util.Scanner;

public class InputHandler {
    private static Scanner input_scanner = new Scanner(System.in);
    public static int sepLength = 40;

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
    }

    public static void printRoomMenu() {
        System.out.println("\nChoose an action:");
        printSeparator();
        System.out.println("1. Move to another room");
        System.out.println("2. Explore current room");
        System.out.println("3. Character info");
        System.out.println("4. Exit");
    }

    public static void printSeparator() {
        for (int i = 0; i < sepLength; ++i) {
            System.out.print('=');
        }
        System.out.println();
    }

    public static int moveInCurrentRoom(Room currentRoomObj) {
        if (currentRoomObj == null) {
            System.out.println("Error: Current room not found in map!");
            return 0;
        }

        System.out.println("\n=== " + currentRoomObj.Name + " ===");
        System.out.println(currentRoomObj.Description);
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
}