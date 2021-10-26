package dungeonmania;

import dungeonmania.util.Position;
import dungeonmania.Boulder;

public class FloorSwitch extends Entity {

    public boolean isTriggered = false;

    public FloorSwitch(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }


    
	public boolean getisTriggered() {
		return isTriggered;
	}

    public void setisTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }
 
    public void checkFloorSwitch(Boulder b) {
        if (b.getPosition().equals(getPosition())){ 
            setisTriggered(true);
        }
    }
    
}
