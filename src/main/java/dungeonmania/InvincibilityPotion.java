package dungeonmania;

public class InvincibilityPotion extends Items {

    public InvincibilityPotion(String itemId, String itemType, int durability) {
        super(itemId, itemType, durability);
    }
    /**
     * On potion use, set character's state to invincible
     */
    @Override
    public void use(Character character) {
        character.setState(new InvincibleState(character));
        super.use(character);
    }

    
    
}
