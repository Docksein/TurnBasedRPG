package cz.cuni.mff.dockalea.entities;

import java.util.Random;

/**
 * Utility class for generating random enemy instances for encounters.
 *
 * <p>Enemies are created with a name, randomized level, and boss status.
 * Boss enemies have stronger attributes.</p>
 */
public class EnemyGenerator {
    /** Random number generator used for enemy selection and randomization. */
    private static final Random random = new Random();

    /** List of names for common (non-boss) enemies. */
    private static final String[] commonEnemyNames = {
            "Goblin", "Orc", "Skeleton", "Wolf", "Spider", "Bandit"
    };

    /** List of names for boss enemies. */
    private static final String[] bossNames = {
            "Goblin King", "Orc Warlord", "Lich", "Dragon", "Giant Spider", "Bandit Leader"
    };

    /**
     * Generates a new {@code Enemy} instance scaled to the player's level.
     *
     * @param playerLevel the player's level to determine enemy difficulty
     * @param isBoss whether the generated enemy is a boss
     * @return a new {@code Enemy} instance
     */
    public static Enemy generateEnemy(int playerLevel, boolean isBoss) {
        String[] names = isBoss ? bossNames : commonEnemyNames;
        String name = names[random.nextInt(names.length)];

        int enemyLevel = Math.max(1, playerLevel + random.nextInt(3) - 1);
        return new Enemy(name, enemyLevel, isBoss);
    }
}
