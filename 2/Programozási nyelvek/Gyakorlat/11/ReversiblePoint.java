public class ReversiblePoint extends Point implements Reversable {
    protected double previousX;
    protected double previousY;


    ReversiblePoint(double x, double y) {
        super(x, y);
        this.previousX = this.x;
        this.previousY = this.y;
    }
    
    @Override
    public void reverse() {
        this.x = this.previousX;
        this.y = this.previousY;
    }

    @Override
    public void setX(double x) {
        this.previousX = this.x;
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.previousY = this.y;
        this.x = y;
    }
}
