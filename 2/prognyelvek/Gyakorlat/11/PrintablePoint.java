public class PrintablePoint extends Point implements Printable {
    
    PrintablePoint(double x, double y) {
        super(x, y);
    }
    
    @Override
    public void print() {
        System.out.println("(" + this.x + "," + this.y + ")");
    }
}
