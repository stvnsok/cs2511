package dungeonmania;

public class InvincibleState implements CharacterState {
    private int duration = 10;


    public InvincibleState() {

    }

    public String getStateName() {
        return "Invincible";
    }
    //public void battle(Mob mob) {}
    @Override
    public int getStateDuration() {
        return this.duration;
    }

    @Override
    public void tickStateDuration() {
        this.duration -= 1;
    }

}
