package dungeonmania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import dungeonmania.util.Position;

public class Mercenary extends Mob implements Enemies {
    
    private int bribeAmount;
    private int controlDuration = -1;
    private boolean isAlly;
    private Armour armour;
    //private Map<Position, Double> Grid = new HashMap<Position, Double>();

    private int infinity = 100000;
    
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

    /*@Override
    public void move(List<Entity> entities, Character character) {
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
    }*/
    
    public Map<Position,Integer> allPositions(List<Entity> entities) {
        Map <Position, Integer> Grid = new HashMap<>();
        Integer d = 0;
        // assume a fixed size for the grid
        // furthest x and y wall 

        List<Integer> xList = new ArrayList<Integer>();
        List<Integer> yList = new ArrayList<Integer>();
        for (Entity e: entities) {
            if (e.getType().equals("wall")) {
                xList.add(e.getPosition().getX());
                yList.add(e.getPosition().getY());
            }
        }
        
        for (int x=0; x < Collections.max(xList); x++) {
            for (int y=0; y < Collections.max(yList); y++) {
                Position curPos = new Position(x, y); 

                Grid.put(curPos, d);
                d++;
                
            }
        }
        return Grid;
    }


    /**
     * Moves the mercenary based on Djikstra's algorithm.
     */
    @Override
    public void move(List<Entity> entities, Character character) {

        Map<Position, Integer> Grid = allPositions(entities);
        Position source = this.getPosition();

        Map<Position, Integer> dist = new HashMap<>();
        List<Position> settled = new ArrayList<>();

        //put all the positions of the dungeon size
        for (Map.Entry<Position, Integer> set : Grid.entrySet()) {
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

        dist.replace(source, 0);
        // comparator to use for priority queue based on dist.
        class posDistCompare implements Comparator<Position> {
            public int compare(Position a, Position b) {
                return dist.get(a) - dist.get(b);
            }
        }

        Queue<Position> queue = new PriorityQueue<>(1, new posDistCompare());
        //add source node to queue
        queue.add(source);

        while(!queue.isEmpty()) {
            
            Position evaluated = queue.peek();
            List<Position> adjacentPositions = evaluated.getCardinallyAdjacentPosition();
            for (Position v: adjacentPositions) {
                // Do not consider positions that are out of the map.
                if (dist.containsKey(v)) {
                    if (dist.get(evaluated) + cost(entities, v) < dist.get(v)) {
                        dist.put(v, dist.get(evaluated) + cost(entities, v));
                        prev.put(v, evaluated);
                    }
                    if (!queue.contains(v) && !settled.contains(v)) {
                        queue.add(v);
                    }
                }  
            }
            // Do not consider nodes again that have already been evaluated.
            queue.remove(evaluated);
            settled.add(evaluated);
        }
        Position nextMove = character.getPosition();
        // route the path all the way back to the mercenary.
        while (!prev.get(nextMove).equals(this.getPosition())) {
            nextMove = prev.get(nextMove);
        }
        this.setPosition(nextMove);
        if (this.isOn(character) && !isAlly) {
            character.battle(this);
        }
    }
    /**
     * Calculates cost of moving to a particular tile. Returned values correspond to impassable objects and swamp.
     * @param entities
     * @param tile
     * @return
     */
    public Integer cost(List<Entity> entities, Position tile){
        List<Entity> onTile = new ArrayList<>(entities.stream().filter(e -> e.getPosition().equals(tile))
                                            .collect(Collectors.toList()));
        List<String> impassable = new ArrayList<>(Arrays.asList("wall", "zombie_toast_spawner", "boulder", "door"));
        if (onTile.size() > 0) {
            for (Entity e : onTile) {
                String eType = e.getType();
                // Can't pass through these entities in any case.
                if (impassable.contains(eType)) {
                    return 1000;
                } else if (eType.equals("swamp_tile")) {
                    // Have not implemented swamp tiles yet.
                    return 2;
                }
            }
        }
        // There are no entities on the position: empty space so cost is 1.
        return 1;
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
            setInteractable(true);
            setAlly(false);
        }
    }

    public void control(Character character, Items sceptre) {
        setAlly(true);
        setInteractable(false);
        setControlDuration(10);
        sceptre.use(character);
    }

    public boolean checkPlayer(List<Entity> entities, Position position) {
        for (Entity entity : entities) {

            Position entPos = entity.getPosition();

            if (entity.getType().equals("player")) {
            
                if (position.equals(entPos)) {
                    return false;
                }

            }
        }
        return true;
    }

    public void bribe() {
        //System.out.println("BRIBED");
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