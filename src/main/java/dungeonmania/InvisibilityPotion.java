package dungeonmania;

public class InvisibilityPotion extends Items {

    public InvisibilityPotion(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
    }
    
    @Override
    public void use() {
        Character character = getCharacter();
        character.setState(new InvisibleState(character));
        super.use();
    }
}
