package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;
import dungeonmania.Character;

public class Portal extends Entity {

    Portal p;
    private Position portalPosition;
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

    public void setPortal(Portal p) {
        this.p = p;
    }

    public Portal getPortal() {
        return p;
    }

    public void setnewPosition(Position position) {
        portalPosition = position; 

    }
    
    public void findPortal(Character character, int id) {
        
        for (Entity e: getEntities()){
            if (e.getType().equals("portal")){
                
                if (p.getportalId() == id && p.getId() != e.getId()) {
                    character.updatePosition(p.getPosition()); 
                }
            }
        }
        


    }


    public void teleport(Character character,int portalId){

        if (character.getPosition().equals(portalPosition)) {
            findPortal(character, portalId);
            
        }

    }

    
}
