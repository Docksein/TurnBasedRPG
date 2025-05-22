package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

/**
 * Represents a strength potion item that temporarily increases the player's damage.
 *
 * <p>This class extends {@link Item} and applies a temporary damage boost effect
 * to the player during combat or an encounter.</p>
 */
public class StrengthPotion extends Item {
    /**
     * The amount of additional damage granted to the player.
     */
    private final int damageBoost;

    /**
     * Constructs a new {@code StrengthPotion} with the specified name, description, and damage boost.
     *
     * @param name the name of the potion
     * @param description a brief description of the potion
     * @param damageBoost the amount of additional damage granted
     */
    public StrengthPotion(String name, String description, int damageBoost) {
        super(name, description);
        this.damageBoost = damageBoost;
    }

    /**
     * Applies a temporary damage boost to the player and prints a message.
     *
     * @param player the player who is using the potion
     */
    @Override
    public void use(Player player) {
        player.addTemporaryDamageBoost(damageBoost);
        System.out.println("You used " + name + " and gained " + damageBoost + " damage boost for this encounter!");
    }

    /**
     * Returns the type identifier for this item.
     *
     * @return a string representing this item as a strength potion
     */
    @Override
    public String getType() { return "Strength Potion"; }
}
