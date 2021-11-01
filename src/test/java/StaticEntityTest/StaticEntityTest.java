package StaticEntityTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import dungeonmania.*;


import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
public class StaticEntityTest {
    
    //Tests the creation of all static entities
    @Test
    public void createEntitiesTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");

        Position p1 = new Position(3, 3, 0);
<<<<<<< HEAD
        Boulder boulder = new Boulder("boulder1", "boulder", p1, false);
=======
        Boulder boulder = new Boulder("boulder1", "boulder", p1, true);
>>>>>>> 27288e5 (fixed some merge conflicts)
        assertEquals(p1, boulder.getPosition());


        Position p2 = new Position(4,3, 0);
        Door door = new Door("door1", "Door", p2, false, false);

        assertEquals(p2, door.getPosition());


        Position p3 = new Position(5,3, 0);
<<<<<<< HEAD
        FloorSwitch fswitch = new FloorSwitch("floorswitch1", "switch", p3, false);
=======
        FloorSwitch fswitch = new FloorSwitch("floorswitch1", "floorswitch", p3, false);
>>>>>>> 27288e5 (fixed some merge conflicts)
        assertEquals(p3, fswitch.getPosition());

        

        Position p4 = new Position(6,3,0);
<<<<<<< HEAD
        Portal portal = new Portal("portal1", "portal", p4, false, "BLUE");
        assertEquals(p4, portal.getPosition());
 

        
        Position p5 = new Position(7,3,0);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombie_toast_spawner", p5, false);
        assertEquals(p5, z1.getPosition());

        Position p6 = new Position (8,3,0);
        Exit e = new Exit("exit1", "exit", p6, false);
        assertEquals(p6, e.getPosition());
=======
        Portal portal = new Portal("portal1", "portal", p4, true, "BLUE");
        assertEquals(p4, portal.getPosition());


        
        Position p5 = new Position(7,3,0);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombietoastspawner", p5, true);
        assertEquals(p5, z1.getPosition());


        Position p6 = new Position (8,3,0);
        Exit e = new Exit("exit1", "exit", p6, true);
        assertEquals(p6, e.getPosition());

>>>>>>> 27288e5 (fixed some merge conflicts)

    }

     


}
