package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class newGameTest {
    @Test
    public void testIllegalArgument() {
        DungeonManiaController d = new DungeonManiaController();
        assertThrows(IllegalArgumentException.class, () -> d.newGame("invalid dungeon name", "peaceful"));
        assertThrows(IllegalArgumentException.class, () -> d.newGame("advanced", "invalid game mode"));
    }

    @Test
    public void testNewGame() {
        DungeonManiaController d = new DungeonManiaController();
        DungeonResponse newDungeon = d.newGame("advanced", "peaceful");
        EntityResponse lowerRightCorner = new EntityResponse("wall120", "wall", new Position(15, 17), false);
        assertTrue(newDungeon.getEntities().contains(lowerRightCorner));
        assertTrue(newDungeon.getInventory().isEmpty());
    }
    
    @Test
    public void abcTest() {
        assertEquals(1, DungeonManiaController.abc());
    }
}
