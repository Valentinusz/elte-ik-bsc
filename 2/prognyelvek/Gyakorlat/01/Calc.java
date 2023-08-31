public class Calc {
    public static void main(String[] args) {
        if (args.length > 1) {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            System.out.println("Sum: " + (x + y));
            System.out.println("Sub: " + (x - y));
            System.out.println("Mul: " + (x * y));
            if (y == 0) {
                System.out.println("Division by Zero");
            } else {
                System.out.println("Div: " + (x / y));
            }
        }
    }
}
