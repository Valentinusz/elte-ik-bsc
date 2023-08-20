import java.util.HashMap;

public class MultiSet {
    private HashMap<String, Integer> data;

    public MultiSet() {
        this.data = new HashMap<>();
    }

    public MultiSet(HashMap<String, Integer> data) {
        this.data = new HashMap<>(data);
    }

    public HashMap<String, Integer> getData() {
        return new HashMap<>(this.data);
    }

    public void put(String str) {
        if (data.containsKey(str)) {
            data.replace(str, data.get(str)+1);
        } else {
            data.put(str, 1);
        }
    }

    public MultiSet intersect(MultiSet multiSet) {
        MultiSet result = new MultiSet();

        for (String key : this.data.keySet()) {
            if (multiSet.data.containsKey(key)) {
                result.data.put(key, Integer.min(this.data.get(key), multiSet.data.get(key)));
            }
        }

        return result;
    }


    public static String wordStat(String str) {
        MultiSet stat = new MultiSet();
        for (int i=0; i < str.length(); ++i) {
            Character c = str.charAt(i);
            stat.put(String.valueOf(c));
        }
        StringBuilder sb = new StringBuilder();
        for (String key : stat.data.keySet()) {
            sb.append(key);
            sb.append("(");
            sb.append(stat.data.get(key));
            sb.append(")");
        }

        return sb.toString();
    }
}
