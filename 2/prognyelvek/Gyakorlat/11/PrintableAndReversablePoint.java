public class PrintableAndReversablePoint extends ReversiblePoint implements Printable {
    PrintableAndReversablePoint(double x, double y) {
        super(x, y);
    }


    @Override
    public void print() {
        System.out.println("jelenlegi: (" + this.x + "," + this.y + ") előző: (" + this.previousX + "," + this.previousY + ")");
    }

    @Override
    public void reverse() {
        this.x = this.previousX;
        this.y = this.previousY;
    }
}
