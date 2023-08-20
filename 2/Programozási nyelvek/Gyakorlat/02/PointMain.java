public class PointMain {
    public static void main(String[] args) {
        Point p1 = new Point(2,5);
        System.out.println("(" + p1.x + "," + p1.y + ")");

        p1.move(-10,-5);
        System.out.println("(" + p1.x + "," + p1.y + ")");

        p1.mirror(0,0);
        System.out.println("(" + p1.x + ", " + p1.y + ")");


        Point p2 = new Point(3,7);
        System.out.println("(" + p2.x + ", " + p2.y + ")");

        p1.mirror2(p2);
        System.out.println("(" + p1.x + ", " + p1.y + ")");

        System.out.println(p1.distance(p2));


        Point p3 = new Point(0,5);
        System.out.println("(" + p3.x + "," + p3.y + ")");


        Point p4 = new Point(0,0);
        System.out.println("(" + p4.x + "," + p4.y + ")");
        System.out.println(p3.distance(p4));

    }
}