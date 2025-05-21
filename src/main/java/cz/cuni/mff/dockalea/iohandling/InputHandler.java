package cz.cuni.mff.dockalea.iohandling;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Room;

import java.util.Scanner;

public class InputHandler {
    private static Scanner input_scanner = new Scanner(System.in);
    public static int sepLength;

    public static int getNextChoice(int actionChoices, String prompt){
        int choice = -1;

        System.out.println(prompt);
        while (choice < 1 || choice > actionChoices){
            try {
                choice = Integer.parseInt(input_scanner.next());
            } catch (NumberFormatException e) {
                choice = - 1;
                System.out.println("Please select a choice from 1 to " + actionChoices);
            }
        }

        return choice;
    }

    public static void printCharacterStats(Player player) {
        InputHandler.printSeparator(sepLength);
        System.out.println("CHARACTER INFO:");
        InputHandler.printSeparator(sepLength);
        System.out.println("Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("XP: " + player.getXp());
        System.out.println("Level: " + player.getLevel());
        System.out.println();
    }


    public static void printMenu() {
        System.out.println("Choose an action:");
        printSeparator(sepLength);
        System.out.println("(1) Continue");
        System.out.println("(2) Character info");
        System.out.println("(3) Exit");
    }


    public static void printSeparator(int length) {
        for (int i = 0; i < length; ++i) {
            System.out.print('=');
        }
        System.out.println();
    }

    public static void printRoom(Room r){
        System.out.println(r.Name);
        System.out.println(r.Description);
        int i = 0;
        if (r.North != null){
            ++i;
            System.out.println(i + r.North);
        }
        if (r.South != null) {
            ++i;
            System.out.println(i + r.South);
        }
        if (r.East != null) {
            ++i;
            System.out.println(r.East);
        }
        if (r.West != null) {
            ++i;
            System.out.println(r.West);
        }
    }
}
