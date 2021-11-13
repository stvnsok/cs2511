package dungeonmania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dungeonmania.util.Position;

// public interface Enemies extends CharacterObserver {
public interface Enemies {
    
    /**
     * Move a mob based on its supposed behavior
     * @param entities
     * @param character
     */
    public void move(List<Entity> entities, Character character);

    /**
     * Determine can an enemy can move or not
     * @return true if can move
     */
    public boolean canMove();

    public void isOnSwampTile(ArrayList<SwampTile> swampTilePosition);

    //public void dropRing;
}
