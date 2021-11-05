package dungeonmania;

public class GoalLeaf implements Goals {
    private String goalType;
    private boolean goalFulfilled = false;

    public GoalLeaf(String goalType) {
        this.goalType = goalType;
    }
    /**
     * Returns goaltype with colon for frontend image use.
     */
    public String getGoal() {
        return ":" + goalType;
    }

    public void setFulfilled(boolean goalFulfilled) {
        this.goalFulfilled = goalFulfilled;
    }

    public boolean fulfilledGoals() {
        return goalFulfilled;
    }
}
