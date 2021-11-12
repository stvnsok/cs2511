package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ItemTest {
    // Checking item is now present in inventory of player when added
    @Test
    public void itemAdd() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new Sword("s1", "sword", 4);
        c1.addInventory(i1);
        assertEquals(new ArrayList<Items>(Arrays.asList(i1)), c1.getInventory());
    }
    
    // Using an item should cause it to lose a point of durability
    @Test
    public void useItem() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new Sword("s1", "sword", 4);
        c1.addInventory(i1);
        c1.useItem("s1");
        assertEquals(3, i1.getDurability());
    }
    
    // When Item runs out of durability, it should 'break' disappearing from inventory
    @Test
    public void breakItem() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new Items("i1", "sword", 1);
        c1.addInventory(i1);
        assertEquals(new ArrayList<Items>(Arrays.asList(i1)), c1.getInventory());
        c1.useItem("i1");
        assertEquals(0, c1.getInventory().size());
        assertEquals(new ArrayList<Items>(), c1.getInventory());
    }
    
    // Testing Potion usage, as they have different effects that just decreasing durability
    @Test
    public void usePotion() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new InvincibilityPotion("i1", "invincibility_potion", 1);
        c1.addInventory(i1);
        c1.useItem("i1");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals("Invincible", c1.getStateName());
        c1.addInventory(new InvisibilityPotion("i2", "invisibility_potion", 1));
        c1.useItem("i2");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals("Invisible", c1.getStateName());
        c1.setHealth(20);
        c1.addInventory(new HealthPotion("i3", "health_potion", 1));
        c1.useItem("i3");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals(c1.getMaxHealth(), c1.getHealth());
    }
    
    // Testing building items with Character
    @Test
    public void buildTest() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items a = new Items("a", "wood", 1);
        Items b = new Items("b", "arrow", 1);
        Items c = new Items("c", "arrow", 1);
        Items d = new Items("d", "arrow", 1);
        c1.addInventory(a);
        c1.addInventory(b);
        c1.addInventory(c);
        c1.addInventory(d);
        List<Items> test = new ArrayList<>(Arrays.asList(a,b,c,d));
        assertEquals(test, c1.getInventory());
        c1.buildItem("bow");
        assertEquals(1, c1.getInventory().size());
        Items e = c1.getInventory().get(0);
        assertEquals("bow", e.getItemType());
    }
    
    @Test
    public void useBomb() {
        // Creating a new game just to have access to entity list
        List<Entity> entities = new ArrayList<>();
        Position cPosition = new Position(10,11);
        Character c1 = new Character("player", "Character", cPosition, false, 100, 10, new ArrayList<>(), entities);
        entities.add(c1);
        c1.addInventory(new Bomb("a", "bomb", 1));
        c1.useItem("a");
        assertEquals(0, c1.getInventory().size());
        assertEquals(2, entities.size());
        Entity testBomb = entities.get(1);
        assertEquals("bomb", testBomb.getType());
        Position bPosition = testBomb.getPosition();
        assertEquals(cPosition, bPosition); // character and bomb on same position

        // attempt to move character back to original position, but move is blocked by the placed bomb
        c1.move(Direction.LEFT);
        c1.move(Direction.RIGHT);
        assertEquals(0, c1.getInventory().size()); // character has not picked up the bomb
        assertEquals(2, entities.size());
        assertNotEquals(cPosition, c1.getPosition()); // character is not back to original position

    }
    @Test
    public void zombieDropArmourTest() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("advanced", "Standard");
        Game game = dc.getCurrentGame();
        Character player = game.getCharacter();
        Position dummyPosition = new Position(1, 1);
        Armour.seed = 11;
        Zombie zombie1 = (Zombie) EntityFactory.createEntity("zombie1", dummyPosition, "zombie");
        assertTrue(zombie1.getArmour() == null);
        assertTrue(player.getInventory().isEmpty());
        assertDoesNotThrow(() -> player.battle(zombie1));
        assertEquals(player.getArmour(), null);

        Armour.seed = 10;
        Zombie zombie2 = (Zombie) EntityFactory.createEntity("zombie2", dummyPosition, "zombie");
        assertTrue(zombie2.getArmour() != null);
        assertTrue(player.getInventory().isEmpty());
        zombie2.setHealth(1);
        assertDoesNotThrow(() -> player.battle(zombie2));
        assertTrue(!player.getInventory().isEmpty());
        assertNotEquals(player.getArmour(), null);
    }
    @Test
    public void mercenaryDropArmourTest() {
        DungeonManiaController dc = new DungeonManiaController();
        dc.newGame("advanced", "Standard");
        Game game = dc.getCurrentGame();
        Character player = game.getCharacter();
        Position dummyPosition = new Position(1, 1);
        Armour.seed = 11;
        Mercenary mercenary1 = (Mercenary) EntityFactory.createEntity("mercenary1", dummyPosition, "mercenary");
        assertTrue(mercenary1.getArmour() == null);
        assertTrue(player.getInventory().isEmpty());
        assertDoesNotThrow(() -> player.battle(mercenary1));
        assertEquals(player.getArmour(), null);

        Armour.seed = 10;
        Mercenary mercenary2 = (Mercenary) EntityFactory.createEntity("mercenary2", dummyPosition, "mercenary");
        assertTrue(mercenary2.getArmour() != null);
        assertTrue(player.getInventory().isEmpty());
        mercenary2.setHealth(1);
        assertDoesNotThrow(() -> player.battle(mercenary2));
        assertTrue(!player.getInventory().isEmpty());
        assertNotEquals(player.getArmour(), null);
    }

    @Test
    public void sunStoneTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>());
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        Door d = new Door("d1", "door", new Position(0,1), false, false, 1);
        Items k = new Items("s1", "sun_stone", 1);
        
        entities.add(d);
        inventory.add(k);
        //character.useItem("key");
        character.checkDoor(entities, new Position(0,1));
        assertTrue(d.isOpen());
        assertEquals("door_unlocked", d.getType());
    }

}
