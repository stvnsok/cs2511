package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitGoalTest {
    @Test
    public void exitGoalTest() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("exit", "Standard");

        // Move character towards exit
        DungeonResponse dungeonResponse = controller.tick("", Direction.DOWN);
        assertEquals(dungeonResponse.getGoals(), ":exit");

        // Move character onto exit
        dungeonResponse = controller.tick("", Direction.RIGHT);
        // Game finished - dungeon response's goals should be empty
        assertEquals(dungeonResponse.getGoals(), "");
    }
}
