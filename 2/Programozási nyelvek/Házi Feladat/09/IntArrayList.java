import java.util.ArrayList;

public class IntArrayList {
    /*
    milyen előnyei vannak a (b) megoldásnak az (a) megoldással szemben?
    - kevesebb adattag
    - kevesebb hibát okozhat 
    - metódusok definíciója jelentősen egyszerűbb
    */
    private ArrayList<Integer> data;

    public IntArrayList() {
        this.data = new ArrayList<>();
    }

    public IntArrayList(int[] array) {
        this.data = new ArrayList<>();
        for (int item : array) {
            this.add(item);
        }
    }

    public void add(int num) {
        this.data.add(num);
    }

    public void concat(IntArrayList intArrayList) {
        this.data.addAll(intArrayList.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.data.toString().substring(1, this.data.toString().length()-1));
        return sb.toString();
    }

    public void removeItemsGreaterThan(int limit) {
        this.data.removeIf(num -> num > limit);
    }

    public ArrayList<Integer> getData() {
        ArrayList<Integer> result = new ArrayList<>(this.data);
        return result;
    }
}
