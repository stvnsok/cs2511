package dungeonmania;

public class InventoryItems {
    
    //variable names changed to be clearer

    private String itemId;
    private String itemType; //enum(?)

    public InventoryItems(String itemId, String itemType) {
        this.itemId = itemId;
        this.itemType = itemType;
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

    
}
