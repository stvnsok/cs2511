package dungeonmania;

import dungeonmania.util.Position;
import dungeonmania.Character;
public class Boulder extends Entity {

    public Boulder(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    
    public void moveBoulder(Character character) 
        
    }

    @Override
    public Boolean interact(Character character) {
        moveBoulder(character);
        return false;
    }
}
