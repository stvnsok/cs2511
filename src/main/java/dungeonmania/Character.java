package dungeonmania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dungeonmania.util.Direction;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class Character extends Mob {
    
    private List<Items> inventory;
    private List<Entity> mapEntities;
    private int maxHealth;
    private CharacterState state;

    private Key currentKey; // as character can only hold one key at a time??

    private List<CharacterObserver> observers = new ArrayList<>();

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<Items> inventory, List<Entity> mapEntities) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
        this.maxHealth = health;
        this.mapEntities = mapEntities;

        this.state = new NormalState(this);
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

    public void buildItem(String item) throws InvalidActionException, IllegalArgumentException  {
        Build buildable = null;
        switch (item) {
            case "bow":
                buildable = new Bow(String.valueOf(inventory.size()), item, 3);
                break;
        
            case "shield":
                buildable = new Shield(String.valueOf(inventory.size()), item, 3);
                break;
            default:
                throw new IllegalArgumentException("Not a valid build item");
        }
        List<Map<String,Integer>> recipes = buildable.getRecipe();
        List<String> recipeItems = new ArrayList<>();
        boolean recipeFulfilled = false;
        for (Map<String,Integer> recipe : recipes) {
            // Retrieves as many items from inventory as needed to fulfill recipe. If one of components isn't fulfilled
            // move on to next recipe.
            for (String component : recipe.keySet()) {
                List<String> query = new ArrayList<>();
                query.addAll(inventory.stream()
                     .filter(i -> i.getItemType() == component)
                     .limit(recipe.get(component))
                     .map(Items::getItemId)
                     .collect(Collectors.toList()));

                if (query.size() != recipe.get(component)) {
                    recipeFulfilled = false;
                    recipeItems.clear();
                    break;
                }
                recipeItems.addAll(query);
                recipeFulfilled = true;
            }
            if (recipeFulfilled) {
                recipeItems.stream().forEach(i -> useItem(i));
                //recipeItems.stream().forEach(i -> System.out.println(i.getDurability()));
                // Casting bad, will fix later.
                inventory.add((Items) buildable);
            }
        }

    }

    public List<Entity> getEntities() {
        return this.mapEntities;
    }

    public void setEntities(List<Entity> mapEntities) {
        this.mapEntities = mapEntities;

        // set observers
        for (Entity mapEntity : mapEntities) {
            if (mapEntity instanceof CharacterObserver) {
                this.attach(mapEntity);
            }
        }
    }

    //public void PlayerMovement(Direction direction) {}
    // Under assumption argument passed is an id.
    public void useItem(String item) throws InvalidActionException {
        Items useItem = inventory.stream()
                        .filter(query -> item.equals(query.getItemId())).findAny()
                        .orElseThrow(() -> new InvalidActionException("Item does not exist"));
        
        useItem.use(this);
    }

    //public void checkRing() {}

    public void move(Direction direction) {
        //Position curPos = getPosition();
        Position newPos = getPosition().translateBy(direction);
        
        if (checkWall(mapEntities, newPos) 
        && checkMoveBoulder(mapEntities, newPos, direction)
        && checkDoor(mapEntities, newPos))
        {
            setPosition(getPosition().translateBy(direction));
            notifyObservers();
            moveBoulder(mapEntities, newPos, direction);
        }

        checkItem(mapEntities, newPos);

        goThroughPortal(mapEntities, newPos);

    }

    public void battle(Mob enemy) {
        state.battle(enemy);
    }

    public void attach(CharacterObserver observer) {
        observers.add(observer);
        
        // attach observers when generating/loading map?
    }

    public void detach(CharacterObserver observer) {
        observers.remove(observer);

        // detach observers when they are destroyed/die/become allied?
    }

    public void notifyObservers() {
        // iterate over copy of observers to prevent ConcurrentModificationException
        for (CharacterObserver observer : new ArrayList<>(observers)) {
            observer.update(this);
        }

        observers.forEach(o -> o.update(this));
    }
    
    // for testing purposes
    public boolean hasObserver(CharacterObserver observer) {
        return observers.contains(observer);
    }

    /**
     * Checks if a position contains a wall
     * @param entities
     * @param position
     * @return
     */
    public boolean checkWall(List<Entity> entities, Position position) {
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("wall") && position.equals(entPos)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a position contains an item removes the item when picked up
     * @param entities
     * @param position
     */
    public void checkItem(List<Entity> entities, Position position) {
        for (Entity entity : new ArrayList<>(entities)) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("treasure")
            || entity.getType().equals("arrow")
            || entity.getType().equals("wood")
            || entity.getType().equals("armour")
            ) {
                
                if (position.equals(entPos)) {
                    entities.remove(entity);
                }
            }

            if (entity.getType().equals("bomb") && position.equals(entPos)) {
                entities.remove(entity);
            }

            if (entity.getType().equals("sword") && position.equals(entPos)) {
                entities.remove(entity);
            }

            if (entity.getType().equals("key") && position.equals(entPos)) {
                entities.remove(entity);
            }

            if (entity.getType().equals("health_potion") && position.equals(entPos)) {
                entities.remove(entity);
            }

            if (entity.getType().equals("invisibility_potion") && position.equals(entPos)) {
                entities.remove(entity);
            }

            if (entity.getType().equals("invincibility_potion") && position.equals(entPos)) {
                entities.remove(entity);
            }
        }
    }

    /**
     * Checks if a position contains a boulder and moves the boulder
     * @param entities
     * @param position
     * @param direction
     */
    public void moveBoulder(List<Entity> entities, Position position, Direction direction) {
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();
            if (entity.getType().equals("boulder") && position.equals(entPos)) {
                Position newPos = entPos.translateBy(direction);
                if(checkWall(entities, newPos)) {
                    entity.setPosition(newPos);
                }
            }
        }
    }

    public boolean checkMoveBoulder(List<Entity> entities, Position position, Direction direction) {
        for (Entity entity : entities) {
            Position entPos = entity.getPosition();
            if (entity.getType().equals("boulder") && position.equals(entPos)) {
                for (Entity entity2 : entities) {
                    Position entPos2 = entity2.getPosition();
                    if (entity2.getType().equals("boulder") && position.translateBy(direction).equals(entPos2)) {
                        return false;
                    }
                    if (entity2.getType().equals("wall") && position.translateBy(direction).equals(entPos2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkDoor(List<Entity> entities, Position position) {
        for (Entity entity : entities) {
            Position entPos = entity.getPosition();
            if (entity.getType().equals("door") && position.equals(entPos)) {
                Door door = (Door) entity;
                if (door.isOpen() == false) {
                    //check if character have the right key
                    for(Items items : inventory) {
                        if (items.getItemType().equals("key")) {
                            Key key = (Key) items;
                            if (key.getKeyId() == (door.getKeyId())) {
                                door.setOpen(true);
                                door.setType("door_unlocked");
                                key.use(this);
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void goThroughPortal(List<Entity> entities, Position position) {
        for (Entity entity : new ArrayList<>(entities)) {

            Position entPos = entity.getPosition();

            if (entity instanceof Portal && position.equals(entPos)) {
                Portal portal = (Portal) entity;
                String portalColour = portal.getColour();

                for (Entity entity2 : new ArrayList<>(entities)) {

                    Position entPos2 = entity2.getPosition();
        
                    if (entity2 instanceof Portal && !entPos.equals(entPos2)) {
                        Portal portal2 = (Portal) entity2;
                        String portal2Colour = portal2.getColour();

                        if (portalColour.equals(portal2Colour)) {
                            this.setPosition(entPos2);
                        }
                    }
                }
            }
        }
    }


    public void destroySpawner(List<Entity> entities, Position position) {
        

        for (Entity entity : entities) {
            if (entity instanceof ZombieToastSpawner) {
                Position spawnerPos = entity.getPosition();

                List<Position> adjacentPos = spawnerPos.getAdjacentPositions();

                for (Position p : adjacentPos) {
                    if (p.equals(position)){
                        for (Items item: inventory) {
                            if(item.getItemType().equals("sword")){
                                //sword use

                                //destroy spawner
                                entities.remove(entity);
                            }
                        }
                    }

                }
            }

        }
    }
}


 