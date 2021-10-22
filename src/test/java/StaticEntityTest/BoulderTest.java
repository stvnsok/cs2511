package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import dungeonmania.*;
import dungeonmania.Character;
import dungeonmania.Boulder;
import dungeonmania.response.*;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;




public class BoulderTest {
    

    @Test
    public void createBoulderTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");

        Position p = new Position(3, 3);
        Boulder boulder = new Boulder("boulder1", "boulder", p, true);

        
        assertEquals(p, boulder.getPosition());

    }


    @Test 
    public void moveDownBoulderTest() {

        DungeonManiaController game = new DungeonManiaController();
        game.newGame("dungeonName", "Standard");

        Position cPosition = new Position (3,2);

        
        Character character = new Character("character1", "character",cPosition ,false, 100, 10, null);
        
        Position bPosition = new Position(3,3);
        Boulder boulder = new Boulder("boulder1", "boulder", bPosition, true);

        character.moveDown();
        //game.interact("boulder1");
        //player position
        Position newcPosition = new Position(3,2);
        Position newbPosition = new Position(3,4);
        assertEquals(newcPosition, character.getPosition());
        // boulder position
        assertEquals(newbPosition, boulder.getPosition());

    }


}
