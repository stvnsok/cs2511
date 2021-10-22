package dungeonmania;

<<<<<<< HEAD
public class Bow extends Items implements Build{
=======
import java.util.List;

public class Bow extends Items implements Build{

    private List<String> recipe;
>>>>>>> 91b62ed (Changes UML Week 6)

    public Bow(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
    }

    public List<String> getRecipe() {
        return recipe;
    }

}
