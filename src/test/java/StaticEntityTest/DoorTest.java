
package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




import dungeonmania.*;
import dungeonmania.Door;
import dungeonmania.Key;
import dungeonmania.util.Position;
import dungeonmania.Character;



public class DoorTest {
    @Test

    public void createDoorTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, true, false);


        assertEquals(doorPosition, door.getPosition());
    }

    public void openDoorTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, true, false);


        door.setDoorId(1);
        
        Key key = new Key("key1", "Key", 1, 1);
        Position cPosition = new Position (4,3);
        Character character = new Character("character1", "character",cPosition ,false, 100, 10, null);
        character.moveDown();

        assertEquals(true, door.isOpen());
    }
}
