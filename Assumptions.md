<<<<<<< HEAD
The id of dungeons is defined "dungeon" concatenated with current dungeon number.
The id of entities is defined as entities type concatenated with current entities number.
Function saveGame would throw IllegalArgumentException if name already exist.
=======
Item Assumptions:
1. If duplicate items present in inventory, use the ones that appear first.
2. Bombs are placed on same position as player on the same layer.
3. Swords and Bows can be used in combination ie. if both are present in the inventory, character attacks twice with higher attack.
4. Duplicate weapons cannot be stacked. For example, if character has multiple swords, dmg bonus applies only once, and uses
   durability of one sword.
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> de6108a (Item tests implemented, minor changes made to CharacterState(and associated subclasses) to facilitate tests and enabled use and build to allow tests to be written. Assumptions with task updated)
=======
5. Using an item decreases its durability by 1.
>>>>>>> a11f225 (Added extra assumption about item usage)
=======
5. Using an item decreases its durability by 1.
6. Potions have a 'duration' of multiple ticks, and invincibility does not end after one battle.
>>>>>>> 5b1300d (Potions implemented and passes tests.)
