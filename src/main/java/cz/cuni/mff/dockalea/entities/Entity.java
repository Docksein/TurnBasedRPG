package cz.cuni.mff.dockalea.entities;

public abstract class Entity {
    protected int maxHealth;
    protected int currentHealth;
    protected int level;

    public Entity(int maxHealth, int level) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.level = level;
    }

    public int getMaxHealth() { return maxHealth; }
    public int getCurrentHealth() { return currentHealth; }
    public int getLevel() { return level; }

    public void setCurrentHealth(int health) {
        this.currentHealth = Math.max(0, Math.min(maxHealth, health));
    }

    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public abstract int attack();
    public abstract int defend();
}