package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterObserverTest {
    @Test
    public void characterOnEnemy() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("empty.json", "Standard");
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, null);
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        game.addEntity(character);
        game.addEntity(zombie);

        game.tick("", Direction.UP);
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 75);
        assertTrue(zombie.getHealth() < 50); // assertEquals(zombie.getHealth(), 30);

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        game.addEntity(spider);
        game.tick("", Direction.LEFT);
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 63);
        assertTrue(spider.getHealth() < 40); // assertEquals(spider.getHealth(), 20);

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        game.addEntity(mercenary);
        game.tick("", Direction.DOWN);
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 7);
        assertTrue(mercenary.getHealth() < 70); // assertEquals(mercenary.getHealth(), 57);
    }


    @Test
    public void characterOnExit() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("empty.json", "Standard");
        // empty.json has exit goal
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 20, null);
        
        Exit exit = new Exit("exit", "exit", new Position(8,8), false);

        game.addEntity(character);
        game.addEntity(exit);

        // Move character towards exit
        DungeonResponse dungeonResponse = game.tick("", Direction.DOWN);
        assertEquals(dungeonResponse.getGoals(), "exit");

        // Move character onto exit
        dungeonResponse = game.tick("", Direction.RIGHT);
        // Game finished - dungeon response's goals should be empty
        assertEquals(dungeonResponse.getGoals(), "");
    }
}
