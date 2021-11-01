package dungeonmania;

import java.util.ArrayList;
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

    //public void PlayerMovement(Direction direction) {}
    // Under assumption argument passed is an id.
    public void useItem(String item) throws InvalidActionException {
        Items useItem = inventory.stream()
                        .filter(query -> item == query.getItemId()).findAny()
                        .orElseThrow(() -> new InvalidActionException("Item does not exist"));
        
        useItem.use(this);
    }

    //public void checkRing() {}

    public void move(Direction direction) {
        setPosition(getPosition().translateBy(direction));

        notifyObservers();
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
}
