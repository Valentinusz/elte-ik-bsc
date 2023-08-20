import java.util.HashMap;

public class Bag<T> {
    private HashMap<T, Integer> map = new HashMap<>();

    public void add(T elem) {
        if(this.map.containsKey(elem)) {
            this.map.put(elem, this.map.get(elem)+1);
        } else {
            this.map.put(elem, 1);
        }
    }

    public Integer countOf(T elem) {
        if (this.map.containsKey(elem)) {
            return this.map.get(elem);
        }
        return Integer.valueOf(0);
    }

    public void remove(T elem) throws NotInBagException {
        if (!this.map.containsKey(elem)) {
            throw new NotInBagException();
        }

        Integer count = this.map.get(elem);
        if (count == 1) {
            this.map.remove(elem);
        } else {
            this.map.replace(elem, - 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T key : this.map.keySet()) {
            sb.append(key);
            sb.append(": ");
            sb.append(this.map.get(key));
            sb.append("\n");
        }
        return sb.toString();
    }
}