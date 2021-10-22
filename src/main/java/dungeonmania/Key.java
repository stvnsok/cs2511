package dungeonmania;

<<<<<<< HEAD
import dungeonmania.Door;
=======
>>>>>>> 91b62ed (Changes UML Week 6)
public class Key extends Items {
    
    private int keyId;

    public Key(String itemId, String itemType, int durability, int keyId, Character character) {
        super(itemId, itemType, durability, character);
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

    }
}
