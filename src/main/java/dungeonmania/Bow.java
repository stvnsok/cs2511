package dungeonmania;

<<<<<<< HEAD
<<<<<<< HEAD
public class Bow extends Items implements Build{
=======
=======
import java.util.ArrayList;
>>>>>>> 4cf6fde (Building and using regular items complete, and passes test. Items now has method-forward Character for potion/bomb use Character also now has access to list of map entities so bomb can be placed)
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Bow extends Items implements Build{
    // Making it list of map here since there could be multiple recipes.
    private List<Map<String, Integer>> recipe = new ArrayList<>();

<<<<<<< HEAD
    private List<String> recipe;
>>>>>>> 91b62ed (Changes UML Week 6)

    public Bow(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
=======
    public Bow(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
        Map <String, Integer> recipe = new HashMap<>();
        recipe.put("wood", 1);
        recipe.put("arrow", 3);
        this.recipe.add(recipe);
>>>>>>> 4cf6fde (Building and using regular items complete, and passes test. Items now has method-forward Character for potion/bomb use Character also now has access to list of map entities so bomb can be placed)
    }

    public List<Map<String, Integer>> getRecipe() {
        return recipe;
    }

}
