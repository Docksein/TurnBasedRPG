package cz.cuni.mff.dockalea.items;

import java.util.Random;

public class ItemGenerator {
    private static final Random random = new Random();

    public static Item generateRandomItem(int level) {
        int itemType = random.nextInt(3);

        return switch (itemType) {
            case 0 -> // Weapon
                    generateWeapon(level);
            case 1 -> // Health Potion
                    generateHealthPotion(level);
            case 2 -> // Strength Potion
                    generateStrengthPotion(level);
            default -> generateHealthPotion(level);
        };
    }

    private static Weapon generateWeapon(int level) {
        String[] weaponNames = {"Iron Sword", "Steel Blade", "Silver Sword", "Enchanted Blade", "Dragon Slayer"};
        String name = weaponNames[Math.min(level - 1, weaponNames.length - 1)];
        int damage = 8 + level * 3 + random.nextInt(5);
        return new Weapon(name, "A weapon of level " + level, damage);
    }

    private static HealthPotion generateHealthPotion(int level) {
        int healAmount = 20 + level * 5 + random.nextInt(10);
        return new HealthPotion("Health Potion", "Restores " + healAmount + " health", healAmount);
    }

    private static StrengthPotion generateStrengthPotion(int level) {
        int damageBoost = 5 + level * 2 + random.nextInt(3);
        return new StrengthPotion("Strength Potion", "Increases damage by " + damageBoost + " for one encounter", damageBoost);
    }
}