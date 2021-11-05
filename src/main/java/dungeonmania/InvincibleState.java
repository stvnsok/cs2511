package dungeonmania;

public class InvincibleState implements CharacterState {
    private int duration = 10;
    private Character character;

    public InvincibleState(Character character) {
        this.character = character;
    }

    public String getStateName() {
        return "Invincible";
    }
    
    @Override
    public int getStateDuration() {
        return this.duration;
    }

    @Override
    public void tickStateDuration() {
        this.duration -= 1;
    }

    @Override
    public void battle(Mob enemy) {
        // defeat enemy immediately
        enemy.takeDamage(enemy.getHealth());

        // remove enemy from observers and game??
        character.detach((CharacterObserver) enemy);
    }

}
