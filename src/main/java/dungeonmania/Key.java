package dungeonmania;

import dungeonmania.Door;
public class Key extends Items {
    
    private int keyId;

    public Key(String itemId, String itemType, int durability, int keyId) {
        super(itemId, itemType, durability);
        this.keyId = keyId;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    // might be implemented differently depending on items class
    public void useKey(Door door) {
        if (door.getDoorId() == keyId) {
            door.setOpen(true);
            
        }

}
