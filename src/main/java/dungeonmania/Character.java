package dungeonmania;

import java.util.List;

import dungeonmania.util.Position;

public class Character extends MOB {
    
    private List<InventoryItems> inventory;

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<InventoryItems> inventory) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
    }

    public List<InventoryItems> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItems> inventory) {
        this.inventory = inventory;
    }

    public void addInventory(InventoryItems inventoryItem) {
        this.inventory.add(inventoryItem);
    }

    //public void PlayerMovement(Direction direction) {}

    //public void useItem(String item) {}

    //public void checkRing() {}

}
