public class Book {

    static int id = 0;//valami kezdő értéket is kell adni neki

    enum Genre {
        FANTASY,
        SATIRE,
        SCIFI,
        PHILOSOPHY,
        EDUCATIONAL;
    }

    private String author;
    private String title;
    private int reservePrice;
    private int bookId;
    Genre genre;

    private Book(String author, String title, String genre, int reservePrice) {
        this.author = author;
        this.title = title;
        this.genre = Genre.valueOf(genre);
        this.reservePrice = reservePrice;
        this.bookId = ++id;
    }

    static Book make(String author, String title, String genre, int reservePrice) {
        Book book;
        if (author == null || author.length() < 2 || title == null || title.length() < 2 || reservePrice < 0) {
            return null;
        }
        try {
            book = new Book(author, title, genre, reservePrice);
            return book;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    static boolean isSameGenre(Book book1, Book book2) {
        return book1.genre == book2.genre;
    }

    byte compare(Book book) {
        if (Book.isSameGenre(this, book)) {
            if (this.reservePrice > book.reservePrice) {
                return 1;
            } else if (this.reservePrice == book.reservePrice) {
                return 0;
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException("Books need to be of matching genre.");
        }
    }
}