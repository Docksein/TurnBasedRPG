package cz.cuni.mff.dockalea.entities;

import cz.cuni.mff.dockalea.items.Item;
import cz.cuni.mff.dockalea.items.ItemGenerator;

/**
 * Represents an enemy entity in the game.
 *
 * <p>Enemies have a name, level, health, and can be flagged as bosses.
 * They reward XP upon defeat and may drop items.</p>
 */
public class Enemy extends Entity {
    private final String name;
    private final boolean isBoss;
    private final int xpReward;

    /**
     * Constructs an enemy with a name, level, and boss status.
     *
     * @param name the name of the enemy
     * @param level the level of the enemy
     * @param isBoss {@code true} if this enemy is a boss
     */
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

    /**
     * Performs an attack, randomly choosing between light and strong attacks.
     * 30% chance for hard attack.
     *
     * @return the damage dealt
     */
    @Override
    public int attack() {
        boolean isHardAttack = Math.random() < 0.3;
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

    /**
     * Returns the defense effectiveness of the enemy.
     *
     * @return always returns 0 for basic enemies
     */
    @Override
    public int defend() {
        return 0;
    }

    /**
     * Determines if the enemy drops an item after defeat.
     * 50% chance to drop an item.
     *
     * @return a generated {@code Item}, or {@code null} if no item drops
     */
    public Item dropItem() {
        if (Math.random() < 0.5) {
            return ItemGenerator.generateRandomItem(level);
        }
        return null;
    }
}
