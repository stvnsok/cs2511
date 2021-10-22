package dungeonmania;

import java.util.List;

public class Shield extends Items implements Build {

    private List<String> recipe;

    public Shield(String itemId, String itemType, int durabilty) {
        super(itemId, itemType, durabilty);
    }

    public List<String> getRecipe() {
        return recipe;
    }
    
}
