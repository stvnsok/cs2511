package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class EnemyMovementTest {
    @Test
    public void SpiderMovement() {
        Spider spider = new Spider("spider1", "spider", new Position(1, 1), true, 10, 10);
        List<Entity> entities = new ArrayList<>();
        Wall wall = new Wall("wall2", "wall", new Position(2, 2), false);
        entities.add(wall);
        Character character = new Character("player3", "player", new Position(10, 10), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);
        
        //Checks if spider makes full rotation with walls in place
        spider.move(entities, character);

        assertTrue(spider.getPosition().equals(new Position(1, 0)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 0)));
        
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 1)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(1, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 1)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 0)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(1, 0)));

        //checks if spider change direction after seeing boulder
        Boulder boulder = new Boulder("boulder4", "boulder", new Position(2, 1), true);
        Boulder boulder2 = new Boulder("boulder5", "boulder", new Position(0, 1), true);
        entities.add(boulder);
        entities.add(boulder2);

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 0)));

        spider.move(entities, character);
        System.out.println(spider.getPosition());
        assertTrue(spider.getPosition().equals(new Position(1, 0)));
        
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 0)));

        spider.move(entities, character);
       
        assertTrue(spider.getPosition().equals(new Position(1, 0)));
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 0)));

    }

    @Test
    public void spiderBoulderMovement() {
        Spider spider = new Spider("spider1", "spider", new Position(1, 1), true, 10, 10);
        List<Entity> entities = new ArrayList<>();
        Wall wall = new Wall("wall2", "wall", new Position(1, 2), false);
        entities.add(wall);
        Character character = new Character("player3", "player", new Position(10, 10), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);

        Boulder boulder = new Boulder("boulder4", "boulder", new Position(2, 0), true);
        entities.add(boulder);

        // Check if spider makes full rotation in reverse direction

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(1, 0)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 0)));

        // Remove boulder - boulder destroyed!
        entities.remove(boulder);

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 1)));
        
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(0, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(1, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 2)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 1)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 0)));

        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(1, 0)));
    }

    @Test
    public void spiderMovementInvincible() {
        // test spider running away from invincible state player
        Spider spider = new Spider("spider1", "spider", new Position(3, 3), true, 10, 10);
        List<Entity> entities = new ArrayList<>();
        Wall wall = new Wall("wall2", "wall", new Position(1, 2), false);
        entities.add(wall);

        Character character = new Character("player3", "player", new Position(3, 4), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        character.addInventory(new InvincibilityPotion("invincibilityPotion", "invincibility_potion", 1));
        character.useItem("invincibilityPotion");
        assertEquals("Invincible", character.getStateName());

        entities.add(character);

        // character below spider, spider moves up
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(3, 2)));

        // character above spider, spider moves down
        character.setPosition(new Position(3, 1));
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(3, 3)));

        // character to the right of spider, spider moves left
        character.setPosition(new Position(4, 3));
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(2, 3)));

        // character to the left of spider, spider moves right
        character.setPosition(new Position(1, 3));
        spider.move(entities, character);
        assertTrue(spider.getPosition().equals(new Position(3, 3)));
    }

    @Test
    public void spiderMovementBattleTest() {
        Spider spider = new Spider("spider1", "spider", new Position(3, 3), true, 20, 3);
        List<Entity> entities = new ArrayList<>();

        Character character = new Character("player2", "player", new Position(3, 2), true, 100, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);

        // spider moves then battles character
        spider.move(entities, character);
        assertEquals(spider.getPosition(), character.getPosition());
        assertTrue(spider.getHealth() <= 0);
    }

    @Test
    public void ZombieMovement() {
        Zombie zombie = new Zombie("1", "zombie_toast", new Position(5, 5), true, 10, 10);
        List<Entity> entities = new ArrayList<>();
        Character character = new Character("2", "Character", new Position(10, 10), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);

        zombie.move(entities, character);

        //checks if zombie moves
        assertFalse(zombie.getPosition().equals(new Position(5, 5)));
        
        //checks where zombie goes if only 1 option
        Zombie zombie2 = new Zombie("1", "zombie_toast", new Position(1, 1), true, 10, 10);
        Wall wall = new Wall("2", "wall", new Position(1, 0), true);
        entities.add(wall);
        Wall wall1 = new Wall("2", "wall", new Position(0, 1), true);
        entities.add(wall1);
        Wall wall2 = new Wall("2", "wall", new Position(1, 2), true);
        entities.add(wall2);

        zombie2.move(entities, character);
        assertTrue(zombie2.getPosition().equals(new Position(2,1)));
    }

    @Test
    public void zombieMovementInvincible() {
        // test zombie running away from invincible state player
        Zombie zombie = new Zombie("zombie1", "zombie_toast", new Position(3, 3), true, 10, 10);
        List<Entity> entities = new ArrayList<>();
        Wall wall = new Wall("wall2", "wall", new Position(1, 2), false);
        entities.add(wall);

        Character character = new Character("player3", "player", new Position(3, 4), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        character.addInventory(new InvincibilityPotion("invincibilityPotion", "invincibility_potion", 1));
        character.useItem("invincibilityPotion");
        assertEquals("Invincible", character.getStateName());

        entities.add(character);

        // character below zombie, zombie moves up
        zombie.move(entities, character);
        assertTrue(zombie.getPosition().equals(new Position(3, 2)));

        // character above zombie, zombie moves down
        character.setPosition(new Position(3, 1));
        zombie.move(entities, character);
        assertTrue(zombie.getPosition().equals(new Position(3, 3)));

        // character to the right of zombie, zombie moves left
        character.setPosition(new Position(4, 3));
        zombie.move(entities, character);
        assertTrue(zombie.getPosition().equals(new Position(2, 3)));

        // character to the left of zombie, zombie moves right
        character.setPosition(new Position(1, 3));
        zombie.move(entities, character);
        assertTrue(zombie.getPosition().equals(new Position(3, 3)));
    }

    @Test
    public void zombieMovementBattleTest() {
        Zombie zombie = new Zombie("zombie1", "zombie_toast", new Position(3, 3), true, 20, 3);
        List<Entity> entities = new ArrayList<>();

        Character character = new Character("player2", "player", new Position(3, 2), true, 100, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);

        // zombie moves then battles character
        Wall wall = new Wall("2", "wall", new Position(3, 4), true);
        entities.add(wall);
        Wall wall1 = new Wall("2", "wall", new Position(2, 3), true);
        entities.add(wall1);
        Wall wall2 = new Wall("2", "wall", new Position(4, 3), true);
        entities.add(wall2);

        zombie.move(entities, character);
        assertEquals(zombie.getPosition(), character.getPosition());
        assertTrue(zombie.getHealth() <= 0);
    }

    @Test
    public void MercenaryMovement() {
        Mercenary mercenary = new Mercenary("1", "mercenary", new Position(1, 1), true, 10, 10, 10, false);
        List<Entity> entities = new ArrayList<>();
        Character character = new Character("2", "character", new Position(1, 5), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);
        entities.add(mercenary);
        character.setEntities(entities);

        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(1, 2)));

        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(1, 3)));

        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(1, 4)));

        character.move(Direction.RIGHT);

        // mercenary moves onto adjacent position of character
        mercenary.move(entities, character);
        assertTrue(Position.isAdjacent(character.getPosition(), mercenary.getPosition()));

        // mercenary moves onto same position as character
        mercenary.move(entities, character);
        assertEquals(character.getPosition(), mercenary.getPosition());
    }

    @Test
    public void mercenaryMovementInvincible() {
        // test mercenary running away from invincible state player
        Mercenary mercenary = new Mercenary("mercenary1", "mercenary", new Position(3, 3), false, 10, 10, 1, false);
        List<Entity> entities = new ArrayList<>();
        Wall wall = new Wall("wall2", "wall", new Position(1, 2), false);
        entities.add(wall);

        Character character = new Character("player3", "player", new Position(3, 4), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        character.addInventory(new InvincibilityPotion("invincibilityPotion", "invincibility_potion", 1));
        character.useItem("invincibilityPotion");
        assertEquals("Invincible", character.getStateName());

        entities.add(character);

        // character below mercenary, mercenary moves up
        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(3, 2)));

        // character above mercenary, mercenary moves down
        character.setPosition(new Position(3, 1));
        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(3, 3)));

        // character to the right of mercenary, mercenary moves left
        character.setPosition(new Position(4, 3));
        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(2, 3)));

        // character to the left of mercenary, mercenary moves right
        character.setPosition(new Position(1, 3));
        mercenary.move(entities, character);
        assertTrue(mercenary.getPosition().equals(new Position(3, 3)));
    }

    @Test
    public void mercenaryMovementBattleTest() {
        Mercenary mercenary = new Mercenary("mercenary1", "mercenary", new Position(3, 3), false, 10, 10, 1, false);
        List<Entity> entities = new ArrayList<>();

        Character character = new Character("player3", "player", new Position(3, 4), true, 10, 10, new ArrayList<>(), new ArrayList<>(), "Standard");
        entities.add(character);

        // mercenary moves then battles character
        mercenary.move(entities, character);
        assertEquals(mercenary.getPosition(), character.getPosition());
        assertTrue(mercenary.getHealth() <= 0);
    }
}
