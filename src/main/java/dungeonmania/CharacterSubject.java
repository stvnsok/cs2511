package dungeonmania;

public interface CharacterSubject {
    public void attach(CharacterObserver observer);
    
    public void detach(CharacterObserver observer);

    public void notifyObservers();
}
