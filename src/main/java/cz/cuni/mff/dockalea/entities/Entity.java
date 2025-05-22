package cz.cuni.mff.dockalea.entities;

/**
 * Represents a base entity in the game with health and level attributes.
 *
 * <p>All game characters, such as players and enemies, inherit from this class.
 * Provides core functionality for health management, damage handling, and combat behavior.</p>
 */
public abstract class Entity {
    /**
     * The maximum health of the entity.
     */
    protected int maxHealth;

    /**
     * The current health of the entity.
     */
    protected int currentHealth;

    /**
     * The level of the entity.
     */
    protected int level;

    /**
     * Constructs a new entity with the specified maximum health and level.
     *
     * @param maxHealth the maximum health the entity can have
     * @param level the level of the entity
     */
    public Entity(int maxHealth, int level) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.level = level;
    }

    public int getMaxHealth() { return maxHealth; }
    public int getCurrentHealth() { return currentHealth; }
    public int getLevel() { return level; }

    /**
     * Sets the current health of the entity, ensuring it stays within valid bounds.
     *
     * @param health the new health value to apply
     */
    public void setCurrentHealth(int health) {
        this.currentHealth = Math.max(0, Math.min(maxHealth, health));
    }

    /**
     * Reduces the entity's current health by the given damage amount.
     *
     * @param damage the amount of damage taken
     */
    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
    }

    /**
     * Checks if the entity is still alive.
     *
     * @return {@code true} if current health is above 0, {@code false} otherwise
     */
    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * Performs an attack action.
     *
     * @return the damage dealt
     */
    public abstract int attack();

    /**
     * Performs a defense action.
     *
     * @return the effectiveness of the defense
     */
    public abstract int defend();
}
