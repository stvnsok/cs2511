package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Position;

public class FactoryTests {
    @Test
    public void EntityFactoryTest() {
        JSONArray entArray = new JSONArray();
        JSONObject door = new JSONObject();
        JSONObject key = new JSONObject();
        JSONObject swamp = new JSONObject();
        JSONObject portal = new JSONObject();
        JSONObject player = new JSONObject();
        JSONObject wall = new JSONObject();

        door.put("x", 1);
        door.put("y", 1);
        door.put("type", "door");
        door.put("key", 4);
        entArray.put(door);
        key.put("x", 1);
        key.put("y", 1);
        key.put("type", "key");
        key.put("key", 4);
        entArray.put(key);
        swamp.put("x", 1);
        swamp.put("y", 1);
        swamp.put("type", "swamp_tile");
        swamp.put("movement_factor", 4);
        entArray.put(swamp);
        portal.put("x", 1);
        portal.put("y", 1);
        portal.put("type", "portal");
        portal.put("colour", "red");
        entArray.put(portal);
        player.put("x", 1);
        player.put("y", 1);
        player.put("type", "player");
        entArray.put(player);
        wall.put("x", 1);
        wall.put("y", 1);
        wall.put("type", "wall");
        entArray.put(wall);

        EntityFactory fac = new EntityFactory();
        List<Entity> entities = fac.createEntity(entArray, "Peaceful");
        assertEquals(6, entities.size());
        assertEquals("door", entities.get(0).getType());
        Door entDoor = (Door) entities.get(0);
        assertEquals(4, entDoor.getKeyId());
        assertEquals("key", entities.get(1).getType());
        KeyEntity entkey = (KeyEntity) entities.get(1);
        assertEquals(4, entkey.getKeyId());
        assertEquals("swamp_tile", entities.get(2).getType());
        SwampTile entSwamp = (SwampTile) entities.get(2);
        assertEquals(4, entSwamp.getMovementFactor());
        assertEquals("red_portal", entities.get(3).getType());
        Portal entPortal = (Portal) entities.get(3);
        assertEquals("red", entPortal.getColour());
        assertEquals("player", entities.get(4).getType());
        Character entCharacter= (Character) entities.get(4);
        assertEquals(20, entCharacter.getHealth());
        assertEquals("wall", entities.get(5).getType());

        Character char2 = fac.createPlayer("3", new Position(1,2), "player", "Hard", new ArrayList<>());
        assertEquals(10, char2.getHealth());
        assertEquals(10, fac.getCharacter().getHealth());

        assertEquals("zombie_toast", EntityFactory.createEntity("3", new Position(1,2), "zombie_toast").getType());
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "zombie_toast") instanceof Zombie);
        assertEquals("spider", EntityFactory.createEntity("3", new Position(1,2), "spider").getType());
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "spider") instanceof Spider);
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "mercenary") instanceof Mercenary);
        assertEquals("boulder", EntityFactory.createEntity("3", new Position(1,2), "boulder").getType());
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "boulder") instanceof Boulder);
        assertEquals("zombie_toast_spawner", EntityFactory.createEntity("3", new Position(1,2), "zombie_toast_spawner").getType());
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "zombie_toast_spawner") instanceof ZombieToastSpawner);
        assertEquals("hydra", EntityFactory.createEntity("3", new Position(1,2), "hydra").getType());
        assertTrue(EntityFactory.createEntity("3", new Position(1,2), "hydra") instanceof Zombie);
    }

    @Test
    public void ItemFactoryTest() {
        JSONArray itemArray = new JSONArray();
        JSONObject jKey = new JSONObject();
        JSONObject jTreasure = new JSONObject();
        jKey.put("id", "key1");
        jKey.put("type", "key");
        jKey.put("durability", 1);
        jKey.put("key", 3);
        itemArray.put(jKey);
        jTreasure.put("id", "gold");
        jTreasure.put("type", "treasure");
        jTreasure.put("durability", 1);
        itemArray.put(jTreasure);
        List<Items> items = ItemFactory.createInventory(itemArray);
        assertEquals("key", items.get(0).getItemType());
        Key kKey = (Key) items.get(0);
        assertEquals(3, kKey.getKeyId());

        assertEquals("sword", ItemFactory.createItem("wer", "sword").getItemType());
        assertEquals(7, ItemFactory.createItem("wer", "sword").getDurability());
        assertEquals("armour", ItemFactory.createItem("wer", "armour").getItemType());
        assertEquals(7, ItemFactory.createItem("wer", "armour").getDurability());
        assertEquals("anduril", ItemFactory.createItem("wer", "anduril").getItemType());
        assertEquals(14, ItemFactory.createItem("wer", "anduril").getDurability());
        assertTrue(ItemFactory.createItem("wer", "anduril") instanceof Sword);
        assertEquals("arrow",ItemFactory.createItem("wre", "arrow").getItemType());

        assertEquals("health_potion", ItemFactory.createItem("w3r", "health_potion").getItemType());
        assertTrue(ItemFactory.createItem("w3r", "health_potion") instanceof HealthPotion);
        assertEquals("invisibility_potion", ItemFactory.createItem("w3r", "invisibility_potion").getItemType());
        assertTrue(ItemFactory.createItem("w3r", "invisibility_potion") instanceof InvisibilityPotion);
        assertEquals("invincibility_potion", ItemFactory.createItem("w3r", "invincibility_potion").getItemType());
        assertTrue(ItemFactory.createItem("w3r", "invincibility_potion") instanceof InvincibilityPotion);
        assertEquals("bomb", ItemFactory.createItem("w3r", "bomb").getItemType());
        assertTrue(ItemFactory.createItem("w3r", "bomb") instanceof Bomb);
        assertEquals("one_ring", ItemFactory.createItem("w3r", "one_ring").getItemType());
        assertTrue(ItemFactory.createItem("w3r", "one_ring") instanceof TheOneRing);
        assertTrue(ItemFactory.createBuildable("wf", "sceptre") instanceof Sceptre);
        Sceptre sceptre = (Sceptre) ItemFactory.createBuildable("wf", "sceptre");
        assertEquals(3, sceptre.getDurability());
        assertTrue(ItemFactory.createBuildable("wf", "bow") instanceof Bow);
        Bow bow = (Bow) ItemFactory.createBuildable("wf", "bow");
        assertEquals(3, bow.getDurability());
        assertTrue(ItemFactory.createBuildable("wf", "shield") instanceof Shield);
        Shield shield = (Shield) ItemFactory.createBuildable("wf", "shield");
        assertEquals(5, shield.getDurability());
        assertTrue(ItemFactory.createBuildable("wf", "midnight_armour") instanceof MidnightArmour);
        MidnightArmour midArm = (MidnightArmour) ItemFactory.createBuildable("wf", "midnight_armour");
        assertEquals(12, midArm.getDurability());
        assertEquals(null, ItemFactory.createBuildable("wf", "shidBot"));

    }
}
