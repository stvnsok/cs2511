package dungeonmania;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.util.Position;

public class EntityFactory {
    public static List<Entity> createEntity(JSONArray entities, String gameMode) {
        List<Entity> entityList = new ArrayList<>();
        for (int i = 0; i < entities.length(); i++) {
            JSONObject entity = entities.getJSONObject(i);
            Position position = new Position(entity.getInt("x"), entity.getInt("y"));
            String type = entity.getString("type");
            String id = String.valueOf(i);
            switch (type) {
                case "door":
                case "key":
                    entityList.add(createEntity(id, position, type, entity.getString("key")));
                    break;

                case "portal":
                    entityList.add(createEntity(id, position, type, entity.getString("colour")));
                    break;

                case "player":
                    entityList.add(createPlayer(id, position, type, gameMode, entityList));
                    break;

                default:
                    entityList.add(createEntity(String.valueOf(i), position, type));
                    break;
            }
        }
        return entityList;
    }

    public static Entity createEntity(String id, Position position, String type) {
        switch (type) {
            case "zombie":
                return new Zombie(id, type, position, false, 12, 12);
            
            case "spider":
                return new Spider(id, type, position, false, 4, 4);
            
            case "mercenary":
                return new Mercenary(id, type, position, true, 15, 15, 1, false);

            case "boulder":
                return new Boulder(id, type, position, false);
            
            case "zombie_toast_spawner":
                return new ZombieToastSpawner(id, type, position, true);
                
            default:
                return new Entity(id, type, position, false);
        }
    }

    public static Character createPlayer(String id, Position position, String type, String gameMode, List<Entity> entities) {
        int health = (gameMode == "Hard") ? (10):(20);
        int attack = 7;
        List<Items> inventory = new ArrayList<>();
        return new Character(id, type, position, false, health, attack, inventory, entities);
    }
    // Don't know how key entities are represented yet.
    public static Entity createEntity(String id, Position position, String type, String extra) {
        switch (type) {
            case "door":
                return new Door(id, type, position, false, false, extra);
            
            case "portal":
                return new Portal(id, type, position, false, extra);
            default:
                return null;
        }
    }
}
