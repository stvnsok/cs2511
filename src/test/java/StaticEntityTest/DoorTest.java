
package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
        Position doorPosition = new Position(4,4,0); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        assertEquals(doorPosition.getX(), door.getPosition().getX());
        assertEquals(doorPosition.getY(), door.getPosition().getY());
        assertEquals(doorPosition.getLayer(), door.getPosition().getLayer());
        
    }
    // testing opening the door, if there is a key with id that matches the door, then the door should open
    public void openDoorTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        door.setDoorId(1);
        
        List<Items> items = new ArrayList<>();
        Position start = new Position (4,3);
        Character c1 = new Character("player", "Character", start, false, 100, 10, items);
        
        Key key = new Key("key1", "Key", 1, 1, c1);
        items.add(key);
        c1.moveDown();

        c1.useitem(key);

        assertEquals(true, door.isOpen());
    }

    // tesing the wrong key. If there is a wrong key, the door should not open
    public void wrongKeyTest(){
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        door.setDoorId(2);
        List<Items> items = new ArrayList<>();
        Position start = new Position (4,3);
        Character c1 = new Character("player", "Character", start, false, 100, 10, items);
        
        Key key = new Key("key1", "Key", 1, 1, c1);
        items.add(key);
        c1.moveDown();

        c1.useitem(key);

        assertEquals(false, door.isOpen());
    }

    
}
