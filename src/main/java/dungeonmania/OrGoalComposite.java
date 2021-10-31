package dungeonmania;

public class OrGoalComposite implements Goals {
    private Goals goal1;
    private Goals goal2;

    public OrGoalComposite(Goals goal1, Goals goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public String getGoal() {
        boolean g1 = goal1.fulfilledGoals();
        boolean g2 = goal2.fulfilledGoals();
        if (!g1 && !g2) {
            return "(" + goal1.getGoal() + " OR " + goal2.getGoal() + ")";
        }
        return "";
    
    }

    public boolean fulfilledGoals() {
        return goal1.fulfilledGoals() || goal2.fulfilledGoals();
    }
}
