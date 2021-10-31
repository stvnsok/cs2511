package dungeonmania;

import dungeonmania.util.Position;
import dungeonmania.Boulder;

import java.util.List;

import dungeonmania.Bomb;

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

    //check if stwitch is active, if it is active check if it is adjacent
    // anthing adjacent to the bomb
    // get list of entities and get the posiiton and do is adjacent, if it is true remove them from the list
    public void bombExplode() {
        //if the switch is active
        if (isTriggered == true) {
            //if the bomb is cardinally adjacent to the switch
            // loop through all entities
            for (Entity e: getEntities()){
                // if the entity is a bomb
                if (e.getType().equals("bomb")){
                    //check if that bomb is adjacent to the floor switch
                    if(checkAdjacentToSwitch(e.getPosition())){
                        for (Entity e2: getEntities()) {
                            deleteEntities(getEntities(), e.getPosition(), e2);
                        }
                    }
                }

            }
        }
    }

    public boolean checkAdjacentToSwitch(Position bombPosition) {
        if (getPosition().isAdjacent(getPosition(), bombPosition)){
            return true;
        }

        else return false;

    }

    public void deleteEntities(List<Entity> entities, Position bombPosition, Entity e2) {
        for (Position p: bombPosition.getAdjacentPositions()) {
            if(e2.getPosition().equals(p)) {
                entities.remove(e2);
            }
        }
    }

    

    
    
}
