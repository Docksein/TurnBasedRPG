package cz.cuni.mff.dockalea.environment;

import cz.cuni.mff.dockalea.entities.Enemy;

/**
 * Represents a room within the game world. Each room can have up to four directional connections
 * and contains metadata such as description, enemy presence, and exploration state.
 */
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

    /**
     * Constructs a new Room instance with the specified parameters.
     *
     * @param name        the name of the room
     * @param desc        the room's description shown to the player
     * @param north       the name of the room to the north (or null if none)
     * @param south       the name of the room to the south (or null if none)
     * @param east        the name of the room to the east (or null if none)
     * @param west        the name of the room to the west (or null if none)
     */
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

    /**
     * @return true if the room has been visited by the player
     */
    public boolean isVisited() { return visited; }

    /**
     * @return true if the room has been fully explored
     */
    public boolean isExplored() { return explored; }

    /**
     * @return the enemy currently occupying the room, or null if none
     */
    public Enemy getCurrentEnemy() { return currentEnemy; }

    /**
     * Sets the visited state of the room.
     *
     * @param visited true if the player has visited this room
     */
    public void setVisited(boolean visited) { this.visited = visited; }

    /**
     * Sets the explored state of the room.
     *
     * @param explored true if the player has explored this room
     */
    public void setExplored(boolean explored) { this.explored = explored; }

    /**
     * Assigns an enemy to this room.
     *
     * @param enemy the enemy present in the room
     */
    public void setCurrentEnemy(Enemy enemy) { this.currentEnemy = enemy; }

    /**
     * @return true if the room is designated as a boss room based on its name
     */
    public boolean isBossRoom() {
        return Name.toLowerCase().contains("boss");
    }

    /**
     * Returns a concise string representation of the room and its connections.
     *
     * @return a string showing the room name and available directions
     */
    @Override
    public String toString() {
        return Name + ", N: " + North + ", S: " + South + ", E:" + East + ", W: " + West;
    }
}
