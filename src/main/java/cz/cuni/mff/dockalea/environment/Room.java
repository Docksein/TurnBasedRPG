package cz.cuni.mff.dockalea.environment;

public class Room {
    public final String Name;
    public final String Description;
    public final String North;
    public final String South;
    public final String East;
    public final String West;

    public Room(String name, String desc, String north, String south, String east, String west) {
        Name = name;
        Description = desc;
        North = north;
        South = south;
        East = east;
        West = west;
    }

    @Override
    public String toString() {
        return Name + ", N: " + North + ", S: " + South + ", E:" + East + ", W: " + West;
    }

}
