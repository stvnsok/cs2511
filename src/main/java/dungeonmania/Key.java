package dungeonmania;

public class Key extends Items {
    
    private String keyId;

    public Key(String itemId, String itemType, int durability, String keyId) {
        super(itemId, itemType, durability);
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    //public void useKey(int doorId);
}
