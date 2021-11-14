package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class GameTest {

    @Test
    public void testHydraSpawn() {
           
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(5,5), false, 100, 10, inventory, entities, "Standard");
        Game g = new Game("empty.json", "Hard", entities, inventory, new ArrayList<>(), object, character);
        
        Wall w1 = new Wall("w1", "wall", new Position (1,1), false);
        Wall w2 = new Wall("w2", "wall", new Position (11,11), false);
        entities.add(w1);
        entities.add(w2);
        // leave character out of entities for this test
        IntStream.range(0, 51).forEach(i -> {
            g.tick(null, Direction.NONE);

        });
        
        long count = g.getEntities()
            .stream()
            .filter(e -> e.getType().equals("hydra"))
            .count();

        assertEquals(1, count); 

    }

    @Test
    public void buildCheckTest() {
        // Initialise Game to look at buildables
        List<Entity> ents = new ArrayList<>();
        ents.add(EntityFactory.createEntity("wr", new Position(2,3), "exit"));
        EntityFactory fac = new EntityFactory();
        Character player = (Character) fac.createPlayer("rq", new Position(3, 3), "player", "hard", ents);
        ents.add(player);
        List<Items> items = player.getInventory();
        items.add(ItemFactory.createItem("3r2", "wood"));
        items.add(ItemFactory.createItem("3r2w3", "arrow"));
        items.add(ItemFactory.createItem("3er2w3", "treasure"));
        items.add(ItemFactory.createItem("wr", "key", 2, 3));
        items.add(ItemFactory.createItem("2r3", "sun_stone"));
        items.add(ItemFactory.createItem("2333r3", "armour"));
        items.add(ItemFactory.createItem("3re2w3", "arrow"));

        JSONObject goals = new JSONObject();
        goals.put("goal", "exit");
        Game g = new Game("r32", "Hard", ents, items, new ArrayList<>(), goals, player);
        g.tick(null, Direction.NONE);
        // Buildable items should only be these
        assertEquals(2, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("midnight_armour"));
        assertTrue(g.getBuildables().contains("sceptre"));

        // Removing an armour should get rid of midnight_armour buildable
        items.remove(items.stream().filter(e -> e.getItemType().equals("armour")).findFirst().get());
        g.tick(null, Direction.NONE);
        assertEquals(1, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("sceptre"));
        items.add(ItemFactory.createItem("2333r3", "armour"));
        // Removing the sunstone should get rid of both
        items.remove(items.stream().filter(e -> e.getItemType().equals("sun_stone")).findFirst().get());
        g.tick(null, Direction.NONE);
        assertEquals(0, g.getBuildables().size());
        items.add(ItemFactory.createItem("2r3", "sun_stone"));
        // Spawning a zombie should not allow the midnight_armour to be built
        ents.add(EntityFactory.createEntity("q3r", new Position(9,9), "zombie_toast"));
        g.tick(null, Direction.NONE);
        assertEquals(1, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("sceptre"));

        items.remove(items.stream().filter(e -> e.getItemType().equals("sun_stone")).findFirst().get());
        items.remove(items.stream().filter(e -> e.getItemType().equals("armour")).findFirst().get());

        items.add(ItemFactory.createItem("3re2rw3", "arrow"));
        g.tick(null, Direction.NONE);
        // Should be able to build bow with extra wood.
        assertEquals(1, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("bow"));
        // Getting rid of the wood should make it not able to build bow anymore
        items.remove(items.stream().filter(e -> e.getItemType().equals("wood")).findFirst().get());
        g.tick(null, Direction.NONE);
        assertEquals(0, g.getBuildables().size());
        // Adding more wood should allow shield to be built.
        items.add(ItemFactory.createItem("3re2rww3", "wood"));
        items.add(ItemFactory.createItem("3re2rwrw3", "wood"));
        g.tick(null, Direction.NONE);
        assertEquals(2, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("bow"));
        assertTrue(g.getBuildables().contains("shield"));
        items.remove(items.stream().filter(e -> e.getItemType().equals("key")).findFirst().get());
        // Should still be able to build with the treasure
        g.tick(null, Direction.NONE);
        assertEquals(2, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("bow"));
        assertTrue(g.getBuildables().contains("shield"));
        // Without key or treasure, shield cannot be built.
        items.remove(items.stream().filter(e -> e.getItemType().equals("treasure")).findFirst().get());
        g.tick(null, Direction.NONE);
        assertEquals(1, g.getBuildables().size());
        assertTrue(g.getBuildables().contains("bow"));



    }
    
}
