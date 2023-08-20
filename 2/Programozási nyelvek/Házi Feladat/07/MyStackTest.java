import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MyStackTest {

    @Test
    public void lastInFirstOut() {
        MyStack stack = new MyStack(5);
        stack.push(5);
        assertEquals(5,stack.pop());
    }

    @Test
    public void empty() {
        MyStack stack = new MyStack(5);
        assertEquals(true,stack.empty());
    }

    @Test
    public void notEmpty() {
        MyStack stack = new MyStack(5);
        stack.push(3);
        assertEquals(false,stack.empty());
    }

    @Test (expected = NoSuchElementException.class)
    public void emptyPop() {
        MyStack stack = new MyStack(5);
        stack.pop();
    }

    @Test
    public void size0() {
        MyStack stack = new MyStack(5);
        assertEquals(0,stack.size());
    }

    @Test
    public void size1() {
        MyStack stack = new MyStack(5);
        stack.push(4);
        assertEquals(1,stack.size());
    }

    @Test
    public void emptyAfterPop() {
        MyStack stack = new MyStack(5);
        stack.push(4);
        stack.push(3);
        stack.pop();
        stack.pop();
        assertEquals(true,stack.empty());
    }

}
