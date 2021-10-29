package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterObserverTest {
    @Test
    public void normalCharacterOnEnemy() {
        // normal state character 

        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");

        Game game = controller.getCurrentGame();
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, null, null);
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        game.addEntity(character);
        game.addEntity(zombie);

        controller.tick("", Direction.UP); // player battles zombie
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 75);
        assertTrue(zombie.getHealth() < 50); // assertEquals(zombie.getHealth(), 30);

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        game.addEntity(spider);
        controller.tick("", Direction.LEFT); // player battles spider
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 63);
        assertTrue(spider.getHealth() < 40); // assertEquals(spider.getHealth(), 20);

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        game.addEntity(mercenary);
        controller.tick("", Direction.DOWN); // player battles mercenary
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 7);
        assertTrue(mercenary.getHealth() < 70); // assertEquals(mercenary.getHealth(), 57);
    }

    @Test
    public void invincibleCharacterOnEnemy() {
        // invincible state character

        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");
        Game game = controller.getCurrentGame();
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, null, null);
        Items invincibilityPotion = new InvincibilityPotion("invincibilityPotion", "invincibility_potion", 1, character);
        character.addInventory(invincibilityPotion);
        character.useItem("invincibilityPotion");
        assertEquals("Invincible", character.getStateName());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        game.addEntity(character);
        game.addEntity(zombie);

        controller.tick("", Direction.UP); // player battles zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(game.hasEntity(zombie)); // zombie is defeated

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        game.addEntity(spider);
        controller.tick("", Direction.LEFT); // player battles spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(game.hasEntity(spider)); // spider is defeated

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        game.addEntity(mercenary);
        controller.tick("", Direction.DOWN); // player battles mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(game.hasEntity(mercenary)); // mercenary is defeated
    }

    @Test
    public void invisibleCharacterOnEnemy() {
        // invisible state character

        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");
        Game game = controller.getCurrentGame();
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, null, null);
        Items invisibilityPotion = new InvisibilityPotion("invisibilityPotion", "invisibility_potion", 1, character);
        character.addInventory(invisibilityPotion);
        character.useItem("invisibilityPotion");
        assertEquals("Invisible", character.getStateName());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        game.addEntity(character);
        game.addEntity(zombie);

        controller.tick("", Direction.UP); // player moves onto same cell as zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(zombie.getHealth(), 50); // zombie takes no damage

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        game.addEntity(spider);
        controller.tick("", Direction.LEFT); // player moves onto same cell as spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(spider.getHealth(), 40); // spider takes no damage

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        game.addEntity(mercenary);
        controller.tick("", Direction.DOWN); // player moves onto same cell as mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(mercenary.getHealth(), 70); // mercenary takes no damage
    }

    @Test
    public void characterOnExit() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("empty.json", "Standard");
        Game game = controller.getCurrentGame();
        // empty.json has exit goal
        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 20, null, null);
        
        Exit exit = new Exit("exit", "exit", new Position(8,8), false);

        game.addEntity(character);
        game.addEntity(exit);

        // Move character towards exit
        DungeonResponse dungeonResponse = controller.tick("", Direction.DOWN);
        assertEquals(dungeonResponse.getGoals(), "exit");

        // Move character onto exit
        dungeonResponse = controller.tick("", Direction.RIGHT);
        // Game finished - dungeon response's goals should be empty
        assertEquals(dungeonResponse.getGoals(), "");
    }
}
