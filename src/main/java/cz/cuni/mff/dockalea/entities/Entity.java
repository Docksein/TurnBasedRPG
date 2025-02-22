package cz.cuni.mff.dockalea.entities;

public abstract class Entity {

    public abstract int getMaxHealth();
    public abstract int getCurrentHealth();
    public abstract int getLevel();

    public abstract int setCurrentHealth();

    public abstract int attack();
    public abstract int defend();
}
