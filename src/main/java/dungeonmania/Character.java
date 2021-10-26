package dungeonmania;

import java.util.List;

import dungeonmania.util.Position;

public class Character extends Mob {
    
    private List<Items> inventory;
    private int maxHealth;
    private CharacterState state;

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<Items> inventory) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
        this.maxHealth = health;
        this.state = new NormalState();
    }

    public List<Items> getInventory() {
        return inventory;
    }

    public void setInventory(List<Items> inventory) {
        this.inventory = inventory;
    }

    public void addInventory(Items inventoryItem) {
        this.inventory.add(inventoryItem);
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public CharacterState getState() {
        return this.state;
    }

    public String getStateName() {
        return state.getStateName();
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void buildItem(String item) {
        
    }

    //public void PlayerMovement(Direction direction) {}

    public void useItem(String item) {}

    //public void checkRing() {}

}
