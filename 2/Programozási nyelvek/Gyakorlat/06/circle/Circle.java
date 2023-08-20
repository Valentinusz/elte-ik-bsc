package circle;

import circle.utils.Point;

import java.io.*;
import java.util.Scanner;

class Circle {
    private double x = 0;
    private double y = 0;
    private double radius;

    Circle(double x, double y, double radius) {
        if (radius > 0) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius;
    }

    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Circle readFromFile(String filename) {
        try {
            Scanner scanner = new Scanner (new File (filename));
            return new Circle (scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());

        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate file.");
            return new Circle (0,0,1);
        }
    }

    public void saveToFile(String file) {
        Writer writer = new Writer (new File (file));
    }
}