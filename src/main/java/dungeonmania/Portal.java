package dungeonmania;

import dungeonmania.util.Position;
import java.lang.Character;

public class Portal extends Entity {

    private Position newPosition;
    private int portalId;


    public Portal(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    public void setportalId(int portalId) {
        this.portalId = portalId;
    }

    public int getportalId() {
        return portalId;
    }

    public void teleportCharacter(Position newPosition){
        setPosition(newPosition);
    }

    /*
    public void findPortal(int portalId) {
        for (Entity e: g.)
    }

    public void teleportFrom(){
        
    }

    public void teleportTo(){

    }

    */
}
