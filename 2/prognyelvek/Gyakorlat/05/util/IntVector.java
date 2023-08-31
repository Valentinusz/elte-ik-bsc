package util;

public class IntVector {
    int[] numbers;

    public IntVector(int[] numbers) {
        this.numbers = new int[numbers.length];
        for(int i = 0; i < numbers.length; ++i) {
            this.numbers[i] = numbers[i];
        }
    }

    public void add(int n) {
        for (int i = 0; i < this.numbers.length-1; i++)
            this.numbers[i] += n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < this.numbers.length; ++i) {
            sb.append(this.numbers[i]);
            if (i != this.numbers.length -1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}