package dungeonmania;

public class DeterioratableItems extends InventoryItems {
    
    private int durability;

    public DeterioratableItems(String itemId, String itemType, int durability) {
        super(itemId, itemType);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    //public void use(Character character) {}

}
