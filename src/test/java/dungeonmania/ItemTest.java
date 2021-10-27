package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Position;

public class ItemTest {
    // Checking item is now present in inventory of player when added
    @Test
    public void itemAdd() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new Sword("s1", "sword", 4, c1);
        c1.addInventory(i1);
        assertEquals(new ArrayList<Items>(Arrays.asList(i1)), c1.getInventory());
    }
    // Using an item should cause it to lose a point of durability
    @Test
    public void useItem() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items i1 = new Sword("s1", "sword", 4, c1);
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
        Items i1 = new Items("i1", "sword", 1, c1);
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
        Items i1 = new InvincibilityPotion("i1", "invincibility_potion", 1, c1);
        c1.addInventory(i1);
        c1.useItem("invincibility_potion");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals("Invincible", c1.getStateName());
        c1.addInventory(new InvisibilityPotion("i2", "invisibility_potion", 1, c1));
        c1.useItem("invisibility_potion");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals("Invisible", c1.getStateName());
        c1.setHealth(20);
        c1.addInventory(new HealthPotion("i3", "health_potion", 1, c1));
        c1.useItem("health_potion");
        assertEquals(new ArrayList<Items>(), c1.getInventory());
        assertEquals(c1.getMaxHealth(), c1.getHealth());
    }
    // Testing building items with Character
    @Test
    public void buildTest() {
        Position start = new Position(10, 10);
        List<Items> items = new ArrayList<>();
        Character c1 = new Character("player", "Character", start, false, 100, 10, items, new ArrayList<>());
        Items a = new Items("a", "wood", 1, c1);
        Items b = new Items("b", "arrow", 1, c1);
        Items c = new Items("c", "arrow", 1, c1);
        Items d = new Items("d", "arrow", 1, c1);
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
    // Testing placing a bomb. Should turn bomb into entity same potion as player, but higher layer
    // This Test cannot be conducted until Game has been implemented. Need to find a way for this to access
    // list Entities from game.
   /* @Test
    public void useBomb() {
        // Creating a new game just to have access to entity list
        Game g1 = new Game("Test", "Peaceful", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "");
        Character c1 = new Character("player", "Character", new Position(10,11), false, 100, 10, new ArrayList<>(), g1.getEntities());
        g1.addEntity(c1);
        c1.addInventory(new Items("a", "bomb", 1));
        c1.useItem("bomb");
        assertEquals(0, c1.getInventory().size());
        List<Entity> entities = g1.getEntities;
        assertEquals(2, entities.size());
        Entity testBomb = entities.get(1);
        assertEquals("bomb", testBomb.getType());
        Position bPosition = testBomb.getPosition();
        assert(10, bPosition.getX());
        assert(11, bPosition.getY());
        assert(1, bPosition.getLayer());
    }*/


}
