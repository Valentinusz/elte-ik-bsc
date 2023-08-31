public class Kangaroo {
    private String name;
    private int age;

    public Kangaroo(String name, int age) {
        if (name != null && age >= 0) {
            this.name = name;
            this.age = age;
        }
    }

    public Kangaroo(int age) {
        System.out.println("2.");
    }

    public void display(String country) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(country);
        stringBuilder.append(" ");
        stringBuilder.append(this.name);
        stringBuilder.append(" ");
        stringBuilder.append(this.age);
        this.age++;
        stringBuilder.append(" ");
        stringBuilder.append(this.age);
    }
}