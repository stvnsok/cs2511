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
        
        enemy.takeDamage(character.getHealth() * character.getAttack() / 5);
        character.takeDamage(enemyHealth * enemy.getAttack() / 10);

        // check health of enemy and character?
        // if <= 0, remove from observers and game?
        if (enemy.getHealth() <= 0) {
            character.detach((CharacterObserver) enemy);
        }
    }
}
