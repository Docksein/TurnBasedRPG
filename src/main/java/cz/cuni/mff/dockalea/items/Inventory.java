package cz.cuni.mff.dockalea.items;

import java.util.*;

/**
 * Manages the collection of items held by a player, including consumables and equipment.
 *
 * <p>The {@code Inventory} class allows adding and removing items, managing the equipped weapon,
 * and checking inventory state such as size or emptiness. It ensures the number of items does not
 * exceed a configurable maximum capacity.</p>
 */
public class Inventory {
    /**
     * The list of items currently held in the inventory.
     */
    private final List<Item> items;

    /**
     * The weapon currently equipped by the player.
     */
    private Weapon equippedWeapon;

    /**
     * The maximum number of items the inventory can hold.
     */
    private final int maxSize;

    /**
     * Constructs a new {@code Inventory} with a given maximum size and initializes
     * it with a default weapon.
     *
     * @param maxSize the maximum number of items allowed in the inventory
     */
    public Inventory(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
        this.equippedWeapon = new Weapon("Fists", "Just your fists. You don't have a weapon yet.", 10);
    }

    /**
     * Attempts to add an item to the inventory.
     *
     * @param item the item to be added
     * @return {@code true} if the item was added, {@code false} if the inventory is full
     */
    public boolean addItem(Item item) {
        if (items.size() >= maxSize) {
            System.out.println("Inventory is full! Cannot add " + item.getName());
            return false;
        }
        items.add(item);
        System.out.println("Added " + item.getName() + " to inventory.");
        return true;
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Returns a copy of the current list of items in the inventory.
     *
     * @return a list of the items currently held
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns the currently equipped weapon.
     *
     * @return the weapon the player has equipped
     */
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Equips the specified weapon, replacing the current one if necessary.
     *
     * @param weapon the new weapon to equip
     */
    public void equipWeapon(Weapon weapon) {
        if (equippedWeapon != null) {
            System.out.println("Unequipped " + equippedWeapon.getName());
        }
        this.equippedWeapon = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
    }

    /**
     * Checks whether the inventory contains any items.
     *
     * @return {@code true} if the inventory is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Returns the current number of items in the inventory.
     *
     * @return the number of items
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Returns the maximum number of items the inventory can hold.
     *
     * @return the inventory capacity
     */
    public int getMaxSize() {
        return maxSize;
    }
}
