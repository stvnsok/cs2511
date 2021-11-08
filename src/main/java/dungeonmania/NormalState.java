package dungeonmania;

public class NormalState implements CharacterState{
    private Character character;

    public NormalState(Character character) {
        this.character = character;
    }
    public String getStateName() {
        return "Normal";
    }

    @Override
    public void battle(Mob enemy) {
        // get enemy initial health so enemy and player can take damage at "same time"
        int enemyHealth = enemy.getHealth();

        // for testing!!!
        // System.out.println("Battle against " + enemy.getId() + "!");
        // System.out.println("Player Health before: " + character.getHealth());
        // System.out.println("Enemy Health before: " + enemy.getHealth());

        int characterDamage = character.getHealth() * character.getAttack() / 5;
        int enemyDamage = enemyHealth * enemy.getAttack() / 10;

        Shield shield = character.getShield();
        Armour cArmour = character.getArmour();
        Sword sword = character.getSword();
        Bow bow = character.getBow();

        if (shield != null) { // if player has shield
            enemyDamage = enemyDamage * 3 / 4; // decrease damage by 25%
            shield.use(character);
        }

        if (cArmour != null) { // if player has armour
            enemyDamage = enemyDamage / 2; // halve damage
            cArmour.use(character);
        }

        if (sword != null) { // if player has sword
            characterDamage = characterDamage * 2; // double damage
            sword.use(character);
        }

        if (bow != null) { // if player has bow
            enemy.takeDamage(characterDamage); // allows second attack
            bow.use(character);
        }

        Armour eArmour = null;

        if (enemy instanceof Zombie) {
            Zombie zombie = (Zombie) enemy;
            eArmour = zombie.getArmour();

            if (eArmour != null) { // if zombie has armour
                characterDamage = characterDamage / 2; // halve damage
                eArmour.use(zombie);
            }
        
        } else if (enemy instanceof Mercenary) {
            Mercenary mercenary = (Mercenary) enemy;
            eArmour = mercenary.getArmour();

            if (eArmour != null) { // if mercenary has armour
                characterDamage = characterDamage / 2; // halve damage
                eArmour.use(mercenary);
            }
        }

        enemy.takeDamage(characterDamage);
        character.takeDamage(enemyDamage);

        // if enemy health <= 0, remove from game
        if (enemy.getHealth() <= 0) {
            character.removeEnemy(enemy, eArmour);
        }

        // if player health <= 0
        if (character.getHealth() <= 0) {
            // use the one ring if exist
            TheOneRing ring = character.getTheOneRing();
            if (ring != null) {
                ring.use(character);
            } else {
                // remove from game
                character.mapRemove(character);
            }
        }

        // for testing!!!
        // System.out.println("Player Health after: " + character.getHealth());
        // System.out.println("Enemy Health after: " + enemy.getHealth());
    }
    
}
