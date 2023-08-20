import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack<T> {
    private ArrayList<T> data;

    public MyStack() {
        this.data = new ArrayList<>();
    }

    public void push(T element) {
        this.data.add(element);
    }

    public boolean empty() {
        return this.size() == 0;
    }

    public int size() {
        return this.data.size();
    }

    public T pop() {
        T last = this.peek();
        this.data.remove(this.size()-1);
        return last;
    }

    public T peek() {
        if (this.empty()) {
            throw new EmptyStackException();
        } else {
            return this.data.get(this.size()-1);
        }
    }
}