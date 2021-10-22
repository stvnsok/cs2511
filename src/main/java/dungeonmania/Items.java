package dungeonmania;

public class Items {
    
    //variable names changed to be clearer

    private String itemId;
    private String itemType; //enum(?)
    private int durabilty;

    public Items(String itemId, String itemType, int durabilty) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.durabilty = durabilty;
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

    public int getDurabilty() {
        return durabilty;
    }

    public void setDurabilty(int durabilty) {
        this.durabilty = durabilty;
    }

    //public void use(Character) {}
    
}
