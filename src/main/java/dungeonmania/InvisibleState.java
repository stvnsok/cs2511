package dungeonmania;

public class InvisibleState implements CharacterState{
    public int duration = 15;
    
    public InvisibleState() {

    }

    public String getStateName() {
        return "Invisible";
    }

    public int getStateDuration() {
        return this.duration;
    }

    public void tickStateDuration() {
        this.duration -= 1;
    }
    //public void battle(Mob mob) {}

}
