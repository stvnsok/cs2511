package StaticEntityTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


import dungeonmania.*;
import dungeonmania.Character;
import dungeonmania.response.*;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class FloorSwitchTest {
    
    @Test
    public void TestFloorSwitchTriggerBomb(){

        List<Entity> entities = new ArrayList<>();
        Boulder b = new Boulder("boulder1", "boulder", new Position(5,4), false);
        FloorSwitch floorSwitch1 = new FloorSwitch("floorSwitch1", "switch", new Position(5,5), false);
        
<<<<<<< HEAD
        Character c1 = new Character("player", "character",new Position(5,3) ,false, 100, 10, null);
=======
        Character c1 = new Character("player", "character",new Position(5,3) ,false, 100, 10, null, entities);
>>>>>>> 27288e5 (fixed some merge conflicts)
        entities.add(b);
        entities.add(floorSwitch1);
        entities.add(c1);
        c1.PlayerMovement(Direction.DOWN);
        b.moveBoulder(Direction.DOWN);
        floorSwitch1.checkFloorSwitch(b);
        assertTrue(floorSwitch1.getisTriggered());
        //Bomb bomb = new Bomb("bomb", "bomb", 1, c1);
        //c1.useItem("bomb");
        //bomb.use();

        
        entities.add(new Entity("bomb", "bomb", new Position(4,5), false));

        
        List<Entity> check = new ArrayList<>();
        check.add(c1);
        floorSwitch1.bombExplode();
        assertEquals(c1, entities);
        
        

    }
}
