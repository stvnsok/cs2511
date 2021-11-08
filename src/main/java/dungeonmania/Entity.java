package dungeonmania;

import java.util.ArrayList;
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
        List<Entity> entities = character.getEntities();

        for(Entity entity : new ArrayList<>(entities)) {
            if (entity.getType().equals("bomb") && checkSwitchOn(entities, entity.getPosition())) {
                explode(entities, entity.getPosition());
            }
        }

    }

    // Checks if current entity is a collectable entity
    private boolean isCollectable() {
        List<String> collectables = Arrays.asList("treasure", "key", "health_potion", "invincibility_potion", 
        "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour");

        return collectables.contains(type);
    }


    // Adds collectable item to character's inventory
    private void collectItem(Character character) {
<<<<<<< HEAD
        switch (type) {
            case "key":
                // check if there is not already a key in inventory

                character.addInventory(new Key(id, type, 1, "1"));
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
                character.addInventory(new Armour(id, type, 7));
                break;
                
            default:
                // treasure, wood, and arrow
                character.addInventory(new Items(id, type, 1));
        }
=======
        character.addInventory(ItemFactory.createItem(id, type));
>>>>>>> master
    }

    public void explode(List<Entity> entities, Position position) {
        List<Position> adjacentPositions = position.getAdjacentPositions();

        for (Entity entity : new ArrayList<>(entities)) {
            Position entPos = entity.getPosition();

            if (!entity.getType().equals("player") && adjacentPositions.contains(entPos)) {
                entities.remove(entity);
            }

            if (!entity.getType().equals("player") && position.equals(entPos)) {
                entities.remove(entity);
            }

        }
    }

    public boolean checkSwitchOn(List<Entity> entities, Position position) {
        List<Position> adjacentPositions = position.getAdjacentPositions();

        for (Entity entity : new ArrayList<>(entities)) {
            Position entPos = entity.getPosition();

            if (entity.getType().equals("switch") && adjacentPositions.contains(entPos) && checkBoulder(entities, entPos)) {
                return true;
            }

        }
        return false;
    }

    public boolean checkBoulder(List<Entity> entities, Position position) {
        for (Entity entity : new ArrayList<>(entities)) {
            Position entPos = entity.getPosition();

            if (entity.getType().equals("boulder") &&  position.equals(entPos)) {
                return true;
            }

        }
        return false;
    }

}
