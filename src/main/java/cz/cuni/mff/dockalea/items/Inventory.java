package cz.cuni.mff.dockalea.items;

import java.util.*;

public class Inventory {
    private final List<Item> items;
    private Weapon equippedWeapon;
    private final int maxSize;

    public Inventory(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
        this.equippedWeapon = new Weapon("Basic Sword", "A simple iron sword", 10);
    }

    public boolean addItem(Item item) {
        if (items.size() >= maxSize) {
            System.out.println("Inventory is full! Cannot add " + item.getName());
            return false;
        }
        items.add(item);
        System.out.println("Added " + item.getName() + " to inventory.");
        return true;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void equipWeapon(Weapon weapon) {
        if (equippedWeapon != null) {
            System.out.println("Unequipped " + equippedWeapon.getName());
        }
        this.equippedWeapon = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSize() {
        return items.size();
    }

    public int getMaxSize() {
        return maxSize;
    }
}