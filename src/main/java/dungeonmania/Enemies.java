package dungeonmania;

import java.util.List;

// public interface Enemies extends CharacterObserver {
public interface Enemies {
    
    /**
     * Move a mob based on its supposed behavior
     * @param entities
     * @param character
     */
    public void move(List<Entity> entities, Character character);

    //public void dropRing;
}
