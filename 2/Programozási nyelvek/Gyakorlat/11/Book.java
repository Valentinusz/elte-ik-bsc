public class Book implements Printable {
    private String name;
    private String title;
    
    public Book(String name, String title) {
        if (name != null && title != null) {
            this.name = name;
            this.title = title;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void print() {
        System.out.println(this.name + ": " + this.title);
    }
}
