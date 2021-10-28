package dungeonmania;

public class InvincibilityPotion extends Items {

    public InvincibilityPotion(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
    }

    @Override
    public void use() {
        Character character = getCharacter();
        character.setState(new InvincibleState());
        super.use();
    }

    
    
}
