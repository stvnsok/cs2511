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

<<<<<<< HEAD
<<<<<<< HEAD
    public boolean checkOpen(Key key) {
        // get key from character inventory 
        
        
        if (key.getKeyId() == doorId) {
            setOpen(true);            
        }
        return isOpen;

        
    }


    public void openDoor() {
        if (isOpen == true) {
            
        }
=======
    public boolean open(Key key) {
=======
    public boolean checkOpen(Key key) {
        // get key from character inventory 
        
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
        
        if (key.getKeyId() == doorId) {
            setOpen(true);            
        }
        return isOpen;
<<<<<<< HEAD
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
=======

        
    }


    public void openDoor() {
        if (isOpen == true) {
            
        }
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
    }

}
