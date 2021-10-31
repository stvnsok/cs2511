package StaticEntityTest;

import dungeonmania.*;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Collections;
import dungeonmania.Game;

import org.junit.jupiter.api.Test;
public class ZombieToastSpawnerTest {
    
    @Test

    public void spawnerTest() {
        List<Entity> entities = new ArrayList<>();
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");

        Position p1 = new Position(7,3,0);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombie_toast_spawner", p1, false);
        
        Game game = controller.getCurrentGame();

        //move around tick

        IntStream.range(0, 20).forEach(i -> {
            if(i == 20) {
                z1.spawnZombie();
                entities.add(z1.spawnZombie());
            }
            else {
                DungeonResponse d = controller.tick("", Direction.DOWN);
            }

        });

        
        assertEquals(1, Collections.frequency(entities, z1.spawnZombie().getType()));


        
        



    }
}
