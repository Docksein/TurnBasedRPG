package cz.cuni.mff.dockalea.environment;

import java.util.Hashtable;

/**
 * A container class representing the map structure and the current (starting) room.
 * Holds a hashtable of room names mapped to Room objects and the initial room name.
 */
public class RoomData {
    /**
     * A hashtable mapping room names to their corresponding Room objects.
     * Used to store and access all rooms in the game by name.
     */
    public final Hashtable<String, Room> rooms;

    /**
     * The name of the room the player is currently in.
     * This is used as a key to retrieve the current Room object from the rooms hashtable.
     */
    public final String currentRoomName;

    /**
     * Constructs a new RoomData object with a collection of rooms and a starting room.
     *
     * @param rooms            a hashtable mapping room names to their Room objects
     * @param currentRoomName  the name of the initial room the player starts in
     */
    public RoomData(Hashtable<String, Room> rooms, String currentRoomName) {
        this.rooms = rooms;
        this.currentRoomName = currentRoomName;
    }
}
