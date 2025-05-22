package cz.cuni.mff.dockalea.environment;

import cz.cuni.mff.dockalea.entities.Enemy;

public class Room {
    public final String Name;
    public final String Description;
    public final String North;
    public final String South;
    public final String East;
    public final String West;
    private boolean visited;
    private boolean explored;
    private Enemy currentEnemy;

    public Room(String name, String desc, String north, String south, String east, String west) {
        Name = name;
        Description = desc;
        North = north;
        South = south;
        East = east;
        West = west;
        this.visited = false;
        this.explored = false;
        this.currentEnemy = null;
    }

    public boolean isVisited() { return visited; }
    public boolean isExplored() { return explored; }
    public Enemy getCurrentEnemy() { return currentEnemy; }

    public void setVisited(boolean visited) { this.visited = visited; }
    public void setExplored(boolean explored) { this.explored = explored; }
    public void setCurrentEnemy(Enemy enemy) { this.currentEnemy = enemy; }

    public boolean isBossRoom() {
        return Name.toLowerCase().contains("boss");
    }

    @Override
    public String toString() {
        return Name + ", N: " + North + ", S: " + South + ", E:" + East + ", W: " + West;
    }
}
