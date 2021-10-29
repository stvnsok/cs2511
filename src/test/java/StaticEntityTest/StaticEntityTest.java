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
        Boulder boulder = new Boulder("boulder1", "boulder", p1, true);
        assertEquals(p1.getX(), boulder.getPosition().getX());
        assertEquals(p1.getY(), boulder.getPosition().getY());
        assertEquals(p1.getLayer(), boulder.getPosition().getLayer());

        Position p2 = new Position(4,3, 0);
        Door door = new Door("door1", "Door", p2, false, false);

        assertEquals(p2.getX(), door.getPosition().getX());
        assertEquals(p2.getY(), door.getPosition().getY());
        assertEquals(p2.getLayer(), door.getPosition().getLayer());

        Position p3 = new Position(5,3, 0);
        FloorSwitch fswitch = new FloorSwitch("floorswitch1", "floorswitch", p3, false);
        assertEquals(p3.getX(), fswitch.getPosition().getX());
        assertEquals(p3.getY(), fswitch.getPosition().getY());
        assertEquals(p3.getLayer(), fswitch.getPosition().getLayer());
        

        Position p4 = new Position(6,3,0);
        Portal portal = new Portal("portal1", "portal", p4, true);
        assertEquals(p4.getX(), portal.getPosition().getX());
        assertEquals(p4.getY(), portal.getPosition().getY());
        assertEquals(p4.getLayer(), portal.getPosition().getLayer());

        
        Position p5 = new Position(7,3,0);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombietoastspawner", p5, true);
        assertEquals(p5.getX(), z1.getPosition().getX());
        assertEquals(p5.getY(), z1.getPosition().getY());
        assertEquals(p5.getLayer(), z1.getPosition().getLayer());

        Position p6 = new Position (8,3,0);
        Exit e = new Exit("exit1", "exit", p6, true);
        assertEquals(p6.getX(), e.getPosition().getX());
        assertEquals(p6.getY(), e.getPosition().getY());
        assertEquals(p6.getLayer(), e.getPosition().getLayer());

    }

     


}
