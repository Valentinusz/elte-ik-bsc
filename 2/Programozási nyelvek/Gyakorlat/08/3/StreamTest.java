public class StreamTest {
    public static void main(String[] args) {
        Logger logger = new Logger();
        Stream stream1 = new Stream(3, 5, logger);
        stream1.startStreaming();

        ConsoleLogger consoleLogger = new ConsoleLogger();
        Stream stream2 = new Stream(3, 5, consoleLogger);
        stream2.startStreaming();

        CipherLogger cipherLogger = new CipherLogger();
        Stream stream3 = new Stream(3, 5, cipherLogger);
        stream3.startStreaming();

        FileLogger fileLogger = new FileLogger("teszt.txt");
        Stream stream4 = new Stream(3, 5, fileLogger);
        stream4.startStreaming();
        fileLogger.finalize();
    } 
}
