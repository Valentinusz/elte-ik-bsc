import book.*;
import book.PrintedBook.Cover;

public class BookMain {
    public static void main(String[] args) {
        EBook eb = new EBook("XDXDXDXDXD","DSAFASSA",42,69);
        System.out.println(eb.getShortName());
        System.out.println(eb.toString());
        System.out.println(eb.getPrice());

        PrintedBook pb1 = new PrintedBook("KEKKEKKEK", "ASFAFSFASASFASF", 100, Cover.SOFTCOVER);
        System.out.println(pb1.getShortName());
        System.out.println(pb1.toString());
        System.out.println(pb1.getPrice());

        PrintedBook pb2 = new PrintedBook("LULULULULUL", "HFHFHDFDHF", 300, Cover.HARDCOVER);
        System.out.println(pb2.getShortName());
        System.out.println(pb2.toString());
        System.out.println(pb2.getPrice());


        System.out.println(pb1.createReference("ABC", 42, 69));
        System.out.println(pb2.createReference("ABC", 42, 69));
        System.out.println(eb.createReference("ABC", 42, 69));
        System.out.println(eb.createReference("ABC", 42, 69,"2022"));
    }
}