import javax.xml.crypto.Data;

public record DataMover2Result(int count, int data, int forwarded) {
    public DataMover2Result() {
        this(0, 0, 0);
    }

    public DataMover2Result incrementCount() {
        return new DataMover2Result(count + 1, data, forwarded);
    }

    public DataMover2Result addToData(int x) {
        return new DataMover2Result(count, data + x, forwarded);
    }

    public DataMover2Result incrementForwarded() {
        return new DataMover2Result(count, data, forwarded + 1);
    }
}
