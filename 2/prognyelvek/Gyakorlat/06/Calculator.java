public class Calculator {
    public static void main(String[] args) {
        try {
            if (args.length == 3) {
                double operandA = Double.parseDouble(args[0]);
                double operandB = Double.parseDouble(args[2]);
                try {
                    switch (args[1]) {
                        case "+":
                        System.out.println(operandA + operandB);
                        break;
    
                        case "-":
                        System.out.println(operandA - operandB);
                        break;
    
                        case "*":
                        System.out.println(operandA * operandB);
                        break;
    
                        case "/":
                        try {
                            System.out.println(operandA / operandB);
                        } catch (ArithmeticException e) {
                            System.out.println("Attempted to divide by Zero.");
                        }

                        break;
    
                        default:
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Unsupported operation.");
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please supply 3 arguments.");
        }

    }
}
