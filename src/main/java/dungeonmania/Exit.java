package dungeonmania;

import dungeonmania.util.Position;

public class Exit extends Entity implements CharacterObserver {

    public Exit(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    @Override
    public void update(Character player) {
        // check if exit is last goal to be completed 

        if (getPosition().equals(player.getPosition())) {
            // game is won
        }
    }
}
