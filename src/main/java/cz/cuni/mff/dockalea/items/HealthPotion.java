package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

/**
 * Represents a health potion item that restores a portion of the player's health when used.
 *
 * <p>This class extends {@link Item} and defines a healing effect that directly affects
 * the player's current health points.</p>
 */
public class HealthPotion extends Item {
    /**
     * The amount of health this potion restores when used.
     */
    private final int healAmount;

    /**
     * Constructs a new {@code HealthPotion} with the specified name, description, and healing amount.
     *
     * @param name the name of the potion
     * @param description a brief description of the potion
     * @param healAmount the amount of health restored by this potion
     */
    public HealthPotion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    /**
     * Applies the healing effect to the specified player and prints a message.
     *
     * @param player the player who is using the potion
     */
    @Override
    public void use(Player player) {
        player.heal(healAmount);
        System.out.println("You used " + name + " and restored " + healAmount + " health!");
    }

    /**
     * Returns the type identifier for this item.
     *
     * @return a string representing this item as a health potion
     */
    @Override
    public String getType() { return "Health Potion"; }
}
