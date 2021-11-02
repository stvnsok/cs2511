package dungeonmania;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemFactory {
    private static final int sword_durability = 7;

    public static List<Items> createInventory (JSONArray jItems) {
        List<Items> inventory = new ArrayList<>();
        for (int i = 0; i < jItems.length(); i++) {
            JSONObject jitem = jItems.getJSONObject(i);
            String id = jitem.getString("id");
            String type = jitem.getString("type");
            int durability = jitem.getInt("durability");
            Items item = null;
            if (jitem.getString("type") == "key") {
                item = createItem(id, type, durability, jitem.getString("key"));
            } else {
                item = createItem(id, type, durability);
            }
            inventory.add(item);
        }
        
        return inventory;
    }

    public static Items createItem(String id, String type) {
        switch (type) {
            case "sword":
                return createItem(id, type, sword_durability);
        
            default:
                return createItem(id, type, 1);
        }
    }

    public static Items createItem(String id, String type, int durability) {
        switch (type) {
            case "invisibility_potion":
                return new InvisibilityPotion(id, type, durability);
            
            case "invincibility_potion":
                return new InvincibilityPotion(id, type, durability);
            
            case "health_potion":
                return new HealthPotion(id, type, durability);
            
            case "bomb":
                return new Bomb(id, type, durability);
            
            default:
                return new Items(id, type, durability);
        }
    }

    public static Items createItem(String id, String type, int durability, String kID) {
        return new Key(id, type, durability, kID);
    }
}
