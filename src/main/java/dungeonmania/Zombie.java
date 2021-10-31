package dungeonmania;

import java.util.List;
import java.util.Random;

import dungeonmania.util.Position;

public class Zombie extends Mob implements Enemies {

    public Zombie(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable, health, attack);
    }

    @Override
    public void move(List<Entity> entities) {
        Random random = new Random();
        int direction = random.nextInt(4);

        Position curPos = this.getPosition();

        if (direction == 0) { //UP
            Position newPos = new Position(curPos.getX(), curPos.getY()-1);
            checkObstacles(entities, newPos);
        } else if (direction == 1) { //DOWN
            Position newPos = new Position(curPos.getX(), curPos.getY()+1);
            checkObstacles(entities, newPos);
        } else if (direction == 2) { //LEFT
            Position newPos = new Position(curPos.getX()-1, curPos.getY());
            checkObstacles(entities, newPos);
        } else if (direction == 3) { //RIGHT
            Position newPos = new Position(curPos.getX()+1, curPos.getY());
            checkObstacles(entities, newPos);
        }
        
    }

    public void checkObstacles(List<Entity> entities, Position position) {
        boolean setPos = true;
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("Boulder")
                || entity.getType().equals("Wall")
                || entity.getType().equals("Door")) {
            
                if (position.equals(entPos)) {
                    setPos = false;
                    move(entities);
                }

            }
        }
        if (setPos) {
            this.setPosition(position);
        } 
    }
}
