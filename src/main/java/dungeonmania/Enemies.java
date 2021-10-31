package dungeonmania;

import java.util.List;

public interface Enemies extends CharacterObserver {
    
    public void move(List<Entity> entities);

    //public void dropRing;
}
