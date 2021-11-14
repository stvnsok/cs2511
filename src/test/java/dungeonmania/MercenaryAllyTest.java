package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Position;
public class MercenaryAllyTest {
    @Test
    public void allyMoveTest() {
        List<Entity> entities = new ArrayList<>();
        Entity a = EntityFactory.createEntity("rw3", new Position(1,3), "boulder");
        Entity b = EntityFactory.createEntity("rw3", new Position(1,3), "wall");
        Entity c = EntityFactory.createEntity("rw3", new Position(1,3), "door");
        Entity d = EntityFactory.createEntity("rw3", new Position(1,3), "zombie_toast_spawner");
        entities.add(a);
        entities.add(b);
        entities.add(c);
        entities.add(d);
        Mercenary merc = (Mercenary) EntityFactory.createEntity("mercenary", new Position(3, 2), "mercenary");
        EntityFactory fac = new EntityFactory();
        Character character = fac.createPlayer("r32", new Position(3,4), "player", "Hard", entities);
        // Should not bother checking if merc is not ally, or character is not on merc
        assertFalse(merc.allyMove(character, new Position(3,3), entities));
        merc.setPosition(new Position(3,3));
        assertFalse(merc.allyMove(character, new Position(3,4), entities));
        merc.setAlly(true);
        assertFalse(merc.allyMove(character, new Position(3,2), entities));
        assertTrue(merc.allyMove(character, new Position(3,4), entities));
        merc.setPosition(new Position(4,4));
        assertTrue(merc.allyMove(character, new Position(3,4), entities));
        assertEquals(new Position(4,4), merc.getPosition());
        // Should not move onto obstacles
        a.setPosition(new Position(3,5));
        merc.setPosition(new Position(3,4));
        assertTrue(merc.allyMove(character, new Position(3,4), entities));
        assertFalse(merc.getPosition().equals(character.getPosition()));
        assertFalse(merc.getPosition().equals(a.getPosition()));
        b.setPosition(new Position(3,3));
        c.setPosition(new Position(2,4));
        d.setPosition(new Position(4,4));
        assertTrue(merc.allyMove(character, new Position(3,4), entities));
        assertFalse(merc.getPosition().equals(character.getPosition()));    
    }
}
