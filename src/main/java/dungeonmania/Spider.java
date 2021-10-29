package dungeonmania;

import dungeonmania.util.Position;

public class Spider extends Mob implements CharacterObserver {

    public Spider(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable, health, attack);
    }

    @Override
    public void update(Character player) {
        if (getPosition().equals(player.getPosition())) {
            // battle!
            int playerHealth = player.getHealth();
            int playerAttack = player.getAttack();

            player.takeDamage(getHealth() * getAttack() / 10);
            this.takeDamage(playerHealth * playerAttack / 5);
        }
    }
}
