package StaticEntityTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import dungeonmania.Portal;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.Character;

import org.junit.jupiter.api.Test;

public class PortalTest {
   @Test
   public void testPortal() {


      Position p1 = new Position(10,10,0);
      Portal startPortal = new Portal("portal1", "portal", p1, false);

      startPortal.setportalId(100);
      
      Position p2 = new Position(5,5, 0);
      Portal endPortal = new Portal("portal2", "portal", p2, false);

      endPortal.setportalId(100);

      Position start = new Position(9,10,0);
      Character c1 = new Character("character1", "character", start, false, 100, 10, null);

      c1.PlayerMovement(Direction.DOWN);

      startPortal.teleport(c1, startPortal.getportalId());

      assertEquals(c1.getPosition().getX(), endPortal.getPosition().getX());
      assertEquals(c1.getPosition().getY(), endPortal.getPosition().getY());
      assertEquals(c1.getPosition().getLayer(), endPortal.getPosition().getLayer());
   } 

}
