package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.LdapName;
import javax.naming.spi.DirStateFactory.Result;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONObject;

public class DungeonManiaController {
    private DungeonResponse d;
    public DungeonManiaController() {
    }

    public String dungeonId() {
        return "dungeon" + dungeonCount;
    }

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    public List<String> getGameModes() {
        return Arrays.asList("Standard", "Peaceful", "Hard");
    }

    /**
     * /dungeons
     * 
     * Done for you.
     */
    public static List<String> dungeons() {
        try {
            return FileLoader.listFileNamesInResourceDirectory("/dungeons");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public DungeonResponse newGame(String dungeonName, String gameMode) throws IllegalArgumentException {
        List<String> modes = Arrays.asList("Peaceful", "Standard", "Hard");
        // Check validity of arguments
        if (!DungeonManiaController.dungeons().contains(dungeonName) || !modes.contains(gameMode)) {
            throw new IllegalArgumentException();
        }

        int entitiesCount = 0;
        try {
            Object obj = JsonParser.parseReader(new FileReader("src/main/resources/dungeons/" + dungeonName + ".json"));
            JsonObject dungeon = (JsonObject) obj;
            JsonArray entities = dungeon.getAsJsonArray("entities");
            for (int i = 0; i < entities.size(); i += 1) {
                entitiesCount += 1;
                JsonObject entity = entities.get(i).getAsJsonObject();
                int x = entity.get("x").getAsInt();
                int y = entity.get("y").getAsInt();
                Position p = new Position(x, y);
                String type = entity.get("type").getAsString();
                String id = type + System.currentTimeMillis();

                EntityResponse e = new EntityResponse()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        d = new DungeonResponse(dungeonId(), dungeonName, List<EntityResponse> entities, List<ItemResponse> inventory, List<String> buildables, String goals);
        return d;
    }
    public static String abc(String dungeonName, String gameMode) {
        try {
            Object obj = JsonParser.parseReader(new FileReader("src/main/resources/dungeons/" + dungeonName + ".json"));
            JsonObject dungeon = (JsonObject) obj;
            return dungeon.getAsJsonArray("entities").get(0).getAsJsonObject().get("x").toString();

        } catch(Exception e) {
            e.printStackTrace();
        }
        return "abc";
    }
    
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        String path = "src/main/java/dungeons/save";
        
        return null;
    }

    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        return null;
    }

    public List<String> allGames() {
        return new ArrayList<>();
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}