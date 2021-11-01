package dungeonmania;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
// import org.json.JSONException;
import org.json.JSONObject;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

public class DungeonManiaController {
    private Game currentGame;
    private List<String> savedGames = new ArrayList<>();

    public DungeonManiaController() {
    }

    public String dungeonId() {
        return currentGame.getGameMode() + System.currentTimeMillis();
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

        // Read the json file
        char charBuf[] = new char[100000];
        File f = new File("src/main/resources/dungeons/" + dungeonName + ".json");
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(f),"UTF-8");
            int len = input.read(charBuf);
            String text = new String(charBuf,0,len);
            JSONObject game = new JSONObject(text);
            input.close();

            Character character = null;

            // Create entity
            JSONArray JSONEntities = game.getJSONArray("entities");
            List<Entity> entities = new ArrayList<>();
            for (int i = 0; i < JSONEntities.length(); i += 1) {
                entitiesCount += 1;
                JSONObject JSONEntity = JSONEntities.getJSONObject(i);
                int x = JSONEntity.getInt("x");
                int y = JSONEntity.getInt("y");
                Position p = new Position(x, y);
                String type = JSONEntity.getString("type");
                String id = type + entitiesCount;
                Boolean isinteractable = false;
                if (type.equals("Mercenary") || type.equals("ZombieToastSpawner")) {
                    isinteractable = true;
                }

                Entity entity;
                if (type.equals("portal")) {
                    String colour = JSONEntity.getString("colour");
                    entity = new Portal(id, type, p, isinteractable, colour);
                } else if (type.equals("player")) {
                    character = new Character(id, type, p, isinteractable, 100, 5, new ArrayList<>(), null);
                    entity = character;
                } else {
                    entity = new Entity(id, type, p, isinteractable);
                }
                entities.add(entity);
            }

            // Create inventory
            List<Items> inventory = new ArrayList<>();

            // Create Buildables
            List<String> buildables = new ArrayList<>();

            // Create Goals
            //String goals = game.getJSONObject("goal-condition").toString();

            // JSONObject goalCondition;
            // try {
            //     // file may not contain goal condition? like portals.json
            //     goalCondition = game.getJSONObject("goal-condition");
            // } catch (JSONException e) {
            //     goalCondition = new JSONObject();
            // }
            // currentGame = new Game(dungeonName, gameMode, entities, inventory, buildables, goalCondition);
            if (character != null) {
                character.setEntities(entities);
                character.setInventory(inventory);
            }

            // Create DungeonResponse and Game
            currentGame = new Game(dungeonName, gameMode, entities, inventory, buildables, game.getJSONObject("goal-condition"), character);
            
            return getDungeonResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        File f = new File("src/main/java/dungeonmania/save/" + name + ".json");
        if (f.exists()) {
            throw new IllegalArgumentException("File already exist.");
        }
        try {
            f.createNewFile();
            JSONObject JSONDungeon = new JSONObject();

            // Save name
            savedGames.add(name);

            // Transform dungeonName
            JSONDungeon.put("dungeonName", currentGame.getDungeonName());

            // Transform gameMode
            JSONDungeon.put("gameMode", currentGame.getGameMode());

            // Transform entities
            JSONArray JSONEntities = new JSONArray();
            for (Entity e : currentGame.getEntities()) {
                JSONObject entity = new JSONObject();
                entity.put("id", e.getId());
                Position p = e.getPosition();
                entity.put("x", p.getX());
                entity.put("y", p.getY());
                entity.put("type", e.getType());
                entity.put("isinteractable", e.isInteractable());
                if (e instanceof Portal) {
                    Portal portal = (Portal) e;
                    entity.put("colour", portal.getColour());
                }
                JSONEntities.put(entity);
            }
            JSONDungeon.put("entities", JSONEntities);

            // Transform inventory
            JSONArray JSONInventory = new JSONArray();
            for (Items i : currentGame.getInventory()) {
                JSONObject item = new JSONObject();
                item.put("id", i.getItemId());
                item.put("type", i.getItemType());
                item.put("durability", i.getDurability());
                JSONInventory.put(item);
            }
            JSONDungeon.put("inventory", JSONInventory);

            // Transform buildables
            JSONArray JSONBuildables = new JSONArray();
            for (String b : currentGame.getBuildables()) {
                JSONBuildables.put(b);
            }
            JSONDungeon.put("buildables", JSONBuildables);

            // Transform goals
            JSONDungeon.put("goal-condition", currentGame.getJGoals());

            FileWriter fw = new FileWriter(f);
            fw.write(JSONDungeon.toString());
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getDungeonResponse();
    }

    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        // Read a JSON file
        char charBuf[] = new char[100000];
        File f = new File("src/main/java/dungeonmania/save/" + name + ".json");

        if (!f.exists()) {
            throw new IllegalArgumentException();
        }

