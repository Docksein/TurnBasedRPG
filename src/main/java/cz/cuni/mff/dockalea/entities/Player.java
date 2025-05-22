package cz.cuni.mff.dockalea.entities;

import cz.cuni.mff.dockalea.items.Inventory;
import cz.cuni.mff.dockalea.items.Weapon;

/**
 * Represents the player character in the game.
 *
 * <p>The player has experience, a level-based inventory, and can perform attacks,
 * equip weapons, heal, and gain temporary boosts during combat.</p>
 */
public class Player extends Entity {
    private int xp;
    private final Inventory inventory;
    private int temporaryDamageBoost;
    private final int maxInventorySize = 10;

    /**
     * Constructs a player with given health, level, inventory and XP.
     * Inventory has 10 slots.
     * Temporary damage boost is used for Strength Potions during combat.
     *
     * @param maxHealth the maximum health of the player
     * @param level the starting level
     * @param xp the initial experience points
     */
    public Player(int maxHealth, int level, int xp) {
        super(maxHealth, level);
        this.xp = xp;
        this.inventory = new Inventory(maxInventorySize);
        this.temporaryDamageBoost = 0;
    }

    public int getXp() { return xp; }
    public Inventory getInventory() { return inventory; }

    /**
     * Simple leveling system.
     * Increases the player's XP and levels up if the threshold is reached.
     *
     * @param amount the amount of XP to gain
     */
    public void addXp(int amount) {
        xp += amount;
        System.out.println("Gained " + amount + " XP! Total XP: " + xp);

        int newLevel = 1 + (xp / 100);
        if (newLevel > level) {
            level = newLevel;
            int healthIncrease = 20;
            maxHealth += healthIncrease;
            currentHealth = maxHealth;
            System.out.println("Level up! You are now level " + level + "!");
            System.out.println("Max health increased by " + healthIncrease + "!");
        }
    }

    /**
     * Restores health to the player, up to their maximum.
     *
     * @param amount the amount of health to restore
     */
    public void heal(int amount) {
        int oldHealth = currentHealth;
        currentHealth = Math.min(maxHealth, currentHealth + amount);
        System.out.println("Healed " + (currentHealth - oldHealth) + " health! Current: " + currentHealth + "/" + maxHealth);
    }

    /**
     * Equips a new weapon from the inventory.
     *
     * @param weapon the weapon to equip
     */
    public void equipWeapon(Weapon weapon) {
        inventory.equipWeapon(weapon);
    }

    /**
     * Adds a temporary damage boost that applies during the current encounter.
     *
     * @param boost the damage boost to apply
     */
    public void addTemporaryDamageBoost(int boost) {
        temporaryDamageBoost += boost;
    }

    /**
     * Clears all temporary damage boosts.
     */
    public void clearTemporaryBoosts() {
        temporaryDamageBoost = 0;
    }

    /**
     * Performs an attack using the equipped weapon and any damage boosts.
     *
     * @return the total damage dealt
     */
    @Override
    public int attack() {
        int weaponDamage = inventory.getEquippedWeapon().getDamage();
        int totalDamage = weaponDamage + temporaryDamageBoost;
        System.out.println("You attack with " + inventory.getEquippedWeapon().getName() + "!");
        return totalDamage;
    }

    /**
     * Performs a defensive action with a chance to fully block.
     * 50% chance to block completely.
     *
     * @return 0 if blocked, 1 if not blocked
     */
    @Override
    public int defend() {
        if (Math.random() < 0.5) {
            System.out.println("You successfully blocked the attack!");
            return 0;
        } else {
            System.out.println("You couldn't block the entire attack.");
            return 1;
        }
    }
}
