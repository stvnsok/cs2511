package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Character extends Mob {
    
    private List<Items> inventory;

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<Items> inventory) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
    }

<<<<<<< HEAD


=======
>>>>>>> 91b62ed (Changes UML Week 6)
    public List<Items> getInventory() {
        return inventory;
    }

    public void setInventory(List<Items> inventory) {
        this.inventory = inventory;
    }

    public void addInventory(Items inventoryItem) {
        this.inventory.add(inventoryItem);
    }


    
    public void updatePosition(Position newPosition) {
        setPosition(newPosition);
    }

    public void PlayerMovement(Direction direction) {
        setPosition(getPosition().translateBy(direction));
    }

    //public void useItem(String item) {}

    //public void checkRing() {}
    
}
