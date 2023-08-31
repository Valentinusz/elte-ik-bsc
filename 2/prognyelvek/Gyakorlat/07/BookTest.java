import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BookTest {
    //String author1; // illegal author
    String author2 = "ABC";

    String title1 = "ZYX"; 
    String title2 = "X"; // illegal title

    String genre1 = "abc"; // illegal genre
    String genre2 = "SCIFI";
    String genre3 = "FANTASY";

    int reservePrice1 = -500; //  illegal price
    int reservePrice2 = 300;
    int reservePrice3 = 500;

    @Test
    public void nullAuthor() {
        assertEquals(null,Book.make(null, title1, genre2, reservePrice2));
    }

    @Test
    public void shortTitle() {
        assertEquals(null,Book.make(author2, title2, genre2, reservePrice2));
    }

    @Test
    public void illegalGenre() {
        assertEquals(null,Book.make(author2, title1, genre1, reservePrice2));
    }

    @Test
    public void illegalPrice() {
        assertEquals(null,Book.make(author2, title1, genre2, reservePrice1));
    }

    @Test
    public void legalBook() {//itt assertEquals az osztály equals metódusát hívja, ami alap esetben memória címet hasonlít össze, hacsak nem definiálod fülöl
    //írsz egy public boolean equals(Object other) szignatúrájú metódust a Bookba
    //alternatíva a book1.compare(book2) nullát ad-e vissza értékül
        Book.make(author2, title1, genre2, reservePrice2);
    }

    Book book1 = Book.make("ABC", "XDXD", "SCIFI", 500);
    Book book2 = Book.make("ABC", "XDD", "SCIFI", 300);
    Book book3 = Book.make("ABCD", "XDDDD", "SCIFI", 500);
    Book book4 = Book.make("ABCD", "XDDDDDD", "FANTASY", 900);

    @Test (expected = IllegalArgumentException.class)
    public void differentGenre() {
        book1.compare(book4);
    }

    @Test
    public void currentBookGreater() {
        assertEquals(1,book1.compare(book2));
    }

    @Test
    public void bookEquals() {
        assertEquals(0,book1.compare(book3));
    }

    @Test
    public void currentBookLesser() {
        assertEquals(-1,book2.compare(book1));
    }
}
