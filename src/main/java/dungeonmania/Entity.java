package dungeonmania;

<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;

=======
import dungeonmania.util.Direction;
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Character;

public class Entity {
    
    private String id;
    private String type;
    protected Position position;
    private boolean isInteractable;
<<<<<<< HEAD

    private List<Entity> entities = new ArrayList<>();
    
=======
    private List<Entity> entities = new ArrayList<>();
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
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

<<<<<<< HEAD
    public List<Entity> getEntities() {
        return entities;
    }
    
=======
    //this is how the character interacts with static entities
    
    public Boolean interact(Direction direction) {
        return false;
    }

    public List<Entity> getEntities() {
        return entities;
    }



>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)

}
