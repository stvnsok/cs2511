package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import dungeonmania.util.Direction;

public class Game {
    
    private String dungeonName;
    private String gameMode;
    private List<Entity> entities;
    private List<Items> inventory;
    private List<String> buildables;
    private Goals goals;
    private JSONObject jGoals;
    private Character character;
    
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

    }

}
