package dungeonmania;

public class NormalState implements CharacterState{
    private Character character;

    public NormalState(Character character) {
        this.character = character;
    }
    public String getStateName() {
        return "Normal";
    }

    //public void battle(Mob mob) {}

}
