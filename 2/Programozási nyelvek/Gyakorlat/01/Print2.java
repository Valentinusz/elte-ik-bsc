public class Print2 {
    public static void main(String[] args) {
        System.out.print("Start: ");
        int start = Integer.parseInt(System.console().readLine());
        System.out.print("End: ");
        int end = Integer.parseInt(System.console().readLine());

        for (int i=start; i <= end; ++i) {
            System.out.println(i);
        }

    }
}
