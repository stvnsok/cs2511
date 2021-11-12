package dungeonmania;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ItemFactory {
    private static final int sword_durability = 7;
    private static final int armour_durability = 7;

    public static List<Items> createInventory (JSONArray jItems) throws IllegalArgumentException {
        List<Items> inventory = new ArrayList<>();
        for (int i = 0; i < jItems.length(); i++) {
            JSONObject jitem = jItems.getJSONObject(i);
            String id = jitem.getString("id");
            String type = jitem.getString("type");
            int durability = jitem.getInt("durability");
            Items item = null;
            if (jitem.getString("type").contains("key")) {
                item = createItem(id, type, durability, jitem.getInt("key"));
            } else {
                item = createItem(id, type, durability);
            }
            inventory.add(item);
        }
        
        return inventory;
    }

    public static Items createItem(String id, String type) {
        Pattern itemPattern = Pattern.compile("sword|armour");
        Matcher match = itemPattern.matcher(type);
        if (match.find()) {
            switch (match.group()) {
                case "sword":
                    return createItem(id, type, sword_durability);
                
                case "armour":
                    return createItem(id, type, armour_durability);        
            }
        }
        
        return createItem(id, type, 1);
    }

    public static Items createItem(String id, String type, int durability) {
        Pattern itemPattern = Pattern.compile("invisibility_potion|invincibility_potion|health_potion|bomb|sword|armour|one_ring");
        Matcher matcher = itemPattern.matcher(type);
        if (matcher.find()) {
            switch (matcher.group()) {
                case "invisibility_potion":
                    return new InvisibilityPotion(id, type, durability);
                
                case "invincibility_potion":
                    return new InvincibilityPotion(id, type, durability);
                
                case "health_potion":
                    return new HealthPotion(id, type, durability);
                
                case "bomb":
                    return new Bomb(id, type, durability);
                
                case "sword":
                    return new Sword(id, type, durability);
    
                case "armour":
                    return new Armour(id, type, durability);
    
                case "one_ring":
                    return new TheOneRing(id, type, durability);      
            }
        }
        
        return new Items(id, type, durability);
    }

    public static Items createItem(String id, String type, int durability, int kID) {
        return new Key(id, type, durability, kID);
    }

    public static Build createBuildable(String id, String type) {
        Pattern itemPattern = Pattern.compile("sceptre|midnight_armour|bow|shield");
        Matcher matcher = itemPattern.matcher(type);
        if (matcher.find()) {
            switch (matcher.group()) {
                case "sceptre":
                    return new Sceptre(id, type, 3);
                
                case "bow":
                    return new Bow(id, type, 3);
                
                case "shield":
                    return new Shield(id, type, 5);
            
            }
        }
        // Exception should already be thrown at DungeonManiaController
        return null;
    }
}
