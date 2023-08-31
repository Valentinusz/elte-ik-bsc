public class Perfect {
    public static void main(String[] args) {
        if (args.length > 0) {
            int number = Integer.parseInt(args[0]);
            int sum = 0;

            for (int i = 1; i < number; ++i) {
                if (number % i == 0) {
                    sum += i;
                }
            }

            if (sum == number) {
                System.out.println("Perfect.");
            } else {
                System.out.println("Not perfect.");
            }
        }
    }
}