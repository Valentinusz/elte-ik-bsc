package shape;

/**
 * Thrown to indicate that a file contains an identifier with no Shape associated.
 */
public class UnknownShapeTypeException extends Exception {
    /**
     * Constructs an UnknownShapeTypeException with no detail message.
     */
    public UnknownShapeTypeException() {}

    /**
     * Constructs an UnknownShapeTypeException with the specified detail message.
     * @param message the detail message
     */
    public UnknownShapeTypeException(String message) {
        super(message);
    }
}
