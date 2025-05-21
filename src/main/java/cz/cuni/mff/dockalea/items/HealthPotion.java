package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

public class HealthPotion extends Item {
    private final int healAmount;

    public HealthPotion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player) {
        player.heal(healAmount);
        System.out.println("You used " + name + " and restored " + healAmount + " health!");
    }

    @Override
    public String getType() { return "Health Potion"; }
}
