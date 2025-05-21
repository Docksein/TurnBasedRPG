package cz.cuni.mff.dockalea.entities;

import cz.cuni.mff.dockalea.items.Item;
import cz.cuni.mff.dockalea.items.ItemGenerator;

public class Enemy extends Entity {
    private final String name;
    private final boolean isBoss;
    private final int xpReward;

    public Enemy(String name, int level, boolean isBoss) {
        super(calculateHealth(level, isBoss), level);
        this.name = name;
        this.isBoss = isBoss;
        this.xpReward = level * (isBoss ? 50 : 10);
    }

    private static int calculateHealth(int level, boolean isBoss) {
        int baseHealth = 30 + (level * 10);
        return isBoss ? baseHealth * 2 : baseHealth;
    }

    public String getName() { return name; }
    public boolean isBoss() { return isBoss; }
    public int getXpReward() { return xpReward; }

    @Override
    public int attack() {
        // Random choice between light and hard attack
        boolean isHardAttack = Math.random() < 0.3; // 30% chance for hard attack
        int baseDamage = 5 + level * 2;

        if (isHardAttack) {
            int damage = (int)(baseDamage * 1.5);
            System.out.println(name + " uses a powerful attack!");
            return damage;
        } else {
            System.out.println(name + " uses a light attack!");
            return baseDamage;
        }
    }

    @Override
    public int defend() {
        return 0;
    }

    public Item dropItem() {
        if (Math.random() < 0.3) { // 30% chance to drop item
            return ItemGenerator.generateRandomItem(level);
        }
        return null;
    }
}