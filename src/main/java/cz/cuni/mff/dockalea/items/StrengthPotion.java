package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

public class StrengthPotion extends Item {
    private final int damageBoost;

    public StrengthPotion(String name, String description, int damageBoost) {
        super(name, description);
        this.damageBoost = damageBoost;
    }

    @Override
    public void use(Player player) {
        player.addTemporaryDamageBoost(damageBoost);
        System.out.println("You used " + name + " and gained " + damageBoost + " damage boost for this encounter!");
    }

    @Override
    public String getType() { return "Strength Potion"; }
}