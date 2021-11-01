package dungeonmania;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.util.Node;
import dungeonmania.util.Position;

public class Mercenary extends Mob implements Enemies {
    
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

    @Override
    public void move(List<Entity> entities, Character character) {

    }
        /*Position charPosition = character.getPosition();
        Position curPos = this.getPosition();
        List<Node> paths = createMap(entities);
        Node start = null;
        Node end = null;

        for (Node node : paths) {
            if(node.getPosition().equals(curPos)) {
                start = node;
            }

            if (node.getPosition().equals(charPosition)) {
                end = node;
            }
        }

        bfs(start, end);

        Position nextPosition = getNextPosition(start, end);

        this.setPosition(nextPosition);

    }

    public void bfs(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();

        start.setVisited(true);
        queue.add(start);

        while(!queue.isEmpty()) {
            Node curNode = queue.poll();

            for(Node node: curNode.getAdjacentPath()) {
                if(!node.getVisited()) {
                    node.setVisited(true);
                    queue.add(node);
                    node.setPrevNode(curNode);

                    if(node.getPosition().equals(end.getPosition())) {
                        queue.clear();
                        break;
                    }
                }
            }
        }
    }

    public Position getNextPosition(Node start, Node end) {
        Node curNode = end;
        Node prevNode = curNode.getPrevNode();
        while(!prevNode.getPosition().equals(start.getPosition())) {
            curNode = prevNode;
            prevNode = curNode.getPrevNode();
        }
        return curNode.getPosition();
    }

    public List<Node> createMap(List<Entity> entities) {
        List<Node> paths = new ArrayList<>();
        for (int x=0; x < 21; x++) {
            for (int y=0; y < 21; y++) {
                Position curPos = new Position(x, y);
                System.out.println(curPos.toString());
                if (checkObstacles(entities,curPos)) {
                    //check adjacent obstacles
                    List<Position> adjacentPositions = curPos.getAdjacentPositions();
                    List<Node> adjacentPath = new ArrayList<>();
                    for (Position adjacentPosition : adjacentPositions) {
                        if (checkObstacles(entities, adjacentPosition) == true) {
                            adjacentPath.add(new Node(adjacentPosition, new ArrayList<>()));
                        }
                    }

                    Node node = new Node(curPos, adjacentPath);
                    paths.add(node);
                }
            }
        }
        return paths;
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
    }*/

    //public void bribe() {}


    @Override
    public void update(Character character) {
        if (character.isOn(this)) {
            // battle!
            character.battle(this);
        }
    }

}