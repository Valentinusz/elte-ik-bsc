public class ComplexMain {
    public static void main(String[] args) {
        Complex c1 = new Complex(3, 4);
        System.out.println(c1.real + " + " + c1.imaginary + "i");
        System.out.println(c1.abs());

        Complex alpha = new Complex(3, 2);
        Complex beta = new Complex(1, 2);

        System.out.println(alpha.real + " + " + alpha.imaginary + "i");
        System.out.println(beta.real + " + " + beta.imaginary + "i");

        alpha.add(beta);
        System.out.println(alpha.real + " + " + alpha.imaginary + "i");

        alpha.sub(beta);
        System.out.println(alpha.real + " + " + alpha.imaginary + "i");
        System.out.println(beta.real + " + " + beta.imaginary + "i");

        alpha.mul(beta);
        System.out.println(alpha.real + " + " + alpha.imaginary + "i");

        System.out.println();
        Complex c2 = new Complex(3, 4);
        Complex c3 = new Complex(8, -2);

        System.out.println(c2.real + " + " + c2.imaginary + "i");
        System.out.println(c3.real + " + " + c3.imaginary + "i");

        c2.div(c3);

        System.out.println();
        System.out.println(c2.real + " + " + c2.imaginary + "i");
    }
}