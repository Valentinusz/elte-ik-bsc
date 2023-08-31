import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import shape.InvalidFormatException;
import shape.Shape;
import shape.UnknownShapeTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * JUnit 4.12 test environment for the program.
 */
public class BBRTest {
    Scanner scanner;

    /**
     * Test case. File does not exist.
     */
    @Test (expected = FileNotFoundException.class)
    public void noFile() throws FileNotFoundException {
        scanner = new Scanner(new File("shapesN.txt"));
    }

    /**
     * Test case. The file is empty.
     */
    @Test (expected = InvalidFormatException.class)
    public void emptyFile() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes00.txt");
    }

    /**
     * Test case. File contains data in an invalid format.
     */
    @Test (expected = InvalidFormatException.class)
    public void wrongFormat1() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapesW1.txt");
    }

    /**
     * Test case. File contains data in an invalid format.
     */
    @Test (expected = InvalidFormatException.class)
    public void wrongFormat2() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapesW2.txt");
    }

    /**
     * Test case. File contains an unknown shape identifier.
     */
    @Test (expected = UnknownShapeTypeException.class)
    public void unknownShape() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapesU.txt");
    }

    /**
     * Test case. File contains data that trigger an {@code IllegalArgumentException} from a constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void illegalConstructorArgument() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapesI.txt");
    }

    /**
     * Test case. File contains zero shapes.
     */
    @Test
    public void zeroShape() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes0.txt");
        Shape max = shapes.getShapeWithBABR();
        assertNull(max);
    }

    /**
     * Test case. File contains one shape.
     */
    @Test
    public void oneShape() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes1.txt");
        Shape expected = shapes.getNthShape(0);
        Shape max = shapes.getShapeWithBABR();
        assertEquals(expected, max);
    }

    /**
     * Test case. File contains multiple shapes.
     */
    @Test
    public void multipleShapes() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes2.txt");
        Shape expected = shapes.getNthShape(1);
        Shape max = shapes.getShapeWithBABR();
        assertEquals(expected, max);
    }

    /**
     * Test case. File contains multiple shapes and extra data in some lines, that is successfully ignored while parsing.
     */
    @Test
    public void extraData() throws UnknownShapeTypeException, FileNotFoundException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes2.txt");
        Shape expected = shapes.getNthShape(1);
        Shape max = shapes.getShapeWithBABR();
        assertEquals(expected, max);
    }

    /**
     * Test case. File contains multiple shapes, with similar specifications.
     */
    @Test
    public void multipleShapesWithSimilarSizes() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes3.txt");
        Shape expected = shapes.getNthShape(2);
        Shape max = shapes.getShapeWithBABR();
        assertEquals(expected, max);
    }

    /**
     * Test case. File contains multiple shapes with same area boundingRectangles.
     */
    @Test
    public void sameAreaBoundingRectangle() throws FileNotFoundException, UnknownShapeTypeException, InvalidFormatException {
        Shapes shapes = new Shapes();
        shapes.readShapes("shapes4.txt");
        Shape expected = shapes.getNthShape(0);
        Shape max = shapes.getShapeWithBABR();
        assertEquals(expected, max);
    }
}
