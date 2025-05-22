package cz.cuni.mff.dockalea.environment;

import java.util.Hashtable;

/**
 * A container class representing the map structure and the current (starting) room.
 * Holds a hashtable of room names mapped to Room objects and the initial room name.
 */
public class RoomData {
    public final Hashtable<String, Room> rooms;
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
