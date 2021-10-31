package dungeonmania;

public class AndGoalComposite implements Goals{
    private Goals goal1;
    private Goals goal2;

    public AndGoalComposite(Goals goal1, Goals goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public String getGoal() {
        boolean g1 = goal1.fulfilledGoals();
        boolean g2 = goal2.fulfilledGoals();
        if (g1 && g2) {
            return "";
        }
        if (g1 && !g2) {
            return goal2.getGoal();
        }
        if (!g1 && g2) {
            return goal1.getGoal();
        }
        return "(" + goal1.getGoal() + " AND " + goal2.getGoal() + ")";
    }

    public Goals getGoal1() {
        return goal1;
    }

    public Goals getGoal2() {
        return goal2;
    }

    public boolean fulfilledGoals() {
        return goal1.fulfilledGoals() & goal2.fulfilledGoals();
    }


}
