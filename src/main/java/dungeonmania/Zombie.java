package dungeonmania;

import java.util.List;
import java.util.Random;

import dungeonmania.util.Position;

public class Zombie extends Mob implements Enemies {
    private Armour armour = null;

    public Zombie(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable, health, attack);
    }

    @Override
    public void move(List<Entity> entities, Character character) {
        Random random = new Random();
        int direction = random.nextInt(4);

        Position curPos = this.getPosition();
        Position newPos = null;

        if (character.getStateName().equals("Invincible")) {
            //runs away
            Position charPos = character.getPosition();

            if (charPos.getY() > curPos.getY()) { //character is below the spider
                newPos = new Position(curPos.getX(), curPos.getY()-1);
            } else if (charPos.getY() < curPos.getY()) { //character is above the spider
                newPos = new Position(curPos.getX(), curPos.getY()+1);
            } else if (charPos.getX() > curPos.getX()) { //character is on the right of the spider 
                newPos = new Position(curPos.getX()-1, curPos.getY());
            } else if (charPos.getX() <= curPos.getX()) { //character is on the left of the spider
                newPos = new Position(curPos.getX()+1, curPos.getY());
            }
            if(checkObstacles(entities, newPos)) {
                this.setPosition(newPos);
            }
        } else {
            if (direction == 0) { //UP
                newPos = new Position(curPos.getX(), curPos.getY()-1);
            } else if (direction == 1) { //DOWN
                newPos = new Position(curPos.getX(), curPos.getY()+1);
            } else if (direction == 2) { //LEFT
                newPos = new Position(curPos.getX()-1, curPos.getY());
            } else if (direction == 3) { //RIGHT
                newPos = new Position(curPos.getX()+1, curPos.getY());
            }
            checkObstacles(entities, newPos, character);
        }


    }

    /**
     * Checks if there are obstacles in a position
     * @param entities
     * @param position
     * @param character
     */
    public void checkObstacles(List<Entity> entities, Position position, Character character) {
        boolean setPos = true;
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("boulder")
                || entity.getType().equals("wall")
                || entity.getType().equals("door")) {
            
                if (position.equals(entPos)) {
                    setPos = false;
                    move(entities, character);
                }

            }
        }
        if (setPos) {
            this.setPosition(position);
        } 
    }

    public boolean checkObstacles(List<Entity> entities, Position position) {
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("boulder")
                || entity.getType().equals("wall")
                || entity.getType().equals("door")) {
            
                if (position.equals(entPos)) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void update(Character character) {
        if (character.isOn(this)) {
            // battle!
            character.battle(this);
        }
    }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }
}
