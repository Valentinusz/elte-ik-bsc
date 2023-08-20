package book;

public class PrintedBook extends Book {
    public enum Cover {
        HARDCOVER,
        SOFTCOVER;
    }

    private Cover coverType;

    public PrintedBook(String author, String title, int pageCount, Cover cover) {
        super(author, title, pageCount+6);
        this.coverType = cover;
    }

    @Override
    public String toString() {
        return super.toString() + " " + coverType.name();
    }

    @Override
    public int getPrice() {
        switch (this.coverType) {
            case HARDCOVER:
            return super.getPrice() * 3;

            case SOFTCOVER:
            return super.getPrice() * 2;

            default:
            return super.getPrice();
        }
    }

    @Override
    public String createReference(String name, int start, int end) {
        if (name == null || start > end || end < 1 || start < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" [");
        sb.append(start).append("-").append(end).append("] ");
        sb.append("referenced in article: ").append(name);
        return sb.toString();
    }
}
