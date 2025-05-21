package cz.cuni.mff.dockalea.entities;

import cz.cuni.mff.dockalea.items.Inventory;
import cz.cuni.mff.dockalea.items.Weapon;

public class Player extends Entity {
    private int xp;
    private final Inventory inventory;
    private int temporaryDamageBoost;
    private final int maxInventorySize = 10;

    public Player(int maxHealth, int level, int xp) {
        super(maxHealth, level);
        this.xp = xp;
        this.inventory = new Inventory(maxInventorySize); // 10 item slots
        this.temporaryDamageBoost = 0;
    }

    public int getXp() { return xp; }
    public Inventory getInventory() { return inventory; }

    public void addXp(int amount) {
        xp += amount;
        System.out.println("Gained " + amount + " XP! Total XP: " + xp);

        // Simple leveling system
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

    public void heal(int amount) {
        int oldHealth = currentHealth;
        currentHealth = Math.min(maxHealth, currentHealth + amount);
        System.out.println("Healed " + (currentHealth - oldHealth) + " health! Current: " + currentHealth + "/" + maxHealth);
    }

    public void equipWeapon(Weapon weapon) {
        inventory.equipWeapon(weapon);
    }

    public void addTemporaryDamageBoost(int boost) {
        temporaryDamageBoost += boost;
    }

    public void clearTemporaryBoosts() {
        temporaryDamageBoost = 0;
    }

    @Override
    public int attack() {
        int weaponDamage = inventory.getEquippedWeapon().getDamage();
        int totalDamage = weaponDamage + temporaryDamageBoost;
        System.out.println("You attack with " + inventory.getEquippedWeapon().getName() + "!");
        return totalDamage;
    }

    @Override
    public int defend() {
        if (Math.random() < 0.5) { // 50% chance to block completely
            System.out.println("You successfully blocked the attack!");
            return 0;
        } else {
            System.out.println("You couldn't block the entire attack.");
            return 1; // Indicates partial block
        }
    }
}