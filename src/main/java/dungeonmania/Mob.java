package dungeonmania;

import dungeonmania.util.Position;

public class Mob extends Entity {
    private int health;
    private int attack;

    public Mob(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable);
        this.health = health;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public void takeDamage(int damage) {
        health = health - damage;
    }

    public void increaseHealth(int damage) {
        health = health + damage;
    }

    //public void checkArmor() {}
}
