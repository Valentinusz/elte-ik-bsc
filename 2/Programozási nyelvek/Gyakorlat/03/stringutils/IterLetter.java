package stringutils;

public class IterLetter {

    private String str;
    private int current = 0;

    public IterLetter(String str) {
        if (str != null) {
            this.str = str;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean hasNext() {
        return current < this.str.length();
    }

    public void printNext() {
        if (hasNext()) {
            System.out.println(this.str.charAt(this.current));
            this.current++;
        }
    }

    public void restart() {
        this.current = 0;
    }
}
