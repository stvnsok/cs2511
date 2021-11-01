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
<<<<<<< HEAD
<<<<<<< HEAD
      Portal startPortal = new Portal("portal1", "portal", p1, false,"BLUE");
=======
      Portal startPortal = new Portal("portal1", "portal", p1, false);
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
=======
      Portal startPortal = new Portal("portal1", "portal", p1, false, "BLUE");
>>>>>>> 27288e5 (fixed some merge conflicts)
      entities.add(startPortal);
      
      
      Position p2 = new Position(5,5, 0);
<<<<<<< HEAD
<<<<<<< HEAD
      Portal endPortal = new Portal("portal2", "portal", p2, false, "BLUE");
=======
      Portal endPortal = new Portal("portal2", "portal", p2, false);
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
=======
      Portal endPortal = new Portal("portal2", "portal", p2, false, "BLUE");
>>>>>>> 27288e5 (fixed some merge conflicts)
      entities.add(endPortal);
      
      
      

      Position start = new Position(9,10,0);
      Character c1 = new Character("character1", "character", start, false, 100, 10, null, entities);

      c1.PlayerMovement(Direction.DOWN);

<<<<<<< HEAD
<<<<<<< HEAD
      startPortal.teleport(c1, startPortal.getColour());
      
<<<<<<< HEAD
      assertEquals(endPortal.getPosition(), c1.getPosition());
=======
      startPortal.teleport(c1, startPortal.getportalId());
=======
      startPortal.teleport(c1, startPortal.getColour());
>>>>>>> b6e506b (fix portal test)
      
      assertEquals(c1.getPosition().getX(), endPortal.getPosition().getX());
      assertEquals(c1.getPosition().getY(), endPortal.getPosition().getY());
      assertEquals(c1.getPosition().getLayer(), endPortal.getPosition().getLayer());
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
=======
      assertEquals(c1, endPortal.getPosition());

>>>>>>> 27288e5 (fixed some merge conflicts)
   } 

}
