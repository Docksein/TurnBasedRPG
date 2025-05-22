package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

/**
 * Represents a weapon item that can be equipped by a player.
 * Weapons are used to enhance a player's attack capabilities by providing
 * additional damage output during combat.
 *
 * <p>This class extends {@link Item} and adds functionality specific to weapons,
 * such as storing damage values and interacting with the player's equipment system.</p>
 */
public class Weapon extends Item {
    /**
     * The amount of damage this weapon adds to the player's attacks.
     */
    private final int damage;

    /**
     * Constructs a new {@code Weapon} with the specified name, description, and damage.
     *
     * @param name the name of the weapon
     * @param description a brief description of the weapon
     * @param damage the amount of damage this weapon provides
     */
    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    /**
     * Returns the damage value of the weapon.
     *
     * @return the damage this weapon deals
     */
    public int getDamage() { return damage; }

    /**
     * Equips this weapon to the given player.
     *
     * @param player the player who will equip the weapon
     */
    @Override
    public void use(Player player) {
        player.equipWeapon(this);
    }

    /**
     * Returns the type identifier for this item.
     *
     * @return a string representing this item as a weapon
     */
    @Override
    public String getType() { return "Weapon"; }
}
