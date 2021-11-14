package dungeonmania;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.exceptions.InvalidActionException;

import dungeonmania.util.Position;

public class interactTest {
    
    @Test 
    public void mercenaryNotInRangeTest() {

        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        Mercenary m = new Mercenary("m1", "mercenary", new Position(5, 5), true, 10, 10, 10, false);
        entities.add(m);
        entities.add(character);
        assertThrows(InvalidActionException.class, () -> g.interact(m.getId()));

    }

    @Test 
    public void mercenaryBribeNoTreasureTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        Mercenary m = new Mercenary("m1", "mercenary", new Position(0, 1), true, 10, 10, 10, false);
        entities.add(m);
        entities.add(character);
        assertThrows(InvalidActionException.class, () -> g.interact(m.getId()));
    }

    @Test
    public void mercenaryBribeTreasureTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);


        inventory.add(new Items("t1", "treasure",1 ));
        inventory.add(new Items("t2", "treasure",1));
        inventory.add(new Items("t3", "treasure",1));
        inventory.add(new Items("s1", "sun_stone", 1));
        Mercenary m = new Mercenary("m1", "mercenary", new Position(0, 1), true, 10, 10, 2, false);
        entities.add(m);
        entities.add(character);
        
        g.interact("m1");
        assertFalse(m.isInteractable());
        assertTrue(m.isAlly());
        
    }

    @Test
    public void assassinBribeNoRingTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        inventory.add(new Items ("t1", "treasure", 1));
        Mercenary m = new Mercenary("a1", "assassin", new Position(0, 1), true, 10, 10, 1, false);
        entities.add(m);
        entities.add(character);
        
        assertThrows(InvalidActionException.class, () -> g.interact(m.getId()));
    }

    @Test
    public void assassinBribeRingTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        Mercenary m = new Mercenary("a1", "assassin", new Position(0, 1), true, 10, 10, 1, false);
        entities.add(m);
        entities.add(character);
        inventory.add(new Items ("t1", "treasure", 1));
        inventory.add(new TheOneRing("o1", "one_ring", 1));
        g.interact("a1");
        assertFalse(m.isInteractable());
        assertTrue(m.isAlly());

    }
    
    @Test
    public void mercenarySceptreControlTest() {
        List<Entity> entities = new ArrayList<>();
        List<Items> inventory = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Character character = new Character("c1", "player", new Position(0, 0), false, 100, 10, inventory, new ArrayList<>(), "Standard");
        Game g = new Game("empty.json", "standard", entities, inventory, new ArrayList<>(), object, character);
        
        Mercenary m = new Mercenary("a1", "assassin", new Position(0, 1), true, 10, 10, 1, false);
        entities.add(m);
        entities.add(character);
        inventory.add(new Items ("s1", "sceptre", 1));

        g.interact("a1");
        assertFalse(m.isInteractable());
        assertTrue(m.isAlly());
        assertEquals(10, m.getControlDuration());
    }


    
}
