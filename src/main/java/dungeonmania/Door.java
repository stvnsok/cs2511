package dungeonmania;

import dungeonmania.util.Position;

public class Door extends Entity {
    private String keyId;
    private boolean isOpen;

    public Door(String id, String type, Position position, boolean isInteractable, boolean isOpen, String keyId) {
        super(id, type, position, isInteractable);
        this.isOpen = isOpen;
        this.keyId = keyId;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getKeyId() {
        return keyId;
    }

}
