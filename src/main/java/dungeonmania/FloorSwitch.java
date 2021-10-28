package dungeonmania;

import dungeonmania.util.Position;

public class FloorSwitch extends Entity {
    private boolean isTriggered = false;

    public FloorSwitch(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    public boolean isTriggered() {
        return isTriggered;
    }
    
}
