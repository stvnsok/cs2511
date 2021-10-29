package dungeonmania;

import dungeonmania.util.Position;

public class Spider extends Mob implements CharacterObserver {

    public Spider(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable, health, attack);
    }

    @Override
    public void update(Character character) {
        if (getPosition().equals(character.getPosition())) {
            // battle!
            character.battle(this);
        }
    }
}
