package dungeonmania;

public class AndGoalComposite implements Goals{
    private Goals goal1;
    private Goals goal2;

    public AndGoalComposite(Goals goal1, Goals goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public String getGoal() {
        return "AND " + goal1.getGoal() + " " + goal2.getGoal();
    }

    public boolean fulfilledGoals() {
        return goal1.fulfilledGoals() & goal2.fulfilledGoals();
    }


}
