package cz.cuni.mff.dockalea.main;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Map;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.iohandling.InputHandler;

import java.util.Hashtable;

public class Main {
    private static boolean isRunning = true;
    private static Player player = new Player(100, 1, 0);
    private static Hashtable<String, Room> map;
    public static void main(String[] args) {
        map = Map.loadMapFromTxtFile("WorldMap.txt");
        //map.forEach((k,v) -> System.out.println(v));
    }

    public static void gameLoop() {
        while (isRunning) {
            InputHandler.printMenu();
            int input = InputHandler.getNextChoice(3, "Choice: ");
            if (input == 1) {
                nextAction();
            } else if (input == 2) {
                InputHandler.printCharacterStats(player);
            } else {
                isRunning = false;
            }
        }
    }

    public static void nextAction() {

    }

}