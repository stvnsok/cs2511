package dungeonmania;

import java.util.List;
import java.util.ArrayList;

import dungeonmania.util.Position;

public class Entity {
    
    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;

    private List<Entity> entities = new ArrayList<>();
    
    public Entity(String id, String type, Position position, boolean isInteractable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public boolean isInteractable() {
        return isInteractable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public List<Entity> getEntities() {
        return entities;
    }
    

}
