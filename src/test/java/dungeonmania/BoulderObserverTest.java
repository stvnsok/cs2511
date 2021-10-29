package dungeonmania;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoulderObserverTest {
    @Test
    public void boulderOnSwitchTest() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");
        Game game = controller.getCurrentGame();
        
        Character character = new Character("player", "character", new Position(5,5), false, 100, 20, null, null);
        
        Boulder boulder1 = new Boulder("boulder1", "boulder", new Position(5,6), false);
        Boulder boulder2 = new Boulder("boulder2", "boulder", new Position(6,6), false);

        FloorSwitch floorSwitch1 = new FloorSwitch("floorSwitch1", "switch", new Position(5,7), false);
        FloorSwitch floorSwitch2 = new FloorSwitch("floorSwitch2", "switch", new Position(7,6), false);

        game.addEntity(character);
        game.addEntity(boulder1);
        game.addEntity(boulder2);
        game.addEntity(floorSwitch1);
        game.addEntity(floorSwitch2);

        assertFalse(floorSwitch1.isTriggered());
        assertFalse(floorSwitch2.isTriggered());

        // Move player down to push boulder1 onto floorSwitch1
        // character.move(Direction.DOWN);
        controller.tick("", Direction.DOWN);

        assertEquals(boulder1.getPosition(), floorSwitch1.getPosition());
        assertTrue(floorSwitch1.isTriggered());
        assertFalse(floorSwitch2.isTriggered());

        // Move player to the right to push boulder2 onto floorSwitch2
        // character.move(Direction.RIGHT);
        controller.tick("", Direction.RIGHT);
        assertEquals(boulder2.getPosition(), floorSwitch2.getPosition());
        assertTrue(floorSwitch1.isTriggered());
        assertTrue(floorSwitch2.isTriggered());
    }

    @Test
    public void boulderOffSwitchTest() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");
        Game game = controller.getCurrentGame();
        
        Character character = new Character("player", "character", new Position(5,5), false, 100, 20, null, null);

        Boulder boulder1 = new Boulder("boulder1", "boulder", new Position(5,6), false);
        Boulder boulder2 = new Boulder("boulder2", "boulder", new Position(6,6), false);

        FloorSwitch floorSwitch1 = new FloorSwitch("floorSwitch1", "switch", new Position(5,7), false);
        FloorSwitch floorSwitch2 = new FloorSwitch("floorSwitch2", "switch", new Position(7,6), false);

        game.addEntity(character);
        game.addEntity(boulder1);
        game.addEntity(boulder2);
        game.addEntity(floorSwitch1);
        game.addEntity(floorSwitch2);

        assertFalse(floorSwitch1.isTriggered());
        assertFalse(floorSwitch2.isTriggered());

        // Move to push boulders and trigger floor switches
        // character.move(Direction.DOWN);
        // character.move(Direction.RIGHT);
        controller.tick("", Direction.DOWN);
        controller.tick("", Direction.RIGHT);
        assertTrue(floorSwitch1.isTriggered());
        assertTrue(floorSwitch2.isTriggered());

        // Push boulder2 off of floorSwitch2
        // character.move(Direction.RIGHT);
        controller.tick("", Direction.RIGHT);
        assertTrue(floorSwitch1.isTriggered());
        assertFalse(floorSwitch2.isTriggered());

        // Push boulder1 off of floorSwitch1
        // character.move(Direction.DOWN);
        // character.move(Direction.LEFT);
        // character.move(Direction.LEFT);
        controller.tick("", Direction.DOWN);
        controller.tick("", Direction.LEFT);
        controller.tick("", Direction.LEFT);
        assertFalse(floorSwitch1.isTriggered());
        assertFalse(floorSwitch2.isTriggered());
    }
}
