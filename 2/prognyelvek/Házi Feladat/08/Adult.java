public class Adult extends Guest {

    public Adult(String name, int age) {
        super(name,age);
    }

    @Override
    public boolean isAdult() {
        return true;
    }
}