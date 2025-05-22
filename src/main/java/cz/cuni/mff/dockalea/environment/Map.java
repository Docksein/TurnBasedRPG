package cz.cuni.mff.dockalea.environment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Utility class responsible for loading the map layout and room data from text files.
 * Provides methods for parsing a room file into a structured representation of the game world.
 * The methods do not check the connectivity or consistency of the map - user has to check it in the file itself.
 */
public class Map {

    /**
     * Loads room data from a specified text file.
     *
     * @param fileName the name of the file containing room definitions
     * @return a RoomData object containing all rooms and the name of the starting room
     * @throws RuntimeException if an error occurs while reading the file
     */
    public static RoomData loadMapFromTxtFile(String fileName) {
        RoomData worldMapData;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            worldMapData = loadRoomsBufferReader(reader);
        } catch (IOException e) {
            System.out.println("Error reading room file: " + fileName);
            throw new RuntimeException(e);
        }
        return worldMapData;
    }

    /**
     * Loads and parses room definitions from a BufferedReader.
     * Each room must begin with a header line starting with '#' followed by optional connections and description.
     * The description starts at the new line and ends with detection of another room or end of the file.
     * New room gets parsed when the line starts with '#' again.
     * The room layout should look like this:
     * # RoomName | North | South | East | West
     * Description
     *
     * @param reader the reader providing room file input
     * @return a RoomData object containing parsed rooms and the name of the starting room
     */
    public static RoomData loadRoomsBufferReader(BufferedReader reader) {
        Hashtable<String, Room> rooms = new Hashtable<>();
        String firstRoomName = null;

        try {
            String line;
            String currentRoomName = null;
            String north = null;
            String south = null;
            String east = null;
            String west = null;
            StringBuilder description = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    if (currentRoomName != null) {
                        Room room = new Room(currentRoomName, description.toString().trim(), north, south, east, west);
                        rooms.put(currentRoomName, room);
                    }

                    description = new StringBuilder();

                    // Parse the room header line: # RoomName | North | South | East | West
                    String[] parts = line.substring(1).split("\\|");
                    currentRoomName = parts[0].trim();

                    if (firstRoomName == null) {
                        firstRoomName = currentRoomName;
                    }

                    // Parse directions, considering empty strings as null
                    north = parts.length > 1 && !parts[1].trim().isEmpty() ? parts[1].trim() : null;
                    south = parts.length > 2 && !parts[2].trim().isEmpty() ? parts[2].trim() : null;
                    east = parts.length > 3 && !parts[3].trim().isEmpty() ? parts[3].trim() : null;
                    west = parts.length > 4 && !parts[4].trim().isEmpty() ? parts[4].trim() : null;
                } else {
                    if (!description.isEmpty()) {
                        description.append("\n");
                    }
                    description.append(line);
                }
            }

            if (currentRoomName != null) {
                Room room = new Room(currentRoomName, description.toString().trim(), north, south, east, west);
                rooms.put(currentRoomName, room);
            }

        } catch (IOException e) {
            System.err.println("Error reading room file: " + e.getMessage());
            e.printStackTrace();
        }

        return new RoomData(rooms, firstRoomName);
    }
}
