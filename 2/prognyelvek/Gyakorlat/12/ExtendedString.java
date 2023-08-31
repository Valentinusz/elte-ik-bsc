import java.util.Iterator;
import java.util.ArrayList;

public class ExtendedString implements Comparable<ExtendedString>, Iterable<Character> {
    private String string;

    public ExtendedString(String string) {
        if (string != null) {
            this.string = string;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(ExtendedString that) {
        if (this.string.length() > that.string.length()) {
            return 1;
        }

        if (this.string.length() == that.string.length()) {
            return 0;
        }

        return -1;
    }

    @Override
    public Iterator<Character> iterator() {
        ArrayList<Character> it = new ArrayList<>();
        for(int i = 0; i < this.string.length(); ++i) {
            it.add(this.string.charAt(i));
        }
        return it.iterator();
    }
}
