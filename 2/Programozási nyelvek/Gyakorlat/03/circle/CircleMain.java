package circle;

import circle.utils.Point;


class CircleMain {
    public static void main(String[] args) {
        Circle c1 = new Circle(0, 0, 1);
        System.out.println("A kör területe:" + c1.getArea());
        
        c1.setX(5);
        c1.setY(2);
        c1.setRadius(10);

        System.out.println("A kör területe:" + c1.getArea());

        Point p1 = c1.getCenter();
        System.out.println("("+ p1.getX() + "," + p1.getY() + ")");
    }
}