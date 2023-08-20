import java.util.LinkedList;

public class IntLinkedList {
    /*
    milyen előnyei vannak a (b) megoldásnak az (a) megoldással szemben?
    - kevesebb adattag
    - kevesebb hibát okozhat 
    - metódusok definíciója jelentősen egyszerűbb
    */
    private LinkedList<Integer> data;

    public IntLinkedList() {
        this.data = new LinkedList<>();
    }

    public IntLinkedList(int[] array) {
        this.data = new LinkedList<>();
        for (int item : array) {
            this.add(item);
        }
    }

    public void add(int num) {
        this.data.add(num);
    }

    public void concat(IntLinkedList intLinkedList) {
        this.data.addAll(intLinkedList.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.data.toString().substring(1, this.data.toString().length()-1));
        return sb.toString();
    }

    public void removeItemsGreaterThan(int limit) {
        for (Integer num : this.data) {
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
