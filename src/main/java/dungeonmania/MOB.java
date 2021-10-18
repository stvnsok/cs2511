package dungeonmania;

import dungeonmania.util.Position;

public class MOB extends Entity {
    private int Health;
    private int Attack;
    
    public MOB(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable);
        Health = health;
        Attack = attack;
    }

    public int getHealth() {
        return Health;
    }

    public int getAttack() {
        return Attack;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }
    
    //public void takeDamage(int damage) {}

    //public void checkArmor() {}
}
