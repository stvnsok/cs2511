
package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;




import dungeonmania.*;
import dungeonmania.Door;
import dungeonmania.Key;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.Character;



public class DoorTest {
    @Test

    
    public void createDoorTest() {

        Position doorPosition = new Position(4,4,0); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        assertEquals(doorPosition.getX(), door.getPosition().getX());
        assertEquals(doorPosition.getY(), door.getPosition().getY());
        assertEquals(doorPosition.getLayer(), door.getPosition().getLayer());
        
    }
    @Test
    // testing opening the door, if there is a key with id that matches the door, then the door should open
    public void openDoorTest() {

        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        door.setDoorId(1);
        
        List<Items> items = new ArrayList<>();
        Position start = new Position (4,3);
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, null);
        
        Key key = new Key("key1", "Key", 1, 1);
        items.add(key);
        c1.PlayerMovement(Direction.DOWN);

        //c1.useItem("Key");
        key.useKey(door);

        assertEquals(true, door.isOpen());
    }
    @Test
    // tesing the wrong key. If there is a wrong key, the door should not open
    public void wrongKeyTest(){

        Position doorPosition = new Position(4,4); 
        Door door = new Door("door1", "Door", doorPosition, false, false);


        door.setDoorId(2);
        List<Items> items = new ArrayList<>();
        Position start = new Position (4,3);
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, null);
        
        Key key = new Key("key1", "Key", 1, 1);
        items.add(key);
        c1.PlayerMovement(Direction.DOWN);

        //c1.useItem("Key");
        key.useKey(door);
        assertEquals(false, door.isOpen());
    }

    
}
