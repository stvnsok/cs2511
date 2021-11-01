package dungeonmania;

import java.util.List;

public interface Enemies extends CharacterObserver {
    
    public void move(List<Entity> entities, Character character);

    //public void dropRing;
}
