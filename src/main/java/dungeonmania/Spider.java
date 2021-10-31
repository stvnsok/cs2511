package dungeonmania;

import java.util.List;

import dungeonmania.util.Position;

public class Spider extends Mob implements Enemies {

    private int moveCycle;
    private boolean dirClockwise;

    public Spider(String id, String type, Position position, boolean isInteractable, int health, int attack) {
        super(id, type, position, isInteractable, health, attack);
        moveCycle = 0;
        dirClockwise = true;
    }

    @Override
    public void move(List<Entity> entities) {
        
        Position curPos = this.getPosition();

        if (dirClockwise == true) {
            if (moveCycle == 0 || moveCycle == 6 || moveCycle == 7) { //UP
                Position newPos = new Position(curPos.getX(), curPos.getY()-1);
                checkBoulder(entities, newPos);

            } else if (moveCycle == 1 || moveCycle == 8) { //RIGHT
                Position newPos = new Position(curPos.getX()+1, curPos.getY());
                checkBoulder(entities, newPos);
                
            } else if (moveCycle == 2 || moveCycle == 3) { //DOWN
                Position newPos = new Position(curPos.getX(), curPos.getY()+1);
                checkBoulder(entities, newPos);
                
            } else if (moveCycle == 4 || moveCycle == 5) { //LEFT
                Position newPos = new Position(curPos.getX()-1, curPos.getY());
                checkBoulder(entities, newPos);
                
            }
        } else {
            if (moveCycle == 0 || moveCycle == 7 || moveCycle == 8) { //DOWN
                Position newPos = new Position(curPos.getX(), curPos.getY()+1);
                checkBoulder(entities, newPos);
                
            } else if (moveCycle == 1 || moveCycle == 2) { //LEFT
                Position newPos = new Position(curPos.getX()-1, curPos.getY());
                checkBoulder(entities, newPos);
    
            } else if (moveCycle == 3 || moveCycle == 4) { //UP
                Position newPos = new Position(curPos.getX(), curPos.getY()-1);
                checkBoulder(entities, newPos);
                
            } else if (moveCycle == 5 || moveCycle == 6) { //RIGHT
                Position newPos = new Position(curPos.getX()+1, curPos.getY());
                checkBoulder(entities, newPos);
            }
        }
    }

    /**
     * Checks if a boulder is in a position
     * if true then change direcion
     * if false then sets spider position to new position
     * @param entities
     * @param position
     */
    public void checkBoulder(List<Entity> entities, Position position) {
        boolean setPos = true;
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("Boulder") && position.equals(entPos)) {
                changeDirection();
                setPos = false;
                move(entities);
            }
        }
        if (setPos == true) {
            this.setPosition(position);
            if (dirClockwise) {
                if (moveCycle == 8) {
                    moveCycle = 1;
                } else {
                    moveCycle++;
                }
            } else {
                if (moveCycle == 1) {
                    moveCycle = 8;
                } else {
                    moveCycle--;
                }
            }


        }
        
    }

    /**
     * change direction of spider
     */
    public void changeDirection() {
        if (dirClockwise == true) {
            dirClockwise = false;
        } else {
            dirClockwise = true;
        }
    }

    @Override
    public void update(Character character) {
        if (getPosition().equals(character.getPosition())) {
            // battle!
            character.battle(this);
        }
    }
}

