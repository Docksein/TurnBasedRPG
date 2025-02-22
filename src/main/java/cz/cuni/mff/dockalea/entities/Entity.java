package cz.cuni.mff.dockalea.entities;

public abstract class Entity {

    public abstract int getMaxHealth();
    public abstract int getCurrentHealth();
    public abstract int getLevel();

    public abstract void setCurrentHealth(int health);

    public abstract int attack();
    public abstract int defend();
}
