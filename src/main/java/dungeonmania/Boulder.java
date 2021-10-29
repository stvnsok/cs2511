package dungeonmania;

import dungeonmania.util.Position;
import dungeonmania.util.Direction;

public class Boulder extends Entity {

    public Boulder(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    
    public void moveBoulder(Direction direction) {

        Position oldPosition = getPosition();
        Position newPosition = oldPosition.translateBy(direction);

        setPosition(newPosition);

    }

}
