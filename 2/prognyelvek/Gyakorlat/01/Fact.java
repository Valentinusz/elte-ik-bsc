public class Fact {
    public static void main(String[] args) {
        int n;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        } else {
            n= Integer.parseInt(System.console().readLine());
        }

        int product = 1;

        for (int i = 2; i <= n; ++i) {
            product *= i;
        }

        if (n >= 0) {
            System.out.println(n + "! = " + product);
        } else {
            System.out.println("Negative numbers do not have a factorial.");
        }


    }
}
