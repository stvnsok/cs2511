package dungeonmania;
<<<<<<< HEAD
import java.util.List;

<<<<<<< HEAD
<<<<<<< HEAD
import dungeonmania.util.Position;
=======
=======
=======
>>>>>>> d284196 (merge conflicts)
import java.util.List;

import dungeonmania.util.Position;

>>>>>>> 83caddc (Completed Bomb, uncommented BombTest as I figured out I can just directly give it an entity list, , states have Character now)
public class Bomb extends Items {
>>>>>>> 91b62ed (Changes UML Week 6)

<<<<<<< HEAD
public class Bomb extends Items {

<<<<<<< HEAD
=======
>>>>>>> 4cf6fde (Building and using regular items complete, and passes test. Items now has method-forward Character for potion/bomb use Character also now has access to list of map entities so bomb can be placed)
    public Bomb(String itemId, String itemType, int durability, Character character) {
        super(itemId, itemType, durability, character);
=======
    public Bomb(String itemId, String itemType, int durability) {
        super(itemId, itemType, durability);
>>>>>>> f010aa2 (Items and associated subclasses no longer stores character)
    }
    


<<<<<<< HEAD
        super.use(character);
    }
    


=======
>>>>>>> d284196 (merge conflicts)
}
