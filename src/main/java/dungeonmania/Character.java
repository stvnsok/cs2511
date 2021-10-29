package dungeonmania;

import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Character extends Mob {
    
    private List<Items> inventory;

    private List<CharacterObserver> observers; // somehow get list of observers

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<Items> inventory) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
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

    //public void PlayerMovement(Direction direction) {}

    //public void useItem(String item) {}

    //public void checkRing() {}

    public void move(Direction direction) {
        setPosition(getPosition().translateBy(direction));

        notifyObservers();
    }

    public void notifyObservers() {
        for (CharacterObserver observer : observers) {
            observer.update(this);
        }
    }

}
