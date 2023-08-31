public class LineMain {
    public static void main(String[] args) {
        Line l1 = new Line(1, -1, 0);

        Point origin = new Point(0, 0);
        Point p1 = new Point(5, 3);

        System.out.println("Contains:");
        System.out.println(l1.contains(origin));
        System.out.println(l1.contains(p1));

        Line l2 = new Line(1, 1, 0);
        Line l3 = new Line(-1, -1, 0);
        Line l4 = new Line(1, -1, 5);
        Line l5 = new Line(2, 3, 9);

        System.out.println("l1:");
        System.out.println(l1.isParellelWith(l1)); // önmagával nem
        System.out.println(l1.isParellelWith(l2)); // nem
        System.out.println(l1.isParellelWith(l3)); // nem
        System.out.println(l1.isParellelWith(l4)); // igen
        System.out.println(l1.isParellelWith(l5)); // nem

        System.out.println();

        System.out.println(l1.isOrthogonalTo(l1)); // önmaga
        System.out.println(l1.isOrthogonalTo(l2)); // igen
        System.out.println(l1.isOrthogonalTo(l3)); // igen
        System.out.println(l1.isOrthogonalTo(l4)); // nem
        System.out.println(l1.isOrthogonalTo(l5)); // nem

        System.out.println("l2:");
        System.out.println(l2.isParellelWith(l1)); // nem
        System.out.println(l2.isParellelWith(l2)); // önmaga
        System.out.println(l2.isParellelWith(l3)); // önmaga
        System.out.println(l2.isParellelWith(l4)); // nem
        System.out.println(l2.isParellelWith(l5)); // nem

        System.out.println();

        System.out.println(l2.isOrthogonalTo(l1)); // igen
        System.out.println(l2.isOrthogonalTo(l2)); // önmaga
        System.out.println(l2.isOrthogonalTo(l3)); // önmaga
        System.out.println(l2.isOrthogonalTo(l4)); // igen
        System.out.println(l2.isOrthogonalTo(l5)); // nem
    }
}
