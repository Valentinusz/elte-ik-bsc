import java.util.HashSet;

public class CheckedSet<T> {
    private HashSet<T> hashSet;
    public CheckedSet() {
        this.hashSet = new HashSet<>();
    }

    public int size() {
        return this.hashSet.size();
    }

    public void add(T element) throws AlreadyContainedException {
        if (this.hashSet.contains(element)) {
            throw new AlreadyContainedException();
        }
        this.hashSet.add(element);
    }

    public boolean contains(T element) {
        return this.hashSet.contains(element);
    }


}
