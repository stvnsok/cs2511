package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Character;

public class Portal extends Entity {

    
    private Position portalPosition;
    private int portalId;
    private String colour;
    List<Entity> listPortals = new ArrayList<>();
    public Portal(String id, String type, Position position, boolean isInteractable, String colour) {
        super(id, type, position, isInteractable);
        this.colour = colour;
    }   

    public void setportalId(int portalId) {
        this.portalId = portalId;
    }

    public int getportalId() {
        return portalId;
    }

    public String getColour() {
        return colour;
    }

    public void setnewPosition(Position position) {
        portalPosition = position; 

    }

    public void ListPortal(List<Entity> Portals) {
        for (Entity e: getEntities()) {
            if (e.getType().equals("portal")) {
                listPortals.add(e);
            }
        }
    }
    
    public void findPortal(Character character, String colour) {
        
        for (Entity p: listPortals){
                if (getColour().equals(colour)) {
                    character.updatePosition(p.getPosition()); 
                }
        }
        


    }


    public void teleport(Character character,String colour){

        if (character.getPosition().equals(portalPosition)) {
            findPortal(character, colour);
            
        }

    }

    
}
