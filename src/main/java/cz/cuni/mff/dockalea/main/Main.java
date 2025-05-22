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
import cz.cuni.mff.dockalea.items.Weapon;

import java.util.Hashtable;
import java.util.List;

/**
 * Main entry point of the game. Manages the game loop and player interactions with rooms and enemies.
 */
public class Main {
    private static boolean isRunning = true;
    private static Player player = new Player(100, 1, 0);
    private static Hashtable<String, Room> map;
    private static String currentRoom;

    /**
     * Loads the map and starts the game loop.
     *
     * @param args Command line arguments (unused).
     */
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

    /**
     * The core game loop. Handles room logic, combat, and player input.
     */
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
            int input = InputHandler.getNextChoice(5, "Choice: ");

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
                    manageInventory();
                    break;
                case 5:
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

    /**
     * Spawns an enemy in the given room based on player level and room type.
     *
     * @param room The room to spawn the enemy in.
     */
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

    /**
     * Allows the player to explore a room and possibly find items.
     *
     * @param room The room being explored.
     */
    private static void exploreRoom(Room room) {
        if (room.isExplored()) {
            System.out.println("You have already explored this room thoroughly.");
            return;
        }

        System.out.println("You explore the room carefully...");

        // 60% chance to find item
        if (Math.random() < 0.6) {
            Item foundItem = ItemGenerator.generateRandomItem(player.getLevel());
            System.out.println("You found a " + foundItem.getName() + "!");
            player.getInventory().addItem(foundItem);
        } else {
            System.out.println("You search thoroughly but find nothing of value.");
        }

        room.setExplored(true);
        InputHandler.waitForEnter();
    }

    /**
     * Displays and handles inventory management options.
     */
    private static void manageInventory() {
        System.out.println("\n=== INVENTORY MANAGEMENT ===");
        System.out.println("1. Use/Equip Item");
        System.out.println("2. Discard Item");
        System.out.println("3. Back to main menu");

        int choice = InputHandler.getNextChoice(3, "Choose action: ");

        switch (choice) {
            case 1:
                useEquipItem();
                break;
            case 2:
                discardItem();
                break;
            case 3:
                return;
        }
    }

    /**
     * Allows the player to use or equip an item from the inventory.
     */
    private static void useEquipItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty!");
            InputHandler.waitForEnter();
            return;
        }

        System.out.println("\nYour items:");
        List<Item> items = player.getInventory().getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ") - " + item.getDescription());
        }
        System.out.println((items.size() + 1) + ". Cancel");

        int choice = InputHandler.getNextChoice(items.size() + 1, "Choose item to use/equip: ");

        if (choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);
            selectedItem.use(player);

            // Remove item from inventory if it's consumable (not a weapon)
            if (!(selectedItem instanceof Weapon)) {
                player.getInventory().removeItem(selectedItem);
            }
        }
        InputHandler.waitForEnter();
    }

    /**
     * Allows the player to discard an item from the inventory.
     */
    private static void discardItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty!");
            InputHandler.waitForEnter();
            return;
        }

        System.out.println("\nYour items:");
        List<Item> items = player.getInventory().getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ")");
        }
        System.out.println((items.size() + 1) + ". Cancel");

        int choice = InputHandler.getNextChoice(items.size() + 1, "Choose item to discard: ");

        if (choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);
            player.getInventory().removeItem(selectedItem);
            System.out.println("Discarded " + selectedItem.getName() + ".");
        }
        InputHandler.waitForEnter();
    }

    /**
     * Handles movement to a different room based on the player's choice.
     */
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
