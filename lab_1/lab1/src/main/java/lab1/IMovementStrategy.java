package lab1;

public interface IMovementStrategy {
    void move(Hero hero, int x, int y);
    String getName();
}