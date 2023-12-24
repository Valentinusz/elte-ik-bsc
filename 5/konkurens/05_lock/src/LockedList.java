import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class LockedList<T> {
    private final List<T> values = new ArrayList<>();

    private final Lock lock = new ReentrantLock(true);

    public LockedList(List<T> list) {
        values.addAll(list);
    }

    public boolean add(T item) {
        try {
            try {
                lock.lockInterruptibly();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return values.add(item);
    }

    public void forEach(Consumer<T> consumer) {
        try {
            try {
                lock.lockInterruptibly();

                for (T item : this.values) {
                    consumer.accept(item);
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
