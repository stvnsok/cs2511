package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.util.Direction;
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

<<<<<<< HEAD

    
    public void updatePosition(Position newPosition) {
        setPosition(newPosition);
    }

    public void PlayerMovement(Direction direction) {
        setPosition(getPosition().translateBy(direction));
    }
=======
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
>>>>>>> de6108a (Item tests implemented, minor changes made to CharacterState(and associated subclasses) to facilitate tests and enabled use and build to allow tests to be written. Assumptions with task updated)

    public void useItem(String item) {}

    //public void checkRing() {}
    
}
