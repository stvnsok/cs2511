package dungeonmania;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemFactory {
    public static List<Items> createInventory (JSONArray jItems) {
        List<Items> inventory = new ArrayList<>();
        for (int i = 0; i < jItems.length(); i++) {
            JSONObject jitem = jItems.getJSONObject(i);
            inventory.add(createItem(jitem.getString("id"), jitem.getString("type"), jitem.getInt("durability")));
        }
        
        return inventory;
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
