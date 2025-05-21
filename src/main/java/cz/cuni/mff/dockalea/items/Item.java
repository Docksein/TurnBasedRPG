package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;

public abstract class Item {
    protected final String name;
    protected final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public abstract void use(Player player);
    public abstract String getType();
}