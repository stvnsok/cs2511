package dungeonmania;

import java.util.List;

import org.json.JSONObject;

public class Game {
    
    private String dungeonName;
    private String gameMode;
    private List<Entity> entities;
    private List<Items> inventory;
    private Character character;
    private List<String> buildables;
    private Goals goals;
    private JSONObject jGoals;
    
    public Game(String dungeonName, String gameMode, List<Entity> entities, List<Items> inventory,
            List<String> buildables, JSONObject jGoals, Character character) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.entities = entities;
        this.inventory = inventory;
        this.character = character;
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
        character.buildItem(buildable);
        
    }

}
