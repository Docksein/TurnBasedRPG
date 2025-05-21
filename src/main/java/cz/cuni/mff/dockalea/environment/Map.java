package cz.cuni.mff.dockalea.environment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Map {

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
                    // If we've been processing a room, create it before starting a new one
                    if (currentRoomName != null) {
                        Room room = new Room(currentRoomName, description.toString().trim(), north, south, east, west);
                        rooms.put(currentRoomName, room);
                    }

                    // Reset for new room
                    description = new StringBuilder();

                    // Parse the room header line: # RoomName | North | South | East | West
                    String[] parts = line.substring(1).split("\\|");
                    currentRoomName = parts[0].trim();

                    // Save the first room name we encounter
                    if (firstRoomName == null) {
                        firstRoomName = currentRoomName;
                    }

                    // Parse directions, considering empty strings as null
                    north = parts.length > 1 && !parts[1].trim().isEmpty() ? parts[1].trim() : null;
                    south = parts.length > 2 && !parts[2].trim().isEmpty() ? parts[2].trim() : null;
                    east = parts.length > 3 && !parts[3].trim().isEmpty() ? parts[3].trim() : null;
                    west = parts.length > 4 && !parts[4].trim().isEmpty() ? parts[4].trim() : null;
                } else {
                    // Add to the description
                    if (description.length() > 0) {
                        description.append("\n");
                    }
                    description.append(line);
                }
            }

            // Don't forget the last room
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