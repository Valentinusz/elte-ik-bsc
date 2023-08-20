public class Stream {
    private int maxStringLength;
    private int stringNumber;
    private Logger logger;

    public Stream(int maxStringLength, int stringNumber, Logger logger) {
        if (maxStringLength < 0 || stringNumber < 0 || logger == null) {
            throw new IllegalArgumentException();
        }
        this.maxStringLength = maxStringLength;
        this.stringNumber = stringNumber;
        this.logger = logger;
    }

    public void startStreaming() {
        for(int i = 0; i < this.stringNumber; ++i) {
            logger.log("abc");
        }
    }
}