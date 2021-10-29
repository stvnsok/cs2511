package dungeonmania;

import dungeonmania.util.Position;

public class Mercenary extends Mob implements CharacterObserver {
    
    private int bribeAmount;
    private boolean isAlly;
    
    public Mercenary(String id, String type, Position position, boolean isInteractable, int health, int attack,
            int bribeAmount, boolean isAlly) {
        super(id, type, position, isInteractable, health, attack);
        this.bribeAmount = bribeAmount;
        this.isAlly = isAlly;
    }

    public int getBribeAmount() {
        return bribeAmount;
    }
    
    public boolean isAlly() {
        return isAlly;
    }

    public void setBribeAmount(int bribeAmount) {
        this.bribeAmount = bribeAmount;
    }

    public void setAlly(boolean isAlly) {
        this.isAlly = isAlly;
    }

    //public void bribe() {}


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