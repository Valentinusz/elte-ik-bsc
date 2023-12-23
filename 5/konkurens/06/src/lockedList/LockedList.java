package lockedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedList<T> {
    private List<T> list;
    private Lock lock;

    public LockedList() {
        this.list = new ArrayList<>();
        this.lock = new ReentrantLock(true);
    }

    public LockedList(List<T> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list); 
    }
}
