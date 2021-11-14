package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Position;

public class GameTest {

    @Test
    public void entityGameTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        ZombieToastSpawner spawner = new ZombieToastSpawner("spawner", "zombie_toast_spawner", new Position(2, 2), true);
        g.addEntity(spawner);

        assertEquals(1, g.getEntities().size());
        assertTrue(g.hasEntity(spawner));
        g.removeEntity(spawner);
        assertEquals(0, g.getEntities().size());

    }
    
}
