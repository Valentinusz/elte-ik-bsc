public class Perfect2 {
    public static void main(String[] args) {
        if (args.length > 0) {
            int number = Integer.parseInt(args[0]);
            int count = 0;

            for (int i = 1; i <= number; ++i) {
                int sum = 0;

                for (int j = 1; j < i; ++j) {
                    if (i % j == 0) {
                        sum += j;
                    }
                }

                if (sum == i) {
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("There are no perfect numbers in the interval.");
            } else {
                System.out.println("Count of perfect numbers: " + count);
            }

        }
    }
}
