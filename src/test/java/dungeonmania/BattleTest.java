package dungeonmania;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleTest {
    @Test
    public void normalCharacterOnEnemy() {
        // normal state character 
        Character character = new Character("player", "character", new Position(7,7), false, 100, 1, new ArrayList<>(), new ArrayList<>());
        
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);
        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);

        // character.attach(zombie);
        // character.attach(spider);
        // character.attach(mercenary);
        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie, spider, mercenary, character));
        character.setEntities(entities);

        character.move(Direction.UP); // player battles zombie
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 75);
        assertTrue(zombie.getHealth() < 50); // assertEquals(zombie.getHealth(), 30);

        character.move(Direction.LEFT); // player battles spider
        assertTrue(character.getHealth() < 100); // assertEquals(character.getHealth(), 63);
        assertTrue(spider.getHealth() < 40); // assertEquals(spider.getHealth(), 20);

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
        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);
        
        // character.attach(zombie);
        // character.attach(spider);
        // character.attach(mercenary);
        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie, spider, mercenary, character));
        character.setEntities(entities);

        character.move(Direction.UP); // player battles zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.getEntities().contains(zombie)); // zombie is defeated, should be removed from map

        character.move(Direction.LEFT); // player battles spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.getEntities().contains(spider)); // spider is defeated, should be detached from player

        character.move(Direction.DOWN); // player battles mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertFalse(character.getEntities().contains(mercenary)); // mercenary is defeated, should be detached from player
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
        Spider spider = new Spider("spider", "spider", new Position(6,6), false, 40, 3);
        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);

        // character.attach(zombie);
        // character.attach(spider);
        // character.attach(mercenary);
        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie, spider, mercenary, character));
        character.setEntities(entities);

        character.move(Direction.UP); // player moves onto same cell as zombie
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(zombie.getHealth(), 50); // zombie takes no damage

        character.move(Direction.LEFT); // player moves onto same cell as spider
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(spider.getHealth(), 40); // spider takes no damage

        character.move(Direction.DOWN); // player moves onto same cell as mercenary
        assertEquals(character.getHealth(), 100); // player takes no damage
        assertEquals(mercenary.getHealth(), 70); // mercenary takes no damage
    }

    @Test
    public void characterArmourTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Armour armour = new Armour("armour", "armour", 2);
        character2.addInventory(armour);

        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 50, 5);

        // character1.attach(zombie);
        // character2.attach(zombie);

        List<Entity> entities1 = new ArrayList<>(Arrays.asList(zombie, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(zombie,character2));
        character1.setEntities(entities1);
        character2.setEntities(entities2);

        character1.move(Direction.UP); // player1 battles zombie
        character2.move(Direction.UP); // player2 battles zombie

        assertTrue(character1.getHealth() < character2.getHealth()); // player without armour has less health
        // assertEquals(75, character1.getHealth());
        // assertEquals(88, character2.getHealth());
        
        assertEquals(1, armour.getDurability());
        assertEquals(1, character2.getInventory().size());

        character2.move(Direction.LEFT);
        character2.move(Direction.RIGHT); // player2 battles zombie again

        assertEquals(0, armour.getDurability()); // armour out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void characterShieldTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Shield shield = new Shield("shield", "shield", 2);
        character2.addInventory(shield);

        Spider spider = new Spider("spider", "spider", new Position(7,8), false, 40, 4);

        // character1.attach(spider);
        // character2.attach(spider);

        List<Entity> entities1 = new ArrayList<>(Arrays.asList(spider, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(spider,character2));
        character1.setEntities(entities1);
        character2.setEntities(entities2);

        character1.move(Direction.DOWN); // player1 battles spider
        character2.move(Direction.DOWN); // player2 battles spider
        assertTrue(character1.getHealth() < character2.getHealth()); // player without shield has less health
        // assertEquals(84, character1.getHealth());
        // assertEquals(88, character2.getHealth());
        
        assertEquals(1, shield.getDurability());
        assertEquals(1, character2.getInventory().size());

        character2.move(Direction.DOWN);
        character2.move(Direction.UP); // player2 battles spider again

        assertEquals(0, shield.getDurability()); // shield out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void characterArmourShieldTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Character character3 = new Character("player3", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Character character4 = new Character("player4", "player", new Position(7,7), false, 100, 0, new ArrayList<>(), new ArrayList<>());
        Armour armour1 = new Armour("armour1", "armour", 1);
        Armour armour2 = new Armour("armour2", "armour", 2);
        Shield shield1 = new Shield("shield1", "shield", 1);
        Shield shield2 = new Shield("shield2", "shield", 2);
        character2.addInventory(armour1); // player2 has armour
        character3.addInventory(shield1); // player3 has shield
        character4.addInventory(armour2);
        character4.addInventory(shield2); // player4 has both armour and shield

        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,7), false, 70, 8, 3, false);

        // character1.attach(mercenary);
        // character2.attach(mercenary);
        // character3.attach(mercenary);
        // character4.attach(mercenary);

        List<Entity> entities1 = new ArrayList<>(Arrays.asList(mercenary, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(mercenary, character2));
        List<Entity> entities3 = new ArrayList<>(Arrays.asList(mercenary, character3));
        List<Entity> entities4 = new ArrayList<>(Arrays.asList(mercenary, character4));
        character1.setEntities(entities1);
        character2.setEntities(entities2);
        character3.setEntities(entities3);
        character4.setEntities(entities4);

        character1.move(Direction.LEFT); // player1 battles mercenary
        character2.move(Direction.LEFT); // player2 battles mercenary
        character3.move(Direction.LEFT); // player3 battles mercenary
        character4.move(Direction.LEFT); // player4 battles mercenary
        assertTrue(character1.getHealth() < character4.getHealth()); // player with no equipment has less health
        assertTrue(character2.getHealth() < character4.getHealth()); // player with only armour has less health
        assertTrue(character3.getHealth() < character4.getHealth()); // player with only shield has less health
        // assertEquals(44, character1.getHealth());
        // assertEquals(72, character2.getHealth());
        // assertEquals(58, character3.getHealth());
        // assertEquals(79, character4.getHealth());
        
        assertEquals(1, armour2.getDurability());
        assertEquals(1, shield2.getDurability());
        assertEquals(2, character4.getInventory().size());

        character4.move(Direction.DOWN);
        character4.move(Direction.UP); // player4 battles mercenary again

        assertEquals(0, armour2.getDurability()); // player4's armour out of durability
        assertEquals(0, shield2.getDurability()); // player4's shield out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void characterSwordTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());

        Sword sword = new Sword("sword", "sword", 2);
        character2.addInventory(sword); // player2 has sword

        Mercenary mercenary1 = new Mercenary("mercenary1", "mercenary", new Position(6,7), false, 70, 0, 3, false);
        Mercenary mercenary2 = new Mercenary("mercenary2", "mercenary", new Position(8,7), false, 70, 0, 3, false);

        // character1.attach(mercenary1);
        // character2.attach(mercenary2);
        List<Entity> entities1 = new ArrayList<>(Arrays.asList(mercenary1, mercenary2, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(mercenary1, mercenary2, character2));
        character1.setEntities(entities1);
        character2.setEntities(entities2);


        character1.move(Direction.LEFT); // player1 battles mercenary1
        character2.move(Direction.RIGHT); // player2 battles mercenary2
        assertTrue(mercenary2.getHealth() < mercenary1.getHealth()); // mercenary attacked with sword has less health

        // assertEquals(54, mercenary1.getHealth());
        // assertEquals(38, mercenary2.getHealth());
        
        assertEquals(1, sword.getDurability());
        assertEquals(1, character2.getInventory().size());

        character2.move(Direction.DOWN);
        character2.move(Direction.UP); // player2 battles mercenary2 again

        assertEquals(0, sword.getDurability()); // sword out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void characterBowTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());

        Bow bow = new Bow("bow", "bow", 2);
        character2.addInventory(bow); // player2 has bow

        Spider spider1 = new Spider("spider1", "spider", new Position(7,6), false, 60, 0);
        Spider spider2 = new Spider("spider2", "spider", new Position(7,8), false, 60, 0);

        // character1.attach(spider1);
        // character2.attach(spider2);
        List<Entity> entities1 = new ArrayList<>(Arrays.asList(spider1, spider2, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(spider1, spider2, character2));
        character1.setEntities(entities1);
        character2.setEntities(entities2);

        character1.move(Direction.UP); // player1 battles spider1
        character2.move(Direction.DOWN); // player2 battles spider2
        assertTrue(spider2.getHealth() < spider1.getHealth()); // spider attacked with bow has less health
        // assertEquals(44, spider1.getHealth());
        // assertEquals(28, spider2.getHealth());
        
        assertEquals(1, bow.getDurability());
        assertEquals(1, character2.getInventory().size());

        character2.move(Direction.RIGHT);
        character2.move(Direction.LEFT); // player2 battles spider2 again

        assertEquals(0, bow.getDurability()); // bow out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void characterSwordBowTest() {
        Character character1 = new Character("player1", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());
        Character character2 = new Character("player2", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());
        Character character3 = new Character("player3", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());
        Character character4 = new Character("player4", "player", new Position(7,7), false, 80, 1, new ArrayList<>(), new ArrayList<>());

        Sword sword1 = new Sword("sword1", "sword", 1);
        Sword sword2 = new Sword("sword2", "sword", 2);
        Bow bow1 = new Bow("bow1", "bow", 1);
        Bow bow2 = new Bow("bow2", "bow", 2);
        character2.addInventory(sword1); // player2 has sword
        character3.addInventory(bow1); // player3 has bow
        character4.addInventory(sword2);
        character4.addInventory(bow2); // player4 has sword and bow

        Zombie zombie1 = new Zombie("zombie1", "zombie_toast", new Position(7,6), false, 80, 0);
        Zombie zombie2 = new Zombie("zombie2", "zombie_toast", new Position(7,8), false, 80, 0);
        Zombie zombie3 = new Zombie("zombie3", "zombie_toast", new Position(6,7), false, 80, 0);
        Zombie zombie4 = new Zombie("zombie4", "zombie_toast", new Position(8,7), false, 80, 0);

        // character1.attach(zombie1);
        // character2.attach(zombie2);
        // character3.attach(zombie3);
        // character4.attach(zombie4);

        List<Entity> entities1 = new ArrayList<>(Arrays.asList(zombie1, zombie2, zombie3, zombie4, character1));
        List<Entity> entities2 = new ArrayList<>(Arrays.asList(zombie1, zombie2, zombie3, zombie4, character2));
        List<Entity> entities3 = new ArrayList<>(Arrays.asList(zombie1, zombie2, zombie3, zombie4, character3));
        List<Entity> entities4 = new ArrayList<>(Arrays.asList(zombie1, zombie2, zombie3, zombie4, character4));
        character1.setEntities(entities1);
        character2.setEntities(entities2);
        character3.setEntities(entities3);
        character4.setEntities(entities4);

        character1.move(Direction.UP); // player1 battles zombie1
        character2.move(Direction.DOWN); // player2 battles zombie2
        character3.move(Direction.LEFT); // player3 battles zombie3
        character4.move(Direction.RIGHT); // player4 battles zombie4
        assertTrue(zombie4.getHealth() < zombie1.getHealth()); // zombie attacked with sword and bow has less health
        assertTrue(zombie4.getHealth() < zombie2.getHealth()); // zombie attacked with sword and bow has less health
        assertTrue(zombie4.getHealth() < zombie3.getHealth()); // zombie attacked with sword and bow has less health
        assertEquals(64, zombie1.getHealth());
        assertEquals(48, zombie2.getHealth());
        assertEquals(48, zombie3.getHealth());
        assertEquals(16, zombie4.getHealth());
        
        assertEquals(1, sword2.getDurability());
        assertEquals(1, bow2.getDurability());
        assertEquals(2, character4.getInventory().size());

        character4.move(Direction.LEFT);
        character4.move(Direction.RIGHT); // player4 battles zombie4 again

        assertEquals(0, sword2.getDurability()); // sword out of durability
        assertEquals(0, bow2.getDurability()); // bow out of durability
        assertEquals(new ArrayList<Items>(), character2.getInventory()); // removed from inventory
    }

    @Test
    public void zombieArmourTest() {
        Character character = new Character("player", "player", new Position(7,7), false, 60, 2, new ArrayList<>(), new ArrayList<>());

        Zombie zombie1 = new Zombie("zombie1", "zombie_toast", new Position(7,6), false, 50, 0);
        Zombie zombie2 = new Zombie("zombie2", "zombie_toast", new Position(7,6), false, 50, 0);
        Armour armour = new Armour("armour", "armour", 2);
        zombie2.setArmour(armour); // zombie2 has armour

        // character.attach(zombie1);
        // character.attach(zombie2);

        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie1, zombie2, character));
        character.setEntities(entities);

        character.move(Direction.UP); // player battles both zombies

        assertTrue(zombie1.getHealth() < zombie2.getHealth()); // zombie without armour has less health
        // assertEquals(26, zombie1.getHealth());
        // assertEquals(38, zombie2.getHealth());
        
        assertEquals(1, armour.getDurability());

        character.move(Direction.LEFT);
        character.move(Direction.RIGHT); // player battles zombies again

        assertEquals(0, armour.getDurability()); // armour out of durability
        assertNull(zombie2.getArmour()); // removed from zombie2
    }

    @Test
    public void mercenaryArmourTest() {
        Character character = new Character("player", "player", new Position(7,7), false, 60, 2, new ArrayList<>(), new ArrayList<>());

        Mercenary mercenary1 = new Mercenary("mercenary1", "mercenary", new Position(7,6), false, 60, 0, 3, false);
        Mercenary mercenary2 = new Mercenary("mercenary2", "mercenary", new Position(7,6), false, 60, 0, 3, false);
        Armour armour = new Armour("armour", "armour", 2);
        mercenary2.setArmour(armour); // mercenary2 has armour

        // character.attach(mercenary1);
        // character.attach(mercenary2);
        List<Entity> entities = new ArrayList<>(Arrays.asList(mercenary1, mercenary2, character));
        character.setEntities(entities);

        character.move(Direction.UP); // player battles both mercenaries

        assertTrue(mercenary1.getHealth() < mercenary2.getHealth()); // mercenary without armour has less health
        // assertEquals(36, mercenary1.getHealth());
        // assertEquals(48, mercenary2.getHealth());
        
        assertEquals(1, armour.getDurability());

        character.move(Direction.LEFT);
        character.move(Direction.RIGHT); // player battles mercenaries again

        assertEquals(0, armour.getDurability()); // armour out of durability
        assertNull(mercenary2.getArmour()); // removed from mercenary2
    }

    @Test
    public void enemyDropArmourTest() {
        Character character = new Character("player", "player", new Position(7,7), false, 100, 3, new ArrayList<>(), new ArrayList<>());

        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 20, 0);
        Mercenary mercenary = new Mercenary("mercenary", "mercenary", new Position(6,6), false, 20, 0, 3, false);

        Armour armour1 = new Armour("armour1", "armour", 3);
        Armour armour2 = new Armour("armour2", "armour", 3);
        zombie.setArmour(armour1); // zombie has armour
        mercenary.setArmour(armour2); // mercenary has armour

        // character.attach(zombie);
        // character.attach(mercenary);
        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie, mercenary, character));
        character.setEntities(entities);

        assertNull(character.getArmour());
        assertEquals(0, character.getInventory().size());

        character.move(Direction.UP); // player battles zombie
        assertTrue(zombie.getHealth() <= 0); // zombie is dead
        assertEquals(2, armour1.getDurability());
        assertNotNull(character.getArmour());
        assertEquals(1, character.getInventory().size());
        assertTrue(character.getInventory().contains(armour1)); // player gets zombie's armour

        character.move(Direction.LEFT); // player battles mercenary
        assertTrue(mercenary.getHealth() <= 0); // mercenary is dead
        assertEquals(2, armour2.getDurability());
        assertEquals(2, character.getInventory().size());
        assertTrue(character.getInventory().contains(armour1) && character.getInventory().contains(armour2)); // player gets mercenary's armour
    }

    @Test
    public void characterDeathTest() {
        Character character = new Character("player", "player", new Position(7,7), false, 50, 0, new ArrayList<>(), new ArrayList<>());
        Zombie zombie = new Zombie("zombie", "zombie_toast", new Position(7,6), false, 60, 10);

        List<Entity> entities = new ArrayList<>(Arrays.asList(zombie, character));
        character.setEntities(entities);

        assertEquals(2, character.getEntities().size()); // map contains player and zombie

        character.move(Direction.UP); // player battles zombie
        assertTrue(character.getHealth() <= 0); // player is now dead

        assertEquals(1, character.getEntities().size()); // map only contains zombie
    }
}
