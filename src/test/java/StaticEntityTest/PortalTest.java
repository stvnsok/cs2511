package StaticEntityTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dungeonmania.Portal;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.Character;
import dungeonmania.Entity;

import org.junit.jupiter.api.Test;

public class PortalTest {
   @Test
   public void testPortal() {


      List<Entity> entities = new ArrayList<>();

      Position p1 = new Position(10,10,0);
      Portal startPortal = new Portal("portal1", "portal", p1, false);
      entities.add(startPortal);
      startPortal.setportalId(100);
      
      Position p2 = new Position(5,5, 0);
      Portal endPortal = new Portal("portal2", "portal", p2, false);
      entities.add(endPortal);
      
      
      endPortal.setportalId(100);

      Position start = new Position(9,10,0);
      Character c1 = new Character("character1", "character", start, false, 100, 10, null);

      c1.PlayerMovement(Direction.DOWN);

      startPortal.teleport(c1, startPortal.getportalId());
      
      assertEquals(endPortal.getPosition(), c1.getPosition());
   } 

}
