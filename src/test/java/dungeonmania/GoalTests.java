package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
public class GoalTests {
    @Test
    public void leafGoalTest() {
        GoalLeaf leaf = new GoalLeaf("enemies");
        leaf.setFulfilled(true);
        assertEquals(":enemies", leaf.getGoal());
        assertTrue(leaf.fulfilledGoals());
    }

    @Test
    public void AndGoalTest() {
        GoalLeaf leaf1 = new GoalLeaf("enemies");
        leaf1.setFulfilled(true);
        GoalLeaf leaf2 = new GoalLeaf("treasure");
        leaf2.setFulfilled(false);
        List<Goals> goals = new ArrayList<>(Arrays.asList(leaf1, leaf2));
        AndGoalComposite andGoal = new AndGoalComposite(goals);
        assertFalse(andGoal.fulfilledGoals());
        assertEquals(":treasure", andGoal.getGoal());
        leaf2.setFulfilled(true);
        assertTrue(andGoal.fulfilledGoals());
        assertEquals("", andGoal.getGoal());
        leaf1.setFulfilled(false);
        leaf2.setFulfilled(false);
        assertFalse(andGoal.fulfilledGoals());
        assertEquals("( :enemies AND :treasure )", andGoal.getGoal());
    }

    @Test
    public void OrGoalTest() {
        GoalLeaf leaf1 = new GoalLeaf("enemies");
        leaf1.setFulfilled(true);
        GoalLeaf leaf2 = new GoalLeaf("treasure");
        leaf2.setFulfilled(false);
        List<Goals> goals = new ArrayList<>(Arrays.asList(leaf1, leaf2));
        OrGoalComposite OrGoal = new OrGoalComposite(goals);
        assertTrue(OrGoal.fulfilledGoals());
        assertEquals("", OrGoal.getGoal());
        leaf1.setFulfilled(false);
        assertFalse(OrGoal.fulfilledGoals());
        assertEquals("( :enemies OR :treasure )", OrGoal.getGoal());
    }

    @Test
    public void factoryTest() {
        JSONObject object = new JSONObject();
        object.put("goal", "enemies");
        Goals leaf = GoalFactory.createGoals(object);
        assertEquals(":enemies", leaf.getGoal());

        JSONObject object2 = new JSONObject();
        object2.put("goal", "treasure");

        JSONArray subgoals = new JSONArray();
        subgoals.put(object);
        subgoals.put(object2);
        JSONObject andGoal = new JSONObject();
        andGoal.put("goal", "AND");
        andGoal.put("subgoals", subgoals);
        Goals andComp = GoalFactory.createGoals(andGoal);
        assertEquals("( :enemies AND :treasure )", andComp.getGoal());

        JSONArray subgoals2 = new JSONArray();
        subgoals2.put(object);
        subgoals2.put(object2);
        JSONObject OrGoal = new JSONObject();
        OrGoal.put("goal", "OR");
        OrGoal.put("subgoals", subgoals2);
        Goals orComp = GoalFactory.createGoals(OrGoal);
        assertEquals("( :enemies OR :treasure )", orComp.getGoal());
    }

    @Test
    public void complexGoal() {
        GoalLeaf goal1 = new GoalLeaf("enemies");
        GoalLeaf goal2 = new GoalLeaf("treasure");
        GoalLeaf goal3 = new GoalLeaf("exit");
        List<Goals> subGoals = new ArrayList<>();
        subGoals.add(goal1);
        subGoals.add(goal2);
        OrGoalComposite orGoals = new OrGoalComposite(subGoals);
        List<Goals> superGoals = new ArrayList<>();
        superGoals.add(goal3);
        superGoals.add(orGoals);
        AndGoalComposite andGoals = new AndGoalComposite(superGoals);
        assertEquals("( :exit AND ( :enemies OR :treasure ) )", andGoals.getGoal());
        assertFalse(andGoals.fulfilledGoals());
        goal1.setFulfilled(true);
        assertFalse(andGoals.fulfilledGoals());
        goal3.setFulfilled(true);
        assertTrue(andGoals.fulfilledGoals());
    }
}
