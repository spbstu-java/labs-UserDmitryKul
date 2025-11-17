package lab1;

public class Hero {

    private IMovementStrategy _movementStrategy;
    private int _xPos;
    private int _yPos;

    public Hero(int xPos, int yPos, IMovementStrategy moveStrategy){
        setMoveStrategy(moveStrategy);
        _xPos = xPos;
        _yPos = yPos;
    }

    public void setMoveStrategy(IMovementStrategy moveStrategy){
        if (moveStrategy == null)
            throw new IllegalArgumentException("moveStrategy is null");
        _movementStrategy = moveStrategy;
    }

    public String getMoveStrategy(){
        return _movementStrategy.getName();
    }

    public void move(int x, int y){
        _movementStrategy.move(this, x, y);
    }

    public int getXPos() {
        return _xPos;
    }

    public void setXPos(int xPos) {
        this._xPos = xPos;
    }

    public int getYPos() {
        return _yPos;
    }

    public void setYPos(int yPos) {
        this._yPos = yPos;
    }
}