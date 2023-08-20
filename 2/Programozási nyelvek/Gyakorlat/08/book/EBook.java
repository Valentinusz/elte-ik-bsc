package book;

public class EBook extends Book {
    private int PDFSize;

    public EBook(String author, String title, int pageCount, int fileSize) {
        super(author,title,pageCount);
        this.PDFSize = fileSize;
    }
    /*
    @Override
    public String toString() {
        return super.toString() + " " + this.PDFSize;
    }
    */

    @Override
    public int getPrice() {
        return super.getPrice() + this.PDFSize;
    }

    @Override
    public String createReference(String name, int start, int end) {
        if (name == null || start > end || end < 1 || start < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("(PDF size: ").append(this.PDFSize).append(") [");
        sb.append(start).append("-").append(end).append("] ");
        sb.append("referenced in article: ").append(name);
        return sb.toString();
    }

    public String createReference(String name, int start, int end, String date) {
        if (name == null || date == null || start > end || end < 1 || start < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("(PDF size: ").append(this.PDFSize).append(") [");
        sb.append(start).append("-").append(end).append("] ");
        sb.append("referenced in article: ").append(name).append(", accessing PDF date: ").append(date);
        return sb.toString();
    }

    
}
