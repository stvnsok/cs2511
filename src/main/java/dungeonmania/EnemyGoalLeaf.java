package dungeonmania;

import java.util.List;

public class EnemyGoalLeaf extends GoalLeaf {
    public EnemyGoalLeaf(Game game) {
        setGame(game);
        setGoalType(":enemies");
    }

    @Override
    public boolean fulfilledGoals() {
        Game game = getGame();
        List<Entity> entities = game.getEntities();
        return entities.stream().noneMatch(e -> e instanceof Enemies);
    }
}
