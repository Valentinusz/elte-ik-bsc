package mass;

import mass.utils.Point;

class Main {
    public static void main(String[] args) {
        Point[] points = new Point[3];

        for (int i=0; i < 3; ++i) {
            double x = Double.parseDouble(System.console().readLine());
            double y = Double.parseDouble(System.console().readLine());
            points[i] = new Point(x,y);
        }

        Point s = Point.getS(points);

        System.out.println(s.toString());

    }
}