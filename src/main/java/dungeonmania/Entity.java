package dungeonmania;

import java.util.Arrays;
import java.util.List;

import dungeonmania.util.Position;

public class Entity implements CharacterObserver {
    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;
    
    public Entity(String id, String type, Position position, boolean isInteractable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public boolean isInteractable() {
        return isInteractable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public boolean isOn(Entity entity) {
        return position.equals(entity.getPosition());
    }

    @Override
    public void update(Character character) {
        if (character.isOn(this) && isCollectable()) {
            // add collectable to inventory, remove from observers/game map (entities list)?

            // System.out.println("character on and collectable");
            collectItem(character);

            // remove from observers
            character.detach(this);

            // remove from game entities
        }

        // System.out.println("character not on/ not collectable");
        
    }

    // Checks if current entity is a collectable entity
    private boolean isCollectable() {
        List<String> collectables = Arrays.asList("treasure", "key", "health_potion", "invincibility_potion", 
        "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour");

        return collectables.contains(type);
    }


    // Adds collectable item to character's inventory
    private void collectItem(Character character) {
        switch (type) {
            case "key":
                // check if there is not already a key in inventory

                // character.addInventory(new Key(id, type, 1, keyId, character));
                // ?? no keyId anywhere
                break;

            case "health_potion":
                character.addInventory(new HealthPotion(id, type, 1));
                break;    
            
            case "invincibility_potion":
                character.addInventory(new InvincibilityPotion(id, type, 1));
                break;

            case "invisibility_potion":
                character.addInventory(new InvisibilityPotion(id, type, 1));
                break;
                
            case "bomb":
                character.addInventory(new Bomb(id, type, 1));
                break;
                
            case "sword":
                character.addInventory(new Sword(id, type, 7));
                break;
            
            case "armour":
                // character.addInventory(new Armour(id, type, 7, character)); ??
                break;
            
            default:
                // treasure, wood, and arrow
                character.addInventory(new Items(id, type, 1));
        }
    }

}
