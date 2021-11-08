package dungeonmania;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class CharacterObserverTest {
    @Test
    public void normalCharacterOnEnemy() {
        // normal state character 
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, new ArrayList<>(), new ArrayList<>());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        character.attach(zombie);

        character.move(Direction.UP); // player battles zombie
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 75);
        assertTrue(zombie.getHealth() < 50); // assertEquals(zombie.getHealth(), 30);

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        character.attach(spider);
        character.move(Direction.LEFT); // player battles spider
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 63);
        assertTrue(spider.getHealth() < 40); // assertEquals(spider.getHealth(), 20);

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        character.attach(mercenary);
        character.move(Direction.DOWN); // player battles mercenary
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 7);
        assertTrue(mercenary.getHealth() < 70); // assertEquals(mercenary.getHealth(), 57);
    }

    @Test
    public void invincibleCharacterOnEnemy() {
        // invincible state character
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, new ArrayList<>(), new ArrayList<>());
        Items invincibilityPotion = new InvincibilityPotion("invincibilityPotion", "invincibility_potion", 1);
        character.addInventory(invincibilityPotion);
        character.useItem("invincibilityPotion");
        assertEquals("Invincible", character.getStateName());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        character.attach(zombie);

        character.move(Direction.UP); // player battles zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.hasObserver(zombie)); // zombie is defeated, should be detached from player

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        character.attach(spider);
        character.move(Direction.LEFT); // player battles spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.hasObserver(spider)); // spider is defeated, should be detached from player

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        character.attach(mercenary);
        character.move(Direction.DOWN); // player battles mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.hasObserver(mercenary)); // mercenary is defeated, should be detached from player
    }

    @Test
    public void invisibleCharacterOnEnemy() {
        // invisible state character        
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, new ArrayList<>(), new ArrayList<>());
        Items invisibilityPotion = new InvisibilityPotion("invisibilityPotion", "invisibility_potion", 1);
        character.addInventory(invisibilityPotion);
        character.useItem("invisibilityPotion");
        assertEquals("Invisible", character.getStateName());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        character.attach(zombie);

        character.move(Direction.UP); // player moves onto same cell as zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(zombie.getHealth(), 50); // zombie takes no damage

        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        character.attach(spider);
        character.move(Direction.LEFT); // player moves onto same cell as spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(spider.getHealth(), 40); // spider takes no damage

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        character.attach(mercenary);
        character.move(Direction.DOWN); // player moves onto same cell as mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(mercenary.getHealth(), 70); // mercenary takes no damage
    }

    // @Test
    // public void characterOnExit() {
    //     // not sure if correct or if there is a better way to test exit,
    //     // as goals have not been implemented

    //     DungeonManiaController controller = new DungeonManiaController();
    //     controller.newGame("exit", "Standard");

    //     // Move character towards exit
    //     DungeonResponse dungeonResponse = controller.tick("", Direction.DOWN);
    //     assertEquals(dungeonResponse.getGoals(), ":exit");

    //     // Move character onto exit
    //     dungeonResponse = controller.tick("", Direction.RIGHT);
    //     // Game finished - dungeon response's goals should be empty
    //     assertEquals(dungeonResponse.getGoals(), "");
    // }
}
