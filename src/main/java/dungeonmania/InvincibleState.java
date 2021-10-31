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

    //public void battle(Mob mob) {}

}
