package dungeonmania;

import java.util.List;

public class Game {
    
    private String dungeonName;
    private String gameMode;
    private List<Entity> entities;
    private List<Items> inventory;
    private Character character;
    private List<String> buildables;
    private String goals;
    
    public Game(String dungeonName, String gameMode, List<Entity> entities, List<Items> inventory,
            List<String> buildables, String goals, Character character) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.entities = entities;
        this.inventory = inventory;
        this.character = character;
        this.buildables = buildables;
        this.goals = goals;
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
        return goals;
    }
<<<<<<< HEAD
    
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
=======
>>>>>>> 91b62ed (Changes UML Week 6)

    public void build(String buildable) {
        character.buildItem(buildable);
        
    }

}
