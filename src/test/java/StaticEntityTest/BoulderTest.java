package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import dungeonmania.*;
import dungeonmania.Character;
import dungeonmania.response.*;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;





public class BoulderTest {
    


    @Test 
    public void moveDownBoulderTest() {


        Position start = new Position (3,2);

        
        Character character = new Character("player", "character",start ,false, 100, 10, null, null);
        
        Position bPosition = new Position(3,3);
        Boulder boulder = new Boulder("boulder1", "boulder", bPosition, true);

        character.PlayerMovement(Direction.DOWN);
<<<<<<< HEAD
<<<<<<< HEAD
        boulder.moveBoulder(Direction.DOWN);
=======
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)
=======
        boulder.moveBoulder(Direction.DOWN);
>>>>>>> 27288e5 (fixed some merge conflicts)
        //game.interact("boulder1");
        //player position
        Position newcPosition = new Position(3,3);
        Position newbPosition = new Position(3,4);
        assertEquals(newcPosition, character.getPosition());
<<<<<<< HEAD

=======
        
>>>>>>> 27288e5 (fixed some merge conflicts)

        // boulder position
        assertEquals(newbPosition, boulder.getPosition());

    }

    @Test
    public void moveBoulderOnSwitchTest() {


        Position start = new Position (3,2);

        
        Character character = new Character("player", "character",start ,false, 100, 10, null, null);
        
        Position bPosition = new Position(3,3, 0);
        Boulder boulder = new Boulder("boulder1", "boulder", bPosition, true);

        character.PlayerMovement(Direction.DOWN);
<<<<<<< HEAD
<<<<<<< HEAD
        boulder.moveBoulder(Direction.DOWN);
=======
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)

=======
        boulder.moveBoulder(Direction.DOWN);
>>>>>>> 27288e5 (fixed some merge conflicts)
        Position fPosition = new Position (3,4);
        FloorSwitch fSwitch = new FloorSwitch("floorswitch1", "switch", fPosition, false);

<<<<<<< HEAD
        assertEquals(boulder.getPosition(), fPosition);
        fSwitch.checkFloorSwitch(boulder);
=======
        assertEquals(boulder.getPosition(),fSwitch.getPosition());
        fSwitch.checkFloorSwitch(boulder);


>>>>>>> 27288e5 (fixed some merge conflicts)

        assertEquals(true, fSwitch.getisTriggered());

    }   
    
    //the boulder cannot be pushed through the wall or door
    //if the boulders position remains the same then it does not go through the wall 
    // hard to do tests if the game is has not been implemented
    @Test
    public void moveBoulderintoWallTest(){

        

        Position start = new Position (10,10);

        Character character = new Character("player", "character", start, false, 100, 10, null, null);

        Position b1Position = new Position(11,10,0);
        Position b2Position = new Position(9,10,0);

        
        Boulder b1 = new Boulder("boulder1", "boulder", b1Position, true);
        Boulder b2 = new Boulder("boulder1", "boulder", b2Position, true);

        Position w1Position = new Position(12, 10);
        Position w2Position = new Position (8,10);

        Wall w1 = new Wall("wall1", "wall", w1Position, false);
        Wall w2 = new Wall("wall2", "wall", w2Position, false);


        character.PlayerMovement(Direction.LEFT);
        assertEquals(b1Position, b1.getPosition());
<<<<<<< HEAD
        character.PlayerMovement(Direction.RIGHT);;
        assertEquals(b2Position, b2.getPosition());
  
=======

        character.PlayerMovement(Direction.RIGHT);;
        assertEquals(b2Position, b2.getPosition());

>>>>>>> 27288e5 (fixed some merge conflicts)

    }

    @Test 

    public void moveBoulderintoDoorTest() {
        Position start = new Position (10,10);

        Character character = new Character("player", "character", start, false, 100, 10, null, null);
        Position b1Position = new Position(11,10);
        Boulder b1 = new Boulder("boulder1", "boulder", b1Position, true);
        Position d1Position = new Position(12, 10);
       
        Door d1 = new Door("door1", "Door", d1Position, true, false);
       


        character.PlayerMovement(Direction.LEFT);

<<<<<<< HEAD
<<<<<<< HEAD
        assertEquals(b1Position, b1.getPosition());

    

        assertEquals(d1Position, d1.getPosition());
=======
        assertEquals(b1Position.getX(), b1.getPosition().getX());
        assertEquals(b1Position.getY(), b1.getPosition().getY());
        assertEquals(b1Position.getLayer(), b1.getPosition().getLayer());
=======
        assertEquals(b1Position, b1.getPosition());

>>>>>>> 27288e5 (fixed some merge conflicts)
    
>>>>>>> 17b8173 (fixed some minor errors with test and also added portal, although it is not working as intended yet)

<<<<<<< HEAD
=======
        assertEquals(d1Position, d1.getPosition());

>>>>>>> 27288e5 (fixed some merge conflicts)
    }



}
