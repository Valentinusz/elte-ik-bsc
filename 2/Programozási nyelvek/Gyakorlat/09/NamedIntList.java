public class NamedIntList extends IntLinkedList {
    private String name;

    public NamedIntList(String name) {
        this.name = name;
    }

    public NamedIntList(String name, int[] data) {
        this.name = name;
        for (int num : data) {
            this.data.add(num);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(": ");
        sb.append(super.toString());
        return sb.toString();
    }
}
