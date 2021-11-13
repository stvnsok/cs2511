package dungeonmania;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map.Entry;



import dungeonmania.util.Direction;
import dungeonmania.util.Node;
import dungeonmania.util.Position;

public class Mercenary extends Mob implements Enemies {
    
    private int bribeAmount;
    private int controlDuration = -1;
    private boolean isAlly;
    private Armour armour;
    //private Map<Position, Double> Grid = new HashMap<Position, Double>();

    private Double infinity = 1000000.00;
    
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

    @Override
    public void move(List<Entity> entities, Character character) {
        /*
        Position charPosition = character.getPosition();
        Position curPos = this.getPosition();
        Position diff = Position.calculatePositionBetween(charPosition, curPos);
        Position newPos = curPos;

        if (Math.abs(diff.getX()) > Math.abs(diff.getY())) { //move sideways 
            if (diff.getX() > 0) {
                newPos = new Position(curPos.getX()-1, curPos.getY());
            } else if (diff.getX() < 0) {
                newPos = new Position(curPos.getX()+1, curPos.getY());
            }
        } else if (Math.abs(diff.getX()) < Math.abs(diff.getY())) {
            if (diff.getY() > 0) {
                newPos = new Position(curPos.getX(), curPos.getY()-1);
            } else if (diff.getY() < 0) {
                newPos = new Position(curPos.getX(), curPos.getY()+1);
            }
        }

        if (checkObstacles(entities, newPos)) {
            this.setPosition(newPos);
        }

        if (this.isOn(character) && !isAlly) {
            character.battle(this);
        }
        */

        Map<Position, Position> prev = pathFinding(entities, character);

        
        Position newPos = prev.get(character.getPosition());
        while (newPos != this.getPosition()) {
             newPos = prev.get(newPos);
        }
        this.setPosition(newPos);


    }
    
    public Map<Position,Double> allPositions(List<Entity> entities) {
        Map <Position, Double> Grid = new HashMap<>();
        double d = 0.00;
        // assume a fixed size for the grid
        // furthest x and y wall 

        /*
        List<Integer> xList = new ArrayList<Integer>();
        List<Integer> yList = new ArrayList<Integer>();
        for (Entity e: entities) {
            if (e.getType().equals("wall")){
                xList.add(e.getPosition().getX());
                yList.add(e.getPosition().getY());
            }
        }
        
        */
        // all game maps are 50x50

        for (int x=0; x < 50; x++) {
            for (int y=0; y < 50; y++) {
                Position curPos = new Position(x, y); 

                Grid.put(curPos, d);
                d++;
                
            }
        }
        return Grid;
    }




    public Map<Position, Position> pathFinding(List<Entity> entities, Character character) {

        Map<Position, Double> Grid = allPositions(entities);
        Position source = this.getPosition();

        Map<Position, Double> dist = new HashMap<Position, Double>();

        //put all the positions of the dungeon size
        for (Map.Entry<Position, Double> set : Grid.entrySet()) {
            dist.put(set.getKey(), set.getValue());
        }
        Map<Position, Position> prev = new HashMap<Position, Position>();

        for (Position p : Grid.keySet()) {
            prev.put(p, p);
        }

        for (Position p: Grid.keySet()) {
            dist.replace(p, infinity);
            prev.replace(p, null);
        }

        dist.replace(source, 0.00);

        LinkedList<Position> queue = new LinkedList<>(Grid.keySet());
        //add source node to queue
        queue.add(source);

        while(!queue.isEmpty()) {
            
            List<Position> adjacentPositions = source.getCardinallyAdjacentPosition();
            
            for (Position v: adjacentPositions) {
                
                for (Map.Entry<Position, Double> set : dist.entrySet()) {
                    double d = (double)dist.get(v);
                    
                    if ((set.getValue() + cost(entities, v)) < d) {
                        System.out.println(queue.toString());
                        dist.replace(v, (set.getValue() + cost(entities, v)));
                        prev.replace(v, set.getKey());
                        
                    }
                    //queue.remove(source);
                }
                
            }
            queue.remove(source);

        }
        //queue.remove(source);
        return prev;

    }

    public Double cost(List<Entity> entities, Position tile){

        for (Entity e: entities) {
            if (e.getPosition().equals(tile)) {
                if (e.getType().equals("wall")|| e.getType().equals("boulder")) {
                    return 1000.00;
    
                }
    
                else if (e instanceof SwampTile ) {
                    // need to get ticks some how
                    return 2.00;
                }
    
                else {
                    return 1.00;
                }
            }

        }
        return 1.00;

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
    public int getControlDuration() {
        return controlDuration;
    }

    public void setControlDuration(int controlDuration) {
        this.controlDuration = controlDuration;
    }

    public void controlTick() {
        this.controlDuration -= 1;
        if (controlDuration == 0) {
            setAlly(false);
        }
    }

    public void control(Character character, Items sceptre) {
        setAlly(true);
        setControlDuration(10);
        sceptre.use(character);
    }

    public void bribe() {
        System.out.println("BRIBED");
        setAlly(true);
    }


    // @Override
    // public void update(Character character) {
    //     if (character.isOn(this)) {
    //         // battle!
    //         character.battle(this);
    //     }
    // }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }
}