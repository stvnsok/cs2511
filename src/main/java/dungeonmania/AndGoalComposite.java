package dungeonmania;

import java.util.List;
import java.util.stream.Collectors;

public class AndGoalComposite implements Goals{
    private List<Goals> children;

    public AndGoalComposite(List<Goals> children) {
        this.children = children;
    }

    public String getGoal() {
        List<Goals> unfilfilled = children.stream().filter(g -> !g.fulfilledGoals()).collect(Collectors.toList());
        int size = unfilfilled.size();
        String returnString = "";
        if (size > 1) {
            returnString = "( " + unfilfilled.get(0).getGoal();
            for (int i = 1; i < size; i++) {
                returnString += " AND " + unfilfilled.get(i).getGoal();
            }
            returnString += " )";
            
        } else if (size == 1) {
            returnString = unfilfilled.get(0).getGoal();
        }
        return returnString;
    }

    public boolean fulfilledGoals() {
        for (Goals goal : children) {
            if (!goal.fulfilledGoals()) {
                return false;
            }
        }
        return true;
    }
}
