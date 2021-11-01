package dungeonmania;

<<<<<<< HEAD
import dungeonmania.Door;
=======
>>>>>>> 91b62ed (Changes UML Week 6)
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
<<<<<<< HEAD

=======
        if (door.getDoorId() == keyId) {
            door.setOpen(true);
            
        }
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
    }
}
