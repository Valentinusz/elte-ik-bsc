public class Guest {
    protected String name;
    protected int age;

    public Guest(String name, int age) {
        if (name == null || age < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isAdult() {
        return false;
    }
}
