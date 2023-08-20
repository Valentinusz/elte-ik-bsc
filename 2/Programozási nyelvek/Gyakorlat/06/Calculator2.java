public class Calculator2 {
    public static void main(String[] args) {
        try {
            if (args.length == 3) {
                double operandA = Double.parseDouble(args[0]);
                double operandB = Double.parseDouble(args[2]);

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
                    if (operandB == 0) {
                        throw new ArithmeticException("Attempted division by Zero.");   
                    } else {
                        System.out.println(operandA / operandB);
                    }
                    break;
    
                    default:
                    throw new IllegalArgumentException("Unsupported operator.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid number of arguments.");
                }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
