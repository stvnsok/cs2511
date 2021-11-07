package dungeonmania;

import java.util.Random;

public class TheOneRing extends Items {

    public TheOneRing(String itemId) {
        super(itemId, "theOneRing", 1);
    }

    /**
     * Determine whether the enemy drop the one ring or not without seed.
     * @return true if it drops
     */
    public boolean doesDrop() {
        Random r = new Random();
        if (r.nextInt(100) < 10) {
            return true;
        }
        return false;
    }

    /**
     * Determine whether the enemy drop the one ring or not with seed (for test purpose).
     * @return true if it drops
     */
    public boolean doesDrop(int seed) {
        Random r = new Random(seed);
        if (r.nextInt(100) < 10) {
            return true;
        }
        return false;
    }

    /**
     * Restores character's health to its initial value, before reducing its uses through super.
     */
    @Override
    public void use(Character character) {
        character.setHealth(character.getMaxHealth());
        super.use(character);
    }
}
