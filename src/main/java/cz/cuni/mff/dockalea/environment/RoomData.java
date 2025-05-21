package cz.cuni.mff.dockalea.environment;

import java.util.Hashtable;

public class RoomData {
    public final Hashtable<String, Room> rooms;
    public final String currentRoomName;

    public RoomData(Hashtable<String, Room> rooms, String currentRoomName) {
        this.rooms = rooms;
        this.currentRoomName = currentRoomName;
    }
}
