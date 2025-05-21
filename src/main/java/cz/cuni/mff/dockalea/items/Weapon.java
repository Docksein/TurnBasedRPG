package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

public class Weapon extends Item {
    private final int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    public int getDamage() { return damage; }

    @Override
    public void use(Player player) {
        player.equipWeapon(this);
    }

    @Override
    public String getType() { return "Weapon"; }
}