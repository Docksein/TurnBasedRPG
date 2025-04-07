package cz.cuni.mff.dockalea.environment;

public class Room {
    public final String Description;
    public final String North;
    public final String South;
    public final String East;
    public final String West;

    public Room(String desc, String north, String south, String east, String west) {
        Description = desc;
        North = north;
        South = south;
        East = east;
        West = west;
    }

}
