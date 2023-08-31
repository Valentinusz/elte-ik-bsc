import shape.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Facade class for handling multiple shapes.
 */
public class Shapes {
    /**
     * ArrayList of Shape objects
     */
    private ArrayList<Shape> shapes;

    /**
     * Constructor that sets shapes to a new {@code ArrayList<Shape>}.
     */
    public Shapes() {
        shapes = new ArrayList<>();
    }

    /**
     * Creates and adds Shapes to {@code shapes} by reading the specified file.
     * @param fileName name of the file to read shapes from
     * @throws IllegalArgumentException if file contains an unrecognized shape identifier
     * @throws FileNotFoundException if file specified in {@code fileName} is not found
     * @throws InvalidFormatException if data in the file is not in the correct format
     * @throws UnknownShapeTypeException if an unknown shape identifier is encountered during parsing
     */
    public void readShapes(String fileName) throws IllegalArgumentException, FileNotFoundException, InvalidFormatException, UnknownShapeTypeException {
        int row = 1;
        int col = 1;

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int shapeCount = scanner.nextInt();
            if (scanner.hasNext()) scanner.nextLine();

            for (int i = 0; i < shapeCount; i++) {
                // checks if scanner actually has more data to read
                if (scanner.hasNext()) {

                    Scanner lineScanner = new Scanner(scanner.nextLine());

                    // char denoting the type of the shape
                    char shapeID = lineScanner.next().charAt(0);

                    col++;

                    // x of center point
                    int x = lineScanner.nextInt();
                    col++;

                    // y of center point
                    int y = lineScanner.nextInt();
                    col++;

                    // radius or side length
                    int measure = lineScanner.nextInt();
                    col++;

                    // c - circle
                    // s - square
                    // t - equilateral triangle
                    // h - regular hexagon
                    switch (shapeID) {
                        case 'c' -> this.shapes.add(new Circle(x, y, measure));
                        case 's' -> this.shapes.add(new Square(x, y, measure));
                        case 't' -> this.shapes.add(new EquilateralTriangle(x, y, measure));
                        case 'h' -> this.shapes.add(new RegularHexagon(x, y, measure));
                        default -> throw new UnknownShapeTypeException("Unknown shape identifier in row: " + row);
                    }
                    col = 1;
                    row++;
                }
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            throw new InvalidFormatException("Invalid format in row: " + row + ", column: " + col);
        }
    }

    /**
     * Selects shape with the biggest area bounding rectangle from the specified.
     * @return Reference to the shape with the biggest area bounding rectangle. if {@code shapes} is empty null is returned.
     */
    public Shape getShapeWithBABR() {
        return this.shapes.stream().max(Comparator.comparing(Shape::boundingArea)).orElse(null);
    }

    /**
     * Utility method for testing.
     * @param n index
     * @return nth element of shapes
     */
    public Shape getNthShape(int n) {
        return this.shapes.get(n);
    }
}
