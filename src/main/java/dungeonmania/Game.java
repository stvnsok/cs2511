package dungeonmania;

import java.util.List;

import org.json.JSONObject;

public class Game {
    
    private String dungeonName;
    private String gameMode;
    private List<Entity> entities;
    private List<Items> inventory;
    private List<String> buildables;
    private Goals goals;
    private JSONObject jGoals;
    
    public Game(String dungeonName, String gameMode, List<Entity> entities, List<Items> inventory,
            List<String> buildables, JSONObject jGoals) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.entities = entities;
        this.inventory = inventory;
        this.buildables = buildables;
        this.jGoals = jGoals;
        this.goals = GoalFactory.createGoals(jGoals);
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

    public void build(String buildable) {
        for (Entity e : entities) {
            if (e.getType() == "player") {
                Character character = (Character) e;
                character.buildItem(buildable);
                break;
            }
        }
        
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public boolean hasEntity(Entity entity) {
        return entities.contains(entity);
    }
}
