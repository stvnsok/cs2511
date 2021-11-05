package dungeonmania;

import dungeonmania.util.Position;

public interface BoulderSubject {
    public void attach(BoulderObserver observer);

    public void detach(BoulderObserver observer);

    public void notifyObservers(Position oldPosition, Position newPosition);
}
