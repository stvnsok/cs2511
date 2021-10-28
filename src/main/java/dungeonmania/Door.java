package dungeonmania;

import dungeonmania.util.Position;

public class Door extends Entity {
    
    private boolean isOpen = false;
    private int doorId;

    public Door(String id, String type, Position position, boolean isInteractable, boolean isOpen) {
        super(id, type, position, isInteractable);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setDoorId(int doorId){
        this.doorId = doorId;
    }

    public int getDoorId() {
        return doorId;
    }

    public boolean open(Key key) {
        
        if (key.getKeyId() == doorId) {
            setOpen(true);
           
            
        }

        return isOpen;
    }

}
