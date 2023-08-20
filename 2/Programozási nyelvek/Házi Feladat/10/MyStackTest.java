import static org.junit.Assert.assertEquals;

import java.beans.Transient;
import java.util.EmptyStackException;

import org.junit.Test;

public class MyStackTest {

    @Test
    public void lastInFirstOut() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(5);
        assertEquals(5,stack.pop().intValue());
    }

    @Test
    public void empty() {
        MyStack<Integer> stack = new MyStack<>();
        assertEquals(true,stack.empty());
    }

    @Test
    public void notEmpty() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(3);
        assertEquals(false,stack.empty());
    }

    @Test (expected = EmptyStackException.class)
    public void emptyPop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.pop();
    }

    @Test
    public void size0() {
        MyStack<Integer> stack = new MyStack<>();
        assertEquals(0,stack.size());
    }

    @Test
    public void size1() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(4);
        assertEquals(1,stack.size());
    }

    @Test
    public void emptyAfterPop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(4);
        stack.push(3);
        stack.pop();
        stack.pop();
        assertEquals(true,stack.empty());
    }

    @Test
    public void unchangedAfterPeek() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(4);
        stack.peek();
        assertEquals(4,stack.pop().intValue());
    }

}
