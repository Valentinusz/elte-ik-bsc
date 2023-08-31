package cards;

public class EmptyDeckException extends Exception {
    public EmptyDeckException() {}

    public EmptyDeckException(String mString) {
        super(mString);
    }
}
