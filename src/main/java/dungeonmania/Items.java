package dungeonmania;

import java.util.List;

public class Items {
    
    //variable names changed to be clearer

    private String itemId;
    private String itemType; //enum(?)
    private int durability;

    public Items(String itemId, String itemType, int durability) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.durability = durability;
    }

    public String getItemId() {
        return itemId;
    }
    
    public String getItemType() {
        return itemType;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void use(Character character) {
        this.durability = this.durability - 1;
        if (this.durability == 0) {
            List<Items> inventory = character.getInventory();
            inventory.remove(this); 
        }
    }
    
}
