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

<<<<<<< HEAD
=======
import org.json.JSONObject;
>>>>>>> 27288e5 (fixed some merge conflicts)
import org.junit.jupiter.api.Test;
public class ZombieToastSpawnerTest {
    
    @Test

    public void spawnerTest() {
        List<Entity> entities = new ArrayList<>();
<<<<<<< HEAD
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");
=======
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goal", "treasure");

        Game game = new Game("dungeon1", "Standard", entities, new ArrayList<>(), new ArrayList<>(), jsonObject);
        
        DungeonManiaController dungeonManiaController = new DungeonManiaController();
        dungeonManiaController.setCurrentGame(game);
        dungeonManiaController.getDungeonResponse();

>>>>>>> 27288e5 (fixed some merge conflicts)

        Position p1 = new Position(7,3,0);
        ZombieToastSpawner z1 = new ZombieToastSpawner("zombietoastspawner1", "zombie_toast_spawner", p1, false);
        
        

        //move around tick

        IntStream.range(0, 20).forEach(i -> {
            if(i == 20) {
                z1.spawnZombie();
                entities.add(z1.spawnZombie());
            }
            else {
<<<<<<< HEAD
                game.tick("", Direction.DOWN);
=======
                game.tick(Direction.LEFT);
>>>>>>> 27288e5 (fixed some merge conflicts)
            }

        });

        
        assertEquals(1, Collections.frequency(entities, z1.spawnZombie().getType()));


        
        



    }
}
