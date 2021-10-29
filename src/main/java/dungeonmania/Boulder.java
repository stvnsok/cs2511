package dungeonmania;

import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends Entity {
    List<BoulderObserver> floorSwitches; // somehow get list of all floor switches

    public Boulder(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    
    // public void moveBoulder(Position position) {}

    public void moveBoulder(Direction direction) {
        Position oldPosition = getPosition();
        Position newPosition = oldPosition.translateBy(direction);

        setPosition(newPosition);

        notifySwitches(oldPosition, newPosition);
    }


    public void notifySwitches(Position oldPosition, Position newPosition) {
        for (BoulderObserver floorSwitch : floorSwitches) {
            floorSwitch.update(oldPosition, newPosition);
        }
    }
}