        try {
            InputStreamReader input =new InputStreamReader(new FileInputStream(f),"UTF-8");
            int len = input.read(charBuf);
            String text =new String(charBuf,0,len);
            JSONObject game = new JSONObject(text);
            input.close();
            
            // Load dungeonName
            String dungeonName = game.getString("dungeonName");

            // Load gameMode
            String gameMode = game.getString("gameMode");
            Character character = null;
            
            // Load entities
            JSONArray JSONEntites = game.getJSONArray("entities");
            List<Entity> entities = new ArrayList<>();
            for (int i = 0; i < JSONEntites.length(); i += 1) {
                JSONObject JSONEntity = JSONEntites.getJSONObject(i);
                String id = JSONEntity.getString("id");
                int x = JSONEntity.getInt("x");
                int y = JSONEntity.getInt("y");
                Position p = new Position(x, y);
                String type = JSONEntity.getString("type");
                Boolean isinteractable = JSONEntity.getBoolean("isinteractable");
                // Entity entity = new Entity(id, type, p, isinteractable);
                Entity entity;
                
                // switch (type) {
                //     case "player":
                //         character = new Character(id, type, p, isinteractable, 100, 5, null, null);
                //         entity = character;
                //     case "wall":
                //         entity = new Wall(id, type, p, isinteractable);
                //         break;
                //     case "exit":
                //         entity = new Exit(id, type, p, isinteractable);
                //         break;
                //     case "boulder":
                //         entity = new Boulder(id, type, p, isinteractable);
                //         break;
                //     case "switch":
                //         entity = new FloorSwitch(id, type, p, isinteractable);
                //         break;
                //     case "door":
                //         String keyId = JSONEntity.getString("key");
                //         Boolean isOpen = JSONEntity.getBoolean("isopen");
                //         // isopen is false for newgame!!
                //         entity = new Door(id, type, p, isinteractable, isOpen, keyId);
                //         break;
                //     case "portal":
                //         String colour = JSONEntity.getString("colour");
                //         entity = new Portal(id, type, p, isinteractable, colour);
                //         break;
                //     case "zombie_toast_spawner":
                //         entity = new ZombieToastSpawner(id, type, p, isinteractable);
                //         break;


                //     default:
                //         entity = new Entity(id, type, p, isinteractable);
                // }

                if (type.equals("player")) {
                    character = new Character(id, type, p, isinteractable, 100, 5, new ArrayList<>(), null);
                    entity = character;
                } else {
                    entity = new Entity(id, type, p, isinteractable);
                }

                entities.add(entity);
            }

            // Load inventory
            JSONArray JSONInventory = game.getJSONArray("inventory");
            List<Items> inventory = new ArrayList<>();
            for (int i = 0; i < JSONInventory.length(); i += 1) {
                JSONObject JSONItem = JSONInventory.getJSONObject(i);
                String id = JSONItem.getString("id");
                String type = JSONItem.getString("type");
                int durability = JSONItem.getInt("durability");
                Items item = new Items(id, type, durability);
                inventory.add(item);
            }
            
            // Load buildables
            JSONArray JSONBuildables = game.getJSONArray("buildables");
            List<String> buildables = new ArrayList<>();
            for (int i = 0; i < JSONBuildables.length(); i += 1) {
                String buildableEntity = JSONBuildables.getString(i);
                buildables.add(buildableEntity);
            }

            if (character != null) {
                character.setEntities(entities);
                character.setInventory(inventory);
            }

            // currentGame = new Game(dungeonName, gameMode, entities, inventory, buildables, game.getJSONObject("goal-condition"));
            currentGame = new Game(dungeonName, gameMode, entities, inventory, buildables, game.getJSONObject("goal-condition"), character);

            return getDungeonResponse();

        } catch (UnsupportedEncodingException e) {
            e.getStackTrace();
        } catch (IOException e1) {
            e1.getStackTrace();
        }
        return null;
    }

    // Test function
    public Object abc() {
        File f = new File("src/main/java/dungeonmania/save/writeClass.json");
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
            Position p = new Position(1, 2);
            Entity e1 = new Entity("e1", "wall", p, false);
            Entity e2 = new Entity("e2", "player", p, false);
            List<Entity> entities = new ArrayList<>();
            entities.add(e1);
            entities.add(e2);
            JSONObject obj = new JSONObject();
            int data[] = new int[2];
            data[0] = 1;
            data[1] = 10;
            obj.put("entities", data);
             FileWriter fw = new FileWriter(f);
             fw.write(obj.toString());
             fw.close();
            
            char charBuf[] = new char[100000];
            InputStreamReader input =new InputStreamReader(new FileInputStream(f),"UTF-8");
            int len = input.read(charBuf);
            String text =new String(charBuf,0,len);
            input.close();
            JSONObject game = new JSONObject(text);

            Object read = game.get("entities");
            return read;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "wrong";
    }

    // Get DungeonResponse from currentGame
    public DungeonResponse getDungeonResponse() {
        String dungeonName = currentGame.getDungeonName();

        List<EntityResponse> entitiesR = new ArrayList<>();
        for (Entity e : currentGame.getEntities()) {
            EntityResponse entityR = new EntityResponse(e.getId(), e.getType(), e.getPosition(), e.isInteractable());
            entitiesR.add(entityR);
        }

        List<ItemResponse> inventoryR = new ArrayList<>();
        for (Items i : currentGame.getInventory()) {
            ItemResponse itemR = new ItemResponse(i.getItemId(), i.getItemType());
            inventoryR.add(itemR);
        }

        DungeonResponse currentDungeon = new DungeonResponse(dungeonId(), dungeonName, entitiesR, inventoryR, currentGame.getBuildables(), currentGame.getGoals());
        return currentDungeon;
    }

    public List<String> allGames() {
        return savedGames;
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection)
            throws IllegalArgumentException, InvalidActionException {

        currentGame.tick(itemUsed, movementDirection);

        return getDungeonResponse();
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        
        if (!currentGame.getBuildables().contains(buildable)) {
            throw new IllegalArgumentException("Not a valid build item");
        }
        currentGame.build(buildable);
        return getDungeonResponse();
    }

    // for testing purposes??
    public Game getCurrentGame() {
        return currentGame;
    }
}

