package dungeonmania;

public class HealthPotion extends Items {

    public HealthPotion(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
    }

    /**
     * Restores character's health to its initial value, before reducing its uses through super.
     */
    @Override
    public void use() {
        Character character = getCharacter();
        character.setHealth(character.getMaxHealth());
        super.use();
    }
    
}
