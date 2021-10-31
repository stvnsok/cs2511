package dungeonmania;

import dungeonmania.util.Position;

public class Exit extends Entity implements CharacterObserver {
    // private Game game;

    public Exit(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    @Override
    public void update(Character character) {
        // check if exit is last goal to be completed 

        if (character.isOn(this)) {
            // game is won
        }
    }
}
