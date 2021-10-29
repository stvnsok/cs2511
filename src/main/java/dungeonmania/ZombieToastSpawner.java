package dungeonmania;

import dungeonmania.util.Position;
import dungeonmania.Zombie;

public class ZombieToastSpawner extends Entity {

    public ZombieToastSpawner(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }

    public Zombie spawnZombie() {
        return new Zombie("zombie", "zombie_toast", getPosition(), false, 50, 5);
    }


    

<<<<<<< HEAD
    public Zombie spawnZombie() {
        return new Zombie("zombie", "zombie_toast", getPosition(), false, 50, 5);
    }


=======
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
}
