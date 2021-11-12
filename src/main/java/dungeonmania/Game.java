package dungeonmania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.JSONObject;

import dungeonmania.exceptions.InvalidActionException;
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
            character.buildItem(buildable);
            buildables.remove(buildable);
            /*if(buildable.equals("bow")) {
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
            }*/
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

    public void tick(String itemUsed, Direction movementDirection) throws IllegalArgumentException, InvalidActionException {
        character.move(movementDirection);

        int numWood = 0;
        int numArrows = 0;
        int numTreasure = 0;
        int numStone = 0;
        // Won't bother checking if item is not uses(null)
        int itemExists = itemUsed == null ? -1 : 0;
        int numKey = 0;
        List<String> validUse = new ArrayList<>(Arrays.asList("health_potion","invisibility_potion",
                                                              "invincibility_potion","bomb"));
        for (Items items : new ArrayList<>(inventory)) {
            if (items.getItemId().equals(itemUsed)) {
                if (!validUse.stream().anyMatch(e -> e.equals(items.getItemType()))) {
                    throw new IllegalArgumentException("Not a valid use item");
                }
                itemExists = 1;
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
            } else if (items.getItemType().equals("sun_stone")) {
                numStone++;
            }

        }
        // Item id is not null, but does not exist in inventory
        if (itemExists == 0) {
            throw new InvalidActionException("Item does not exist");
        }

        for (Entity entity : new ArrayList<>(entities)) {
            if (entity instanceof Enemies) {
                Enemies enemy = (Enemies) entity;
                enemy.move(entities, character);
            }
            if (entity instanceof Mercenary) {
                Mercenary merc = (Mercenary) entity;
                merc.controlTick();
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
        if (numWood > 0 || numArrows > 1) {
            if (numKey + numTreasure > 0 && numStone > 0 && buildables.stream().noneMatch(e -> e.equals("sceptre"))) {
                buildables.add("sceptre");
            }
        }

        //zombieToastSpawner
        if(gameTick%20 == 0) {
            for (Entity entity : new ArrayList<>(entities)) {
                if (entity instanceof ZombieToastSpawner) {
                    Zombie zombie = new Zombie(System.currentTimeMillis()+"zombie", "zombie", entity.getPosition(), false, 10, 10);
                    entities.add(zombie);
                }
            }
        }    

        gameTick++;
    }

    public void interact(String entityId) {
        for(Entity entity : entities) {
            if(entity.getId().equals(entityId) && entity instanceof Mercenary) {
                Mercenary mercenary = (Mercenary) entity;
                if (inventory.stream().anyMatch(e -> e.getItemType().equals("sceptre"))) {
                    mercenary.control(character, inventory.stream().filter(e -> e.getItemType().equals("sceptre")).findFirst().get());
                } else {
                    mercenary.bribe();
                }
                
            }
        }
    }



    public void interactSpawner(String entityId) throws InvalidActionException {
       
        for (Entity entity : new ArrayList<>(entities)) {

            if (entity.getId().equals(entityId) && entity instanceof ZombieToastSpawner) {
                boolean destroyed = false;
                Position spawnerPos = entity.getPosition();
                List<Position> adjacentPos = spawnerPos.getCardinallyAdjacentPosition();

                for (Position p : adjacentPos) {
                    if (character.getPosition().equals(p)){
                        if (!inventory.stream().anyMatch(e -> e.getItemType().equals("sword") 
                                                        || e.getItemType().equals("anduril"))) {
                            throw new InvalidActionException("You need a weapon to destroy this");
                        }
                        entities.remove(entity);
                        destroyed = true;
                        break;                   
                    }

                }
                if (!destroyed) {
                    throw new InvalidActionException("You need to be adjacent to the spawner to destroy it");
                }
                
            }
        }

    }
}
