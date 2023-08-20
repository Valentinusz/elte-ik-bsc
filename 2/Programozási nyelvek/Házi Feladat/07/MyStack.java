import java.util.NoSuchElementException;

public class MyStack {
    private int[] data;
    private int currentSize;

    public MyStack(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.data = new int[size];
        this.currentSize = 0;
    }

    public void push(int element) {
        if (this.currentSize < this.data.length) {
            this.data[this.currentSize] = element;
            this.currentSize++;
        } else {
            throw new IllegalStateException("Full stack.");
        }
    }

    public boolean empty() {
        return this.currentSize == 0;
    }

    public int pop() {
        if (this.empty()) {
            throw new NoSuchElementException("Empty stack.");
        } else {
            return this.data[--currentSize];
        }
    }



    public int size() {
        return this.currentSize;
    }
}
