package StaticEntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import dungeonmania.*;
import dungeonmania.Character;
import dungeonmania.response.*;
import dungeonmania.response.models.DungeonResponse;

import dungeonmania.util.Position;





public class BoulderTest {
    


    @Test 
    public void moveDownBoulderTest() {

        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");

        Position start = new Position (3,2);

        
        Character character = new Character("player", "character",start ,false, 100, 10, null);
        
        Position bPosition = new Position(3,3);
        Boulder boulder = new Boulder("boulder1", "boulder", bPosition, true);

        character.moveDown();
        //game.interact("boulder1");
        //player position
        Position newcPosition = new Position(3,2);
        Position newbPosition = new Position(3,4);
        assertEquals(newcPosition.getX(), character.getPosition().getX());
        assertEquals(newcPosition.getY(), character.getPosition().getY());

        // boulder position
        assertEquals(newbPosition, boulder.getPosition());

    }

    @Test
    public void moveBoulderOnSwitchTest() {
        DungeonManiaController game = new DungeonManiaController();
        game.newGame("maze.json", "Standard");

        Position start = new Position (3,2);

        
        Character character = new Character("player", "character",start ,false, 100, 10, null);
        
        Position bPosition = new Position(3,3, 0);
        Boulder boulder = new Boulder("boulder1", "boulder", bPosition, true);

        character.moveDown();

        Position fPosition = new Position (3,4);
        FloorSwitch fSwitch = new FloorSwitch("floorswitch1", "floorswitch", fPosition, false);

        assertEquals(bPosition.getX(),fPosition.getX());
        assertEquals(bPosition.getY(),fPosition.getY());
        assertEquals(bPosition.getLayer(),fPosition.getLayer());

        assertEquals(true, fSwitch.getisTriggered());

    }   
    
    //the boulder cannot be pushed through the wall or door
    //if the boulders position remains the same then it does not go through the wall 
    // hard to do tests if the game is has not been implemented
    @Test
    public void moveBoulderintoWallTest(){

        

        Position start = new Position (10,10);

        Character character = new Character("player", "character", start, false, 100, 10, null);

        Position b1Position = new Position(11,10,0);
        Position b2Position = new Position(9,10,0);


        Boulder b1 = new Boulder("boulder1", "boulder", b1Position, true);
        Boulder b2 = new Boulder("boulder1", "boulder", b2Position, true);

        Position w1Position = new Position(12, 10);
        Position w2Position = new Position (8,10);

        Wall w1 = new Wall("wall1", "wall", w1Position, false);
        Wall w2 = new Wall("wall2", "wall", w2Position, false);


        character.moveLeft();
        assertEquals(b1Position.getX(), b1.getPosition().getX());
        assertEquals(b1Position.getY(), b1.getPosition().getY());
        assertEquals(b1Position.getLayer(), b1.getPosition().getLayer());
        character.moveRight();
        assertEquals(b2Position.getX(), b2.getPosition().getX());
        assertEquals(b2Position.getY(), b2.getPosition().getY());
        assertEquals(b2Position.getLayer(), b2.getPosition().getLayer());

    }

    @Test 

    public void moveBoulderintoDoorTest() {
        Position start = new Position (10,10);

        Character character = new Character("player", "character", start, false, 100, 10, null);
        Position b1Position = new Position(11,10);
        Boulder b1 = new Boulder("boulder1", "boulder", b1Position, true);
        Position d1Position = new Position(12, 10);
       
        Door d1 = new Door("door1", "Door", d1Position, true, false);
       


        character.moveLeft();
        assertEquals(d1.getPosition(), d1Position);

        assertEquals(11, d1Position.getX());
        assertEquals(9, d1Position.getY());
        assertEquals(1, d1Position.getLayer());


        assertEquals(d1Position.getX(), d1.getPosition().getX());
        assertEquals(d1Position.getY(), d1.getPosition().getY()));
        assertEquals(d1Position.getLayer(), d1.getPosition().getLayer());
    }



}
