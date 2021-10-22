package dungeonmania;

import java.util.List;

public class Bow extends Items implements Build{

    private List<String> recipe;

    public Bow(String itemId, String itemType, int durability) {
        super(itemId, itemType, durability);
    }

    public List<String> getRecipe() {
        return recipe;
    }

}
