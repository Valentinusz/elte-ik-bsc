public class Swap {
    public static <T> void swap(T[] arr, int i, int j) {
        if (i >= 0 && j >= 0) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
