package dungeonmania;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalFactory {
    // Creating goal objects from JSON
    public static Goals createGoals(JSONObject object) {
        String goalType = object.getString("goal");
        
        switch (goalType) {
            case "AND":
                JSONArray goalArray1 = object.getJSONArray("subgoals");
                return new AndGoalComposite(createGoals(goalArray1.getJSONObject(0)), createGoals(goalArray1.getJSONObject(1)));
            
            case "OR":
                JSONArray goalArray2 = object.getJSONArray("subgoals");
                return new OrGoalComposite(createGoals(goalArray2.getJSONObject(0)), createGoals(goalArray2.getJSONObject(1)));
        
            default:
                return new GoalLeaf(goalType);
        }
    }
}
