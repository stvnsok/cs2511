package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;

public class theOneRingTest {
    @Test
    public void getTheOneRingTest() {
    }

    @Test
    public void useTheOneRingTest() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("advanced", "Standard");
        Game game = dc.getCurrentGame();
        Character player = game.getCharacter();
        TheOneRing testRing1 = new TheOneRing("testRing1");
        player.addInventory(testRing1);
        player.setHealth(1);
        assertEquals(player.getHealth(), 1);
        Position dummyPosition = new Position(1, 1);
        Mob enemy = new Mob("test1", "spider", dummyPosition, false, 20, 10);
        assertTrue(player.getInventory().contains(testRing1));
        assertDoesNotThrow(() -> player.battle(enemy));
        assertTrue(!player.getInventory().contains(testRing1));
        assertEquals(player.getHealth(), player.getMaxHealth());
    }
}
