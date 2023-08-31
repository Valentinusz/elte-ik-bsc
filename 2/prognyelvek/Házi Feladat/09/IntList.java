public class IntList {
    private int[] data;
    private int currentSize;
    private int maxSize;

    public IntList(int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
            this.currentSize = 0;
            this.data = new int[maxSize];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void add(int num) {
        if (this.currentSize >= this.maxSize) {
            throw new IllegalStateException();
        }
        this.data[currentSize] = num;
        this.currentSize++;
    }

    public void concat(IntList intList) {
        if (this.maxSize >= this.currentSize + intList.currentSize) {
            for(int i=0; i<intList.currentSize; ++i) {
                this.add(intList.data[i]);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<this.currentSize; ++i) {
            sb.append(this.data[i]);
            sb.append(", ");
        }
        if (sb.length() >= 2) {
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
    }

    public void removeItemsGreaterThan(int limit) {
        IntList result = new IntList(this.maxSize);
        for (int num : this.data) {
            if (num <= limit) {
                result.add(num);
            }
        }
        this.currentSize = result.currentSize;
        this.data = result.data;
    }
}
