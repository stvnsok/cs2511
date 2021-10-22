
package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




import dungeonmania.*;
import dungeonmania.Door;
import dungeonmania.Key;
import dungeonmania.util.Position;



public class DoorTest {
    @Test

    public void createDoorTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, true, false);


        assertEquals(doorPosition, door.getPosition());
    }
}
