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

        Position p1 = new Position(3, 3);
        Boulder boulder = new Boulder("boulder1", "boulder", p1, true);
        assertEquals(p1, boulder.getPosition());

        Position p2 = new Position(4,3);
        Door door = new Door("door1", "Door", p2, true, false);

        assertEquals(p2, door.getPosition());

        Position p3 = new Position(5,3);
        FloorSwitch fswitch = new FloorSwitch("floorswitch1", "floorswitch", p3, false);
        assertEquals(p3, fswitch.getPosition());
        

        Position p4 = new Position(6,3);
        Portal portal = new Portal("portal1", "portal", p4, true);
        assertEquals(p4, portal.getPosition());

        
        Position p5 = new Position(7,3);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombietoastspawner", p5, true);
        assertEquals(p5, z1.getPosition());

        Position p6 = new Position (8,3);
        Exit e = new Exit("exit1", "exit", p6, true);
        assertEquals(p6, e.getPosition());

    }

     


}
