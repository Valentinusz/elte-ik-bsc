package shape;

/**
 * Thrown to indicate that data in a file is in an illegal format.
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructs an InvalidFormatException with no detail message.
     */
    public InvalidFormatException() {}

    /**
     * Constructs an InvalidFormatException with the specified detail message.
     * @param message the detail message
     */
    public InvalidFormatException(String message) {
        super(message);
    }
}
