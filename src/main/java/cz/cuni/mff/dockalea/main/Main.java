package cz.cuni.mff.dockalea.main;

import cz.cuni.mff.dockalea.combat.Combat;
import cz.cuni.mff.dockalea.entities.Enemy;
import cz.cuni.mff.dockalea.entities.EnemyGenerator;
import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.environment.Map;
import cz.cuni.mff.dockalea.environment.Room;
import cz.cuni.mff.dockalea.environment.RoomData;
import cz.cuni.mff.dockalea.iohandling.InputHandler;
import cz.cuni.mff.dockalea.items.Item;
import cz.cuni.mff.dockalea.items.ItemGenerator;

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
            Room r = map.get(currentRoom);
            System.out.println("Current location: " + currentRoom);
            InputHandler.printSeparator();
            System.out.println(r.Description);
            gameLoop();
        } else {
            System.out.println("Error, Room: " + currentRoom + " is not in the map");
        }
    }

    public static void gameLoop() {
        while (isRunning && player.isAlive()) {
            Room room = map.get(currentRoom);

            if (!room.isVisited() && room.getCurrentEnemy() == null) {
                spawnEnemy(room);
            }

            if (room.getCurrentEnemy() != null) {
                Combat combat = new Combat(player, room.getCurrentEnemy());
                boolean combatResult = combat.startCombat();

                if (!combatResult) {
                    isRunning = false;
                    break;
                }

                room.setCurrentEnemy(null);
            }

            room.setVisited(true);

            InputHandler.printRoomMenu();
            int input = InputHandler.getNextChoice(4, "Choice: ");

            switch (input) {
                case 1:
                    move();
                    break;
                case 2:
                    exploreRoom(room);
                    break;
                case 3:
                    InputHandler.printCharacterStats(player);
                    break;
                case 4:
                    isRunning = false;
                    break;
            }
        }

        if (!player.isAlive()) {
            System.out.println("Game Over! Your adventure ends here.");
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private static void spawnEnemy(Room room) {
        boolean isBoss = room.isBossRoom();
        Enemy enemy = EnemyGenerator.generateEnemy(player.getLevel(), isBoss);
        room.setCurrentEnemy(enemy);

        if (isBoss) {
            System.out.println("A powerful boss appears: " + enemy.getName() + "!");
        } else {
            System.out.println("An enemy appears: " + enemy.getName() + "!");
        }
    }

    private static void exploreRoom(Room room) {
        if (room.isExplored()) {
            System.out.println("You have already explored this room thoroughly.");
            return;
        }

        System.out.println("You explore the room carefully...");

        // 40% chance to find item
        if (Math.random() < 0.4) {
            Item foundItem = ItemGenerator.generateRandomItem(player.getLevel());
            System.out.println("You found a " + foundItem.getName() + "!");
            player.getInventory().addItem(foundItem);
        } else {
            System.out.println("You search thoroughly but find nothing of value.");
        }

        room.setExplored(true);
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
