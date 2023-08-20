package book;

public class Book extends java.lang.Object {
    private String author;
    private String title;
    protected int pageCount;
    
    //10
    public int getPageCount() {
        return pageCount;
    }

    
    //8
    public Book() {
        this.author = "John Steinbeck";
        this.title = "Of Mice and Men";
        this.pageCount = 107;
    }

    public Book (String author, String title, int pageCount) {
        if (null == author || null == title || pageCount < 1) {
            throw new IllegalArgumentException();
        }

        if (author.length() < 2 || title.length() < 4) {
            throw new IllegalArgumentException();
        }
        this.author = author;
        this.title = title;
        this.pageCount = pageCount;
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.author, 0, 2).append(" ");
        sb.append(this.title, 0, 4).append(" ");
        sb.append(this.pageCount);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.author).append(" ");
        sb.append(this.title).append(" ");
        sb.append(this.pageCount);
        return sb.toString();
    }

    public int getPrice() {
        return pageCount;
    }

    public String createReference(String name, int start, int end) {
        if (name == null || start > end || end < 1 || start < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this.getShortName()).append(" [");
        sb.append(start).append("-").append(end).append("] ");
        sb.append("referenced in article: ").append(name);
        return sb.toString();
    }
}