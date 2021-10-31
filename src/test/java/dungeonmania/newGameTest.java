package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
        List<EntityResponse> entities = newDungeon.getEntities();
        EntityResponse lowerRightCorner = entities.get(entities.size() - 1);
        assertEquals(lowerRightCorner.getType(), "wall");
        assertEquals(lowerRightCorner.getPosition().getX(), 15);
        assertEquals(lowerRightCorner.getPosition().getY(), 17);
        assertTrue(newDungeon.getInventory().isEmpty());
        assertTrue(newDungeon.getBuildables().isEmpty());
    }
    
    @Test
    public void abcTest() {
        assertEquals(1, DungeonManiaController.abc());
    }
}
