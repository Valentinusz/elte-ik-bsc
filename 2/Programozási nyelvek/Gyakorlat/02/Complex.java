class Complex {
    public double real;
    public double imaginary;

    Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double abs() {
        return Math.sqrt(Math.pow(this.real, 2)+Math.pow(this.imaginary, 2));
    }

    public void add(Complex c) {
        this.real += c.real;
        this.imaginary += this.imaginary;
    }

    public void sub(Complex c) {
        this.real -= c.real;
        this.imaginary -= c.imaginary;
    }

    public void mul(Complex c) {
        double newReal = (this.real * c.real) - (this.imaginary * c.imaginary);
        double newIm = (this.real * c.imaginary) + (this.imaginary * c.real);
        this.real = newReal;
        this.imaginary = newIm;
    }

    public void conjugate() {
        this.imaginary *= -1;
    }

    public void reciprocate() {
        double denominator = Math.pow(this.real, 2) + Math.pow(this.imaginary, 2);
        double newReal = this.real / denominator;
        double newIm = (-1) * this.imaginary;
        this.real = newReal;
        this.imaginary = newIm;
    }

    public void div(Complex c) {
        Complex conjugate = new Complex(c.real, c.imaginary);
        conjugate.conjugate();

        c.mul(conjugate);
        this.mul(conjugate);

        this.real /= c.real;
        this.imaginary /= c.real; 
    }
}