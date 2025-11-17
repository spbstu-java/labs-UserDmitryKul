package lab1;

public class DragonStrategy implements IMovementStrategy {
    public void move(Hero hero, int x, int y) {
        hero.setXPos(x);
        hero.setYPos(y);
    }
    public String getName() { return "На драконе"; }
}