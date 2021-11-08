package dungeonmania;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.util.Position;

public class EntityFactory {
    private Character character;
    public EntityFactory() {

    }
    /**
     * Takes a JSONArray of entities and a gameMode(to account for player health) and produces
     * a list of entity objects.
     * @param entities
     * @param gameMode
     * @return List of entity objects
     */
    public List<Entity> createEntity(JSONArray entities, String gameMode) {
        List<Entity> entityList = new ArrayList<>();
        for (int i = 0; i < entities.length(); i++) {
            JSONObject entity = entities.getJSONObject(i);
            Position position = new Position(entity.getInt("x"), entity.getInt("y"));
            String type = entity.getString("type");
            String id = type + String.valueOf(i);
            switch (type) {
                case "door":
                case "key":
                    entityList.add(createKeyObject(id, position, type, entity.getInt("key")));
                    break;

                case "portal":
                    entityList.add(createPortal(id, position, type, entity.getString("colour")));
                    break;

                case "player":
                    entityList.add(createPlayer(id, position, type, gameMode, entityList));
                    break;

                default:
                    // entityList.add(createEntity(String.valueOf(i), position, type));
                    entityList.add(createEntity(id, position, type));
                    break;
            }
        }
        return entityList;
    }
    /**
     * Takes in an id, position and type of entity and produces entity object.
     * @param id
     * @param position
     * @param type
     * @return an Entity object of requested position and type.
     */
    public static Entity createEntity(String id, Position position, String type) {
        switch (type) {
            case "zombie":
                return new Zombie(id, type, position, false, 20, 4);
            
            case "spider":
                return new Spider(id, type, position, false, 10, 3);
            
            case "mercenary":
                return new Mercenary(id, type, position, true, 25, 5, 1, false);

            case "boulder":
                return new Boulder(id, type, position, false);
            
            case "zombie_toast_spawner":
                return new ZombieToastSpawner(id, type, position, true);
                
            default:
                return new Entity(id, type, position, false);
        }
    }
    /**
     * Creates a Character object that will represent the player. Health will change based on given gamemode.
     * @param id
     * @param position
     * @param type
     * @param gameMode
     * @param entities
     * @return a Character entity
     */
    public Character createPlayer(String id, Position position, String type, String gameMode, List<Entity> entities) {
        int health = (gameMode.equals("Hard")) ? (10):(20);
        int attack = 3;
        List<Items> inventory = new ArrayList<>();
        character = new Character(id, type, position, false, health, attack, inventory, entities);
        return character;
    }

    public Character getCharacter() {
        return character;
    }

    public static Entity createPortal(String id, Position position, String type, String colour) {
        return new Portal(id, colour + "_" + type, position, false, colour);
    }


    public static Entity createKeyObject(String id, Position position, String type, int keyId) {
        switch (type) {
            case "door":
                return new Door(id, type, position, false, false, keyId);
            
            case "key":
                return new KeyEntity(id, type, position, false, keyId);
            
            default:
                return null;
        }
    }
}
