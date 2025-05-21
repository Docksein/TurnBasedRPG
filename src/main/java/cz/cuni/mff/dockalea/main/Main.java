package cz.cuni.mff.dockalea.main;

import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Map;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.environment.RoomData;
import cz.cuni.mff.dockalea.iohandling.InputHandler;

import java.util.Hashtable;

public class Main {
    private static boolean isRunning = true;
    private static Player player = new Player(100, 1, 0);
    private static Hashtable<String, Room> map;
    private static String currentRoom;

    public static void main(String[] args) {
        // Load Rooms and check if the current one is present
        RoomData mapData = Map.loadMapFromTxtFile("WorldMap.txt");
        map = mapData.rooms;
        currentRoom = mapData.currentRoomName;
        if (map.containsKey(currentRoom)) {
            System.out.println(currentRoom);
            gameLoop();
        }
        else
            System.out.println("Error, Room: " + currentRoom + " is not in the map");
    }

    public static void gameLoop() {
        while (isRunning) {
            InputHandler.printMenu();
            int input = InputHandler.getNextChoice(3, "Choice: ");
            if (input == 1) {
                move();
            } else if (input == 2) {
                InputHandler.printCharacterStats(player);
            } else {
                isRunning = false;
            }
        }
    }

    public static void nextAction() {

    }

    public static void move() {
        Room currentRoomObj = map.get(currentRoom);
        int choice = InputHandler.moveInCurrentRoom(currentRoomObj);
        int directionCount = 0;
        String nextRoom = null;

        if (currentRoomObj.North != null) {
            directionCount++;
            if (choice == directionCount) {
                nextRoom = currentRoomObj.North;
            }
        }

        if (currentRoomObj.South != null && nextRoom == null) {
            directionCount++;
            if (choice == directionCount) {
                nextRoom = currentRoomObj.South;
            }
        }

        if (currentRoomObj.East != null && nextRoom == null) {
            directionCount++;
            if (choice == directionCount) {
                nextRoom = currentRoomObj.East;
            }
        }

        if (currentRoomObj.West != null && nextRoom == null) {
            directionCount++;
            if (choice == directionCount) {
                nextRoom = currentRoomObj.West;
            }
        }

        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom);
        }
    }

}