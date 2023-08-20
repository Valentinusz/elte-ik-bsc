public class CircleMain {
    public static void main(String[] args) {
        Circle c1 = new Circle(0, 0, 1);
        System.out.println(c1.getArea());

        c1.enlarge(1.5);
        System.out.println(c1.radius);
        System.out.println(c1.getArea());
    }
}