import shape.*;
import java.io.FileNotFoundException;

/**
 * Main class of the program.
 * @author Valentinusz - Boda Bálint - KDHPNI
 */
public class Main {
    /**
     * Main method of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                Shapes shapes = new Shapes();
                shapes.readShapes(args[0]);
                Shape shapeWithBBR = shapes.getShapeWithBABR();
                if (shapeWithBBR == null) {
                    System.out.println("No data has been read.");
                } else {
                    System.out.println(shapes.getShapeWithBABR());
                }

            } catch (InvalidFormatException | UnknownShapeTypeException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
                System.exit(-1);
            }
        } else {
            System.err.println("Please specify file to read shapes from as command line argument.");
        }
    }
}
