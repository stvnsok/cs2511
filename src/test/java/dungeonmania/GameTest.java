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
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "Hard", entities, inventory, new ArrayList<>(), object, character);
        
        Wall w1 = new Wall("w1", "wall", new Position (6,6), false);
        Wall w2 = new Wall("w2", "wall", new Position (10,10), false);
        entities.add(w1);
        entities.add(w2);
        entities.add(character);
        IntStream.range(0, 51).forEach(i -> {
            g.tick(null, Direction.NONE);

        });
        
        long count = g.getEntities()
            .stream()
            .filter(e -> e.getType().equals("hydra"))
            .count();

        assertEquals(1, count);
        
    }
    
}
