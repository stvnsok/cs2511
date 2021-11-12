package dungeonmania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.JSONObject;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Game {
    
    private String dungeonName;
    private String gameMode;
    private List<Entity> entities;
    private List<Items> inventory;
    private List<String> buildables;
    private Goals goals;
    private JSONObject jGoals;
    private Character character;
    private int gameTick;
    
    public Game(String dungeonName, String gameMode, List<Entity> entities, List<Items> inventory,
            List<String> buildables, JSONObject jGoals, Character character) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.entities = entities;
        this.inventory = inventory;
        this.buildables = buildables;
        this.jGoals = jGoals;
        this.character = character;
        this.goals = GoalFactory.createGoals(jGoals, this);
        this.gameTick = 0;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public String getGameMode() {
        return gameMode;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Items> getInventory() {
        return inventory;
    }

    public List<String> getBuildables() {
        return buildables;
    }

    public String getGoals() {
        return goals.getGoal();
    }

    public JSONObject getJGoals() {
        return jGoals;
    }

    public Character getCharacter() {
        return character;
    }

    public void build(String buildable) {
        /*for (Entity e : entities) {
            if (e.getType().equals("player")) {
                Character character = (Character) e;
                character.buildItem(buildable);
                break;
            }
        }*/

        
        if (buildables.contains(buildable)) {
            if(buildable.equals("bow")) {
                Bow bow = new Bow("bow"+entities.size(), "bow", 5);
                inventory.add(bow);
                buildables.remove("bow");

                int woodRemoved = 0;
                int arrowRemoved = 0;
                for(Items items : new ArrayList<>(inventory)) {
                    if(items.getItemType().equals("wood") && woodRemoved < 1) {
                        inventory.remove(items);
                        woodRemoved++;
                    } else if (items.getItemType().equals("arrow") && arrowRemoved < 3) {
                        inventory.remove(items);
                        arrowRemoved++;
                    }

                }
            } else if(buildable.equals("shield")) {
                Shield shield = new Shield("shield"+entities.size(), "shield", 5);
                inventory.add(shield);
                buildables.remove("shield");

                int woodRemoved = 0;
                int keyRemoved = 0;
                int treasureRemoved = 0;
                for(Items items : new ArrayList<>(inventory)) {
                    if(items.getItemType().equals("wood") && woodRemoved < 2) {
                        inventory.remove(items);
                        woodRemoved++;
                    } else if (items.getItemType().equals("treasure") && treasureRemoved < 1 && keyRemoved < 1) {
                        inventory.remove(items);
                        treasureRemoved++;
                    } else if (items.getItemType().equals("key") && keyRemoved < 1 && treasureRemoved < 1) {
                        inventory.remove(items);
                        keyRemoved++;
                    }

                }
            }
        }
        
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public boolean hasEntity(Entity entity) {
        return entities.contains(entity);
    }

    public void tick(String itemUsed, Direction movementDirection) {
        character.move(movementDirection);

        int numWood = 0;
        int numArrows = 0;
        int numTreasure = 0;
        int numKey = 0;
        for (Items items : new ArrayList<>(inventory)) {
            if (items.getItemId().equals(itemUsed)) {
                items.use(character);
            }

            if (items.getItemType().equals("wood")) {
                numWood++;
            } else if (items.getItemType().equals("arrow")) {
                numArrows++;
            } else if (items.getItemType().equals("treasure")) {
                numTreasure++;
            } else if (items.getItemType().equals("key")) {
                numKey++;
            }

        }

        for (Entity entity : new ArrayList<>(entities)) {
            if (entity instanceof Enemies) {
                Enemies enemy = (Enemies) entity;
                enemy.move(entities, character);
            }
        }

        if (numWood > 0 && numArrows > 2 && !buildables.contains("bow")) {
            buildables.add("bow");
        }
        if (numWood > 1 && numTreasure > 0 && !buildables.contains("shield")) {
            buildables.add("shield");
        } else if (numWood > 1 && numKey > 0 && !buildables.contains("shield")) {
            buildables.add("shield");
        }

        //zombieToastSpawner
        int spawnTick;
        if (gameMode.equals("Hard")) {
            spawnTick = 15;    
        } else {
            spawnTick = 20;
        }

        if(gameTick%spawnTick == 0) {
            for (Entity entity : new ArrayList<>(entities)) {
                if (entity instanceof ZombieToastSpawner) {
                    Zombie zombie = new Zombie(System.currentTimeMillis()+"zombie", "zombie", entity.getPosition(), false, 10, 10);
                    entities.add(zombie);
                }
            }
        }    

        //spider spawning
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;
        int spiderNum = 0;
        
        for (Entity entity : entities) {
            if (entity.getPosition().getX() > maxX) {
                maxX = entity.getPosition().getX();
            }
            if (entity.getPosition().getY() > maxY) {
                maxY = entity.getPosition().getY();
            }
            if (entity.getPosition().getX() < minX) {
                minX = entity.getPosition().getX();
            }
            if (entity.getPosition().getY() < minY) {
                minY = entity.getPosition().getY();
            }
            if (entity instanceof Spider) {
                spiderNum++;
            }

        }

        //System.out.println(maxX +"|"+ maxY +"|"+ minX +"|"+ minY);

        Random random = new Random();
        //generate random percentage
        if (random.nextInt(5) == 0 && spiderNum < 4) {
            //generate random position
            Position randPos = new Position(random.nextInt((maxX - minX + 1) + minY), random.nextInt((maxY - minY + 1)) + minY);
            Spider spider = new Spider(System.currentTimeMillis()+"spider", "spider", randPos, false, 10, 3);
            entities.add(spider);
        }

        gameTick++;
    }

    public void interact(String entityId) {
        int treasureNum = 0;
        for(Entity entity : new ArrayList<>(entities)) {
            if(entity.getId().equals(entityId) && entity instanceof Mercenary && entity.getPosition().getAdjacentPositions2().contains(character.getPosition())) {
                Mercenary mercenary = (Mercenary) entity;

                for(Items item : inventory) {
                    if (item.getItemType().equals("treasure")) {
                        treasureNum++;
                        
                    } 
                }
                
                if (treasureNum >= mercenary.getBribeAmount()) {
                    
                    mercenary.bribe();
                    mercenary.setInteractable(false);
                    
                    int i = mercenary.getBribeAmount();
                    for(Items item : new ArrayList<>(inventory)) {
                        if (i  == 0) {
                            break;
                        }
                        if (item.getItemType().equals("treasure")) {
                            inventory.remove(item);
                            i--;
                        }
    
                    }
                }
            }
        }
    }


    public void interactSpawner(String entityId){
        List<Entity> toRemove = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getId().equals(entityId) && entity instanceof ZombieToastSpawner) {
                
                Position spawnerPos = entity.getPosition();
                List<Position> adjacentPos = spawnerPos.getAdjacentPositions();

                for (Position p : adjacentPos) {
                    if (p.equals(character.getPosition())){
                        for (Items i: inventory) {
                            if (i.getItemType().equals("sword")){
                                toRemove.add(entity);
                            }
                        }
                    
                    }

                }
                
            }
        }
        // small brain method to deal with concurrentmodifcationexception but at least it works
        entities.removeAll(toRemove); 
    }
}
