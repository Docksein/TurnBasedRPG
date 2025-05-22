package cz.cuni.mff.dockalea.items;

import java.util.Random;

/**
 * Utility class for generating random item instances based on player level.
 *
 * <p>The {@code ItemGenerator} provides methods to create randomized {@link Item}
 * objects such as weapons, health potions, and strength potions. Each item is scaled
 * according to the provided level to maintain gameplay balance and progression.</p>
 */
public class ItemGenerator {
    /**
     * Shared {@link Random} instance used for generating item attributes and types.
     */
    private static final Random random = new Random();

    /**
     * Generates a random item suitable for the given player level.
     * The item type is chosen randomly from weapon, health potion, or strength potion.
     *
     * @param level the level of the player or encounter to scale the item accordingly
     * @return a randomly generated {@code Item}
     */
    public static Item generateRandomItem(int level) {
        int itemType = random.nextInt(3);

        return switch (itemType) {
            case 0 -> generateWeapon(level);
            case 1 -> generateHealthPotion(level);
            case 2 -> generateStrengthPotion(level);
            default -> generateHealthPotion(level); // Fallback
        };
    }

    /**
     * Generates a weapon with damage scaled to the given level.
     *
     * @param level the level to determine weapon strength and name
     * @return a {@code Weapon} instance
     */
    private static Weapon generateWeapon(int level) {
        String[] weaponNames = {"Iron Sword", "Steel Blade", "Silver Sword", "Enchanted Blade", "Dragon Slayer"};
        String name = weaponNames[Math.min(level - 1, weaponNames.length - 1)];
        int damage = 8 + level * 3 + random.nextInt(5);
        return new Weapon(name, "A weapon of level " + level, damage);
    }

    /**
     * Generates a health potion with healing power scaled to the given level.
     *
     * @param level the level to determine healing amount
     * @return a {@code HealthPotion} instance
     */
    private static HealthPotion generateHealthPotion(int level) {
        int healAmount = 20 + level * 5 + random.nextInt(10);
        return new HealthPotion("Health Potion", "Restores " + healAmount + " health", healAmount);
    }

    /**
     * Generates a strength potion with a damage boost scaled to the given level.
     *
     * @param level the level to determine damage boost
     * @return a {@code StrengthPotion} instance
     */
    private static StrengthPotion generateStrengthPotion(int level) {
        int damageBoost = 5 + level * 2 + random.nextInt(3);
        return new StrengthPotion("Strength Potion", "Increases damage by " + damageBoost + " for one encounter", damageBoost);
    }
}
