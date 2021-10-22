package dungeonmania;

<<<<<<< HEAD
public class Shield extends Items implements Build {

    public Shield(String itemId, String itemType, int durability, Character character ) {
        super(itemId, itemType, durability, character);
=======
import java.util.List;

public class Shield extends Items implements Build {

    private List<String> recipe;

    public Shield(String itemId, String itemType, int durabilty) {
        super(itemId, itemType, durabilty);
    }

    public List<String> getRecipe() {
        return recipe;
>>>>>>> 91b62ed (Changes UML Week 6)
    }
    
}
