package cz.cuni.mff.dockalea.items;

import cz.cuni.mff.dockalea.entities.Player;
/**
 * The {@code Item} class serves as an abstract foundation for all usable items
 * within the turn-based RPG game.
 *
 * <p>This class provides the basic interface and structure for all specific
 * item types, ensuring consistency in how items are accessed and used by players.
 * Concrete subclasses must define how the item behaves when used by implementing
 * the {@link #use(Player)} method, as well as indicate what kind of item it is
 * via the {@link #getType()} method.</p>
 *
 * @author Adam Dočkálek
 */
public abstract class Item {
    protected final String name;
    protected final String description;

    /**
     * Constructs a new {@code Item} with the specified name and description.
     * This constructor is called by subclasses when they are instantiated.
     *
     * @param name the display name of the item
     * @param description a brief explanation of the item's function or lore
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return the name of the item, typically used for display purposes
     */
    public String getName() { return name; }

    /**
     * Retrieves the description of the item.
     *
     * @return a short textual explanation of what the item does or represents
     */
    public String getDescription() { return description; }

    /**
     * Defines the behavior of the item when it is used by a player.
     * Subclasses must provide an implementation that specifies the effects
     * of the item on the player.
     *
     * @param player the player who is using the item
     */
    public abstract void use(Player player);

    /**
     * Returns a string representing the item's category or type.
     *
     * @return a string identifier for the item's type
     */
    public abstract String getType();
}