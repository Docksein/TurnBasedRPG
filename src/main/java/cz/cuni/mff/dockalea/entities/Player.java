package cz.cuni.mff.dockalea.entities;

public class Player extends Entity {

    private int maxHealth;
    private int currentHealth;
    private int level;
    private int xp;

    public Player(int maxHealth, int level, int xp) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.level = level;
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public int getLevel() {
        return level;
    }

    public void setCurrentHealth(int health) {
        if (health + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += health;
        }
    }
    //TODO
    public int attack() {
        return 0;
    }

    //TODO
    public int defend() {
        return 0;
    }
}
