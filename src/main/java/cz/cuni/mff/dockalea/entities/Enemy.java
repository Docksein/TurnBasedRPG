package cz.cuni.mff.dockalea.entities;

public class Enemy extends Entity {
    public final String name;
    private final int level;
    public Enemy(String name, int level) {
        int max = level + 2;
        int min = level - 3;
        int range = max - min + 1;
        this.level = (int)(Math.random() * range) + min;
        this.name = name;
    }

    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public int getCurrentHealth() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setCurrentHealth(int health) {

    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }
}
