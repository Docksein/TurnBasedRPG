package cz.cuni.mff.dockalea.environment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Map {

    public static Hashtable<String, Room> loadMapFromTxtFile(String fileName) {
        Hashtable<String, Room> world_map;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            world_map = loadMapBufferReader(reader);
        } catch (IOException e) {
            System.out.println("Error reading room file: " + fileName);
            throw new RuntimeException(e);
        }
        return world_map;
    }

    private static Hashtable<String, Room> loadMapBufferReader(BufferedReader reader) throws IOException {
        Hashtable<String, Room> world_map = new Hashtable<>();
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
                    world_map.put(currentRoomName, room);
                }

                description = new StringBuilder();

                String[] parts = line.substring(1).split("\\|");
                currentRoomName = parts[0].trim();

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
            world_map.put(currentRoomName, room);
        }
        return world_map;
    }

}
