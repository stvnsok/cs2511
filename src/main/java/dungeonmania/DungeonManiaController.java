package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.LdapName;
import javax.naming.spi.DirStateFactory.Result;

import org.json.*;

import org.json.JSONObject;

public class DungeonManiaController {
    private DungeonResponse d;

    public DungeonManiaController() {
    }

    public String dungeonId() {
        return "dungeon" + System.currentTimeMillis();
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
        List<String> modes = Arrays.asList("peaceful", "standard", "hard");
        // Check validity of arguments
        if (!DungeonManiaController.dungeons().contains(dungeonName) || !modes.contains(gameMode)) {
            throw new IllegalArgumentException();
        }

        int entitiesCount = 0;

        char charBuf[] = new char[10000];
        File f = new File("src/main/resources/dungeons/" + dungeonName + ".json");
        try {
            InputStreamReader input =new InputStreamReader(new FileInputStream(f),"UTF-8");
            int len = input.read(charBuf);
            String text =new String(charBuf,0,len);
            JSONObject dungeon = new JSONObject(text);
            input.close();
            JSONArray JSONEntities = dungeon.getJSONArray("entities");
            List<EntityResponse> entities = new ArrayList<>();
            for (int i = 0; i < JSONEntities.length(); i += 1) {
                entitiesCount += 1;
                JSONObject JSONEntity = JSONEntities.getJSONObject(i);
                int x = JSONEntity.getInt("x");
                int y = JSONEntity.getInt("y");
                Position p = new Position(x, y);
                String type = JSONEntity.getString("type");
                String id = type + entitiesCount;
                Boolean isinteractable = true;
                if (type == "Mercenary" || type == "ZombieToastSpawner") {
                    isinteractable = false;
                }
                EntityResponse entity = new EntityResponse(id, type, p, isinteractable);
                entities.add(entity);
            }
            List<ItemResponse> inventory = new ArrayList<>();
            List<String> buildables = new ArrayList<>();
            JSONObject goals = dungeon.getJSONObject("goal-condition");
            d = new DungeonResponse(dungeonId(), dungeonName, entities, inventory, buildables, goals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        File f = new File("src/main/java/dungeonmania/save/" + name + ".json");
        if (f.exists()) {
            throw new IllegalArgumentException();
        }
        try {
            Boolean createDungeon = f.createNewFile();
            if (!createDungeon) {
                throw new IllegalArgumentException("File already exist.");
            }
            JSONObject JSONDungeon = new JSONObject();

            // Transform dungeonId
            JSONDungeon.put("dungeonId", d.getDungeonId());

            // Transform dungeonName
            JSONDungeon.put("dungeonName", d.getDungeonName());

            // Transform entities
            JSONArray JSONEntities = new JSONArray();
            for (EntityResponse e : d.getEntities()) {
                JSONObject entity = new JSONObject();
                entity.put("id", e.getId());
                Position p = e.getPosition();
                entity.put("x", p.getX());
                entity.put("y", p.getY());
                entity.put("type", e.getType());
                entity.put("isinteractable", e.isInteractable());
                JSONEntities.put(entity);
            }
            JSONDungeon.put("entities", JSONEntities);

            // Transform inventory
            JSONArray JSONInventory = new JSONArray();
            for (ItemResponse i : d.getInventory()) {
                JSONObject item = new JSONObject();
                item.put("id", i.getId());
                item.put("type", i.getType());
                JSONInventory.put(item);
            }
            JSONDungeon.put("inventory", JSONInventory);

            // Transform buildables
            JSONArray JSONBuildables = new JSONArray();
            for (String b : d.getBuildables()) {
                JSONBuildables.put(b);
            }
            JSONDungeon.put("buildables", JSONBuildables);

            // Transform goals
            JSONDungeon.put("goals", d.getGoals());

            FileWriter fw = new FileWriter(f);
            fw.write(JSONDungeon.toString());
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        char charBuf[] = new char[10000];
        File f = new File("src/main/java/dungeonmania/save/" + name + ".json");

        if (!f.exists()) {
            throw new IllegalArgumentException();
        }

        try {
            InputStreamReader input =new InputStreamReader(new FileInputStream(f),"UTF-8");
            int len = input.read(charBuf);
            String text =new String(charBuf,0,len);
            JSONObject dungeon = new JSONObject(text);
            input.close();
            
            // Load dungeonId
            String dungeonId = dungeon.getString("dungeonId");

            // Load dungeonName
            String dungeonName = dungeon.getString("dungeonName");

            // Load entities
            JSONArray JSONEntites = dungeon.getJSONArray("entities");
            List<EntityResponse> entities = new ArrayList<>();
            for (int i = 0; i < JSONEntites.length(); i += 1) {
                JSONObject JSONEntity = JSONEntites.getJSONObject(i);
                String id = JSONEntity.getString("id");
                int x = JSONEntity.getInt("x");
                int y = JSONEntity.getInt("y");
                Position p = new Position(x, y);
                String type = JSONEntity.getString("type");
                Boolean isinteractable = JSONEntity.getBoolean("isinteractable");
                EntityResponse entity = new EntityResponse(id, type, p, isinteractable);
                entities.add(entity);
            }

            // Load inventory
            JSONArray JSONInventory = dungeon.getJSONArray("inventory");
            List<ItemResponse> inventory = new ArrayList<>();
            for (int i = 0; i < JSONInventory.length(); i += 1) {
                JSONObject JSONItem = JSONInventory.getJSONObject(i);
                String id = JSONItem.getString("id");
                String type = JSONItem.getString("type");
                ItemResponse item = new ItemResponse(id, type);
                inventory.add(item);
            }
            
            // Load buildables
            JSONArray JSONBuildables = dungeon.getJSONArray("buildables");
            List<String> buildables = new ArrayList<>();
            for (int i = 0; i < JSONBuildables.length(); i += 1) {
                String buildableENtity = JSONBuildables.getString(i);
                buildables.add(buildableENtity);
            }

            // Load goals
            String goals = dungeon.getString("goals");

            DungeonResponse loadedDungeon = new DungeonResponse(dungeonId, dungeonName, entities, inventory, buildables, goals);

            d = loadedDungeon;
            return loadedDungeon;

        } catch (UnsupportedEncodingException e) {
            e.getStackTrace();
        } catch (IOException e1) {
            e1.getStackTrace();
        }
        return null;
    }

    public List<String> allGames() {
        return new ArrayList<>();
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection)
            throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}