package dungeonmania;

import dungeonmania.util.Position;

public class Door extends Entity {
    
    private boolean isOpen;

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

}
