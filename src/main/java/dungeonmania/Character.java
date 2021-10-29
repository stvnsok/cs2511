package dungeonmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

<<<<<<< HEAD
import dungeonmania.util.Direction;
=======
import dungeonmania.exceptions.InvalidActionException;
<<<<<<< HEAD
>>>>>>> 4cf6fde (Building and using regular items complete, and passes test. Items now has method-forward Character for potion/bomb use Character also now has access to list of map entities so bomb can be placed)
=======
import dungeonmania.util.Direction;
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
import dungeonmania.util.Position;

public class Character extends Mob {
    
    private List<Items> inventory;
    private List<Entity> mapEntities;
    private int maxHealth;
    private CharacterState state;

    public Character(String id, String type, Position position, boolean isInteractable, int health, int attack,
            List<Items> inventory, List<Entity> mapEntities) {
        super(id, type, position, isInteractable, health, attack);
        this.inventory = inventory;
        this.maxHealth = health;
        this.mapEntities = mapEntities;

        this.state = new NormalState(this);
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

<<<<<<< HEAD
    //public void PlayerMovement(Direction direction) {}
>>>>>>> de6108a (Item tests implemented, minor changes made to CharacterState(and associated subclasses) to facilitate tests and enabled use and build to allow tests to be written. Assumptions with task updated)
=======
    public List<Entity> getEntities() {
        return this.mapEntities;
    }
>>>>>>> 4cf6fde (Building and using regular items complete, and passes test. Items now has method-forward Character for potion/bomb use Character also now has access to list of map entities so bomb can be placed)

    //public void PlayerMovement(Direction direction) {}
    // Under assumption argument passed is an id.
    public void useItem(String item) throws InvalidActionException {
        Items useItem = inventory.stream()
                        .filter(query -> item == query.getItemId()).findAny()
                        .orElseThrow(() -> new InvalidActionException("Item does not exist"));
        
        useItem.use(this);
    }

<<<<<<< HEAD
    //public void checkRing() {}
<<<<<<< HEAD
    
=======

=======
    public void PlayerMovement(Direction direction) {
        setPosition(getPosition().translateBy(direction));
    }
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)

    public void updatePosition(Position newPosition) {
        setPosition(newPosition);
    }
<<<<<<< HEAD
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
=======


    //public void useItem(String item) {}

    //public void checkRing() {}
    
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
}
