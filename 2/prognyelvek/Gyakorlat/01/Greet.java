public class Greet {
    public static void main(String[] args) {
        System.out.print("Enter name: ");
        String name = System.console().readLine();
        System.out.println("Hello " + name + "!");
    }
}