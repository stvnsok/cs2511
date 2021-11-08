package dungeonmania;

import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity {

    public ZombieToastSpawner(String id, String type, Position position, boolean isInteractable) {
        super(id, type, position, isInteractable);
    }
    


    public Zombie spawnZombie() {
        return new Zombie("zombie", "zombie_toast", getPosition(), false, 50, 5);
    }

}
