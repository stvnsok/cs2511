package dungeonmania;

import java.util.List;

import dungeonmania.util.Position;

public class Bomb extends Items {

    public Bomb(String itemId, String itemType, int durability) {
        super(itemId, itemType, durability);
    }

    @Override
    public void use(Character character) {
        List<Entity> entities = character.getEntities();
        Position charPos = character.getPosition();
        // Get all entities on same position as character.
        Position bombPos = new Position(charPos.getX(), charPos.getY(), charPos.getLayer());
        entities.add(new Entity(String.valueOf(entities.size()), "bomb", bombPos, false));
        

        super.use(character);
    }
    
}
