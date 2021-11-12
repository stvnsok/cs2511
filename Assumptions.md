Item Assumptions:
1. If duplicate items present in inventory, use the ones that appear first.
2. Bombs are placed on same position as player on the same layer.
3. Swords and Bows can be used in combination ie. if both are present in the inventory, character attacks twice with higher attack.
4. Duplicate weapons cannot be stacked. For example, if character has multiple swords, dmg bonus applies only once, and uses
   durability of one sword.
5. Using an item decreases its durability by 1.
6. Potions have a 'duration' of multiple ticks, and invincibility does not end after one battle.
7. Bribery prioritises sceptre over treasure/sunstone/ring.

Dungeon Mania Controller Assumptions:
1. The id of dungeons is defined "dungeon" concatenated with current dungeon number.
2. The id of entities is defined as entities type concatenated with current entities number.
3. Function saveGame would throw IllegalArgumentException if name already exist.
4. saveGame will save the game in JSON format under directory "src/main/java/dungeonmania/save".

Battle Assumptions:
1. The character and enemy takes damage at the same time in battles.
2. Allies attack enemies after the character attacks during a battle.
3. If an ally kills an enemy with armour, the character receives the enemy's armour.
4. If an ally with armour is killed by an enemy, the character receives the ally's armour.
