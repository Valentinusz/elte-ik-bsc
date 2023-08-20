import java.util.LinkedList;

public class IntLinkedList {
    protected LinkedList<Integer> data;

    public IntLinkedList() {
        this.data = new LinkedList<>();
    }

    public IntLinkedList(int[] array) {
    }

    public void add(int num) {
        this.data.add(num);
    }

    public void concat(IntLinkedList intLinkedList) {
        for (int num : intLinkedList.data) {
            this.add(num);
        }
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    public void removeItemsGreaterThan(int limit) {
        for (int num : this.data) {
            if (num > limit) {
                this.data.remove(num);
            }
        }
    }

    public LinkedList<Integer> getData() {
        LinkedList<Integer> result = new LinkedList<>(this.data);
        return result;
    }
}
