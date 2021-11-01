package dungeonmania;

import dungeonmania.util.Position;
<<<<<<< HEAD
import dungeonmania.util.Direction;

=======
import dungeonmania.Character;
import dungeonmania.util.Direction;
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
public class Boulder extends Entity {

    
    public Boulder(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
    public void moveBoulder(Direction direction) {

=======
    public void moveBoulder(Direction direction) 
=======
    public void moveBoulder(Direction direction) {
>>>>>>> 27288e5 (fixed some merge conflicts)
    
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)
        Position oldPosition = getPosition();
        Position newPosition = oldPosition.translateBy(direction);

        setPosition(newPosition);
<<<<<<< HEAD

    }
=======
>>>>>>> bbcc3d2 (fixed merge conflicts with local branch)

    }

    @Override
    public Boolean interact(Direction direction) {
        moveBoulder(direction);
        return false;
    }
}
