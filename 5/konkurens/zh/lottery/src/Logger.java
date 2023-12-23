import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Logger implements Runnable {
    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>(10);

    public void log(String log) {
        try {
            logQueue.put(log);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(logQueue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Logger is terminating... Messages in queue: " + logQueue.size());
                break;
            }
        }
    }
}
