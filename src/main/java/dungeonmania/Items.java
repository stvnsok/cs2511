package dungeonmania;

public class Items {
    
    //variable names changed to be clearer

    private String itemId;
    private String itemType; //enum(?)
    private int durability;
    private Character character;

    public Items(String itemId, String itemType, int durability, Character character) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.durability = durability;
        this.character = character;
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

    public Character getCharacter() {
        return character;
    }

    public void use() {
        this.durability = this.durability - 1;
    }
    
}
