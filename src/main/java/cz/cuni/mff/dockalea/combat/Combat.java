package cz.cuni.mff.dockalea.combat;

import cz.cuni.mff.dockalea.entities.Enemy;
import cz.cuni.mff.dockalea.entities.Player;
import cz.cuni.mff.dockalea.iohandling.InputHandler;
import cz.cuni.mff.dockalea.items.Item;

import java.util.List;

public class Combat {
    private final Player player;
    private final Enemy enemy;
    private boolean combatActive;
    private boolean playerGuarding;

    public Combat(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.combatActive = true;
        this.playerGuarding = false;
    }

    public boolean startCombat() {
        System.out.println("\n=== COMBAT STARTED ===");
        System.out.println("You encounter a " + enemy.getName() + " (Level " + enemy.getLevel() + ")!");
        System.out.println("Enemy Health: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
        InputHandler.waitForEnter();

        while (combatActive && player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
            // Reset guard state after enemy turn
            playerGuarding = false;
        }

        return resolveCombat();
    }

    private void playerTurn() {
        System.out.println("\n--- Your Turn ---");
        System.out.println("Your Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("Enemy Health: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());

        System.out.println("\nChoose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Guard");
        System.out.println("3. Use Item");
        if (!enemy.isBoss()) {
            System.out.println("4. Flee");
        }

        int maxChoice = enemy.isBoss() ? 3 : 4;
        int choice = InputHandler.getNextChoice(maxChoice, "Your choice: ");

        switch (choice) {
            case 1:
                playerAttack();
                break;
            case 2:
                playerGuard();
                break;
            case 3:
                useItem();
                break;
            case 4:
                if (!enemy.isBoss()) {
                    flee();
                }
                break;
        }
    }

    private void playerAttack() {
        int damage = player.attack();
        enemy.takeDamage(damage);
        System.out.println("You deal " + damage + " damage to " + enemy.getName() + "!");

        if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + " is defeated!");
        }
    }

    private void playerGuard() {
        System.out.println("You prepare to guard against the next attack!");
        playerGuarding = true;
    }

    private void useItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("You have no items to use!");
            return;
        }

        System.out.println("\nYour items:");
        List<Item> items = player.getInventory().getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ")");
        }
        System.out.println((items.size() + 1) + ". Cancel");

        int choice = InputHandler.getNextChoice(items.size() + 1, "Choose item: ");

        if (choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);
            selectedItem.use(player);
            player.getInventory().removeItem(selectedItem);
        }
    }

    private void flee() {
        // 70% chance to flee successfully
        if (Math.random() < 0.7) {
            System.out.println("You successfully fled from combat!");
            combatActive = false;
        } else {
            System.out.println("You failed to flee!");
        }
    }

    private void enemyTurn() {
        System.out.println("\n--- Enemy Turn ---");
        int damage = enemy.attack();

        if (playerGuarding) {
            int guardedSuccesfully = player.defend();
            if (guardedSuccesfully == 0) {
                damage = 0;
            } else {
                damage = damage / 2;
            }
        }

        player.takeDamage(damage);
        if (damage > 0) {
            System.out.println("You take " + damage + " damage!");
        }
        InputHandler.waitForEnter();

        if (!player.isAlive()) {
            System.out.println("You have been defeated!");
        }
    }

    private boolean resolveCombat() {
        player.clearTemporaryBoosts();

        if (!player.isAlive()) {
            InputHandler.printSeparator();
            System.out.println("GAME OVER!");
            InputHandler.printSeparator();
            InputHandler.waitForEnter();
            return false;
        }

        if (!enemy.isAlive()) {
            System.out.println("Victory!");
            player.addXp(enemy.getXpReward());

            Item droppedItem = enemy.dropItem();
            if (droppedItem != null) {
                System.out.println(enemy.getName() + " dropped " + droppedItem.getName() + "!");
                player.getInventory().addItem(droppedItem);
            }

            if (enemy.isBoss()) {
                System.out.println("\nCongratulations! You have defeated the boss " + enemy.getName() + "!");
                System.out.println("1. Continue playing");
                System.out.println("2. Quit game");

                int choice = InputHandler.getNextChoice(2, "Would you like to continue?");
                if (choice == 2) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }

            InputHandler.waitForEnter();
            return true;
        }

        return true;
    }
}
