package dungeonmania;

import dungeonmania.util.Position;

public class Exit extends Entity {

    public Exit(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    public void isComplete(Character c) {
        if (getPosition().equals(c.getPosition())){
            // end the game.
        }

        
    }
    
}
