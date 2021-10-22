package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import dungeonmania.Boulder;
import dungeonmania.DungeonManiaController;
import dungeonmania.response.*;
import dungeonmania.response.models.DungeonResponse;
import main.java.dungeonmania;


public class BoulderTest {
    

    @Test
    public void createBoulderTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("dungeonName", "Standard");

        Boulder boulder = new Boulder("boulder1", "boulder", position, true);
        
        assertListAreEqualIgnoringOrder(Arrays.asList("boulder1"), DungeonResponse.getEntities());


    }


    @Test 
    public void moveBoulderTest() {

        DungeonManiaController game = new DungeonManiaController();
        game.newGame("dungeonName", "Standard");

        Boulder boulder = new Boulder("boulder1", "boulder", position, true);

        

        

        character.moveDown();
        //game.interact("boulder1");
        //player position
        assertEquals(position(3,))
        // boulder position
        assertEquals(position(3,3,1), boulder.getPosition());
    }


}
