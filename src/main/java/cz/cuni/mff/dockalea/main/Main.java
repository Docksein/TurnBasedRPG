package cz.cuni.mff.dockalea.main;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Map;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.iohandling.InputHandler;

import java.util.Hashtable;

public class Main {
    private static boolean isRunning = true;
    private static final int sepLength = 30;
    private static Player player = new Player(100, 1, 0);
    private static Hashtable<String, Room> map;

    public static void main(String[] args) {
        map = Map.loadMapFromTxtFile("WorldMap.txt");
        //map.forEach((k,v) -> System.out.println(v));
    }

    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = InputHandler.getNextChoice(3, "Choice: ");
            if (input == 1) {
                nextAction();
            } else if (input == 2) {
                printCharacterStats();
            } else {
                isRunning = false;
            }
        }
    }

    public static void nextAction() {

    }

    public static void printMenu() {
        System.out.println("Choose an action:");
        InputHandler.printSeparator(sepLength);
        System.out.println("(1) Continue");
        System.out.println("(2) Character info");
        System.out.println("(3) Exit");
    }

    public static void printCharacterStats() {
        InputHandler.printSeparator(sepLength);
        System.out.println("CHARACTER INFO:");
        InputHandler.printSeparator(sepLength);
        System.out.println("Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("XP: " + player.getXp());
        System.out.println("Level: " + player.getLevel());
        System.out.println();
    }

}