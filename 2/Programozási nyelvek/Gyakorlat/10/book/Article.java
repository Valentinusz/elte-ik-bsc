package book;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Article {
    private String title;
    private String body;
    private String conclusion;
    private PrintWriter printWriter;
    private ArrayList<Book> refs;

    Article(String title, String body, String conclusion) {
        this.title = title;
        this.body = body;
        this.conclusion = conclusion;
        this.refs = new ArrayList<>();
    }

    public void addBookToReferences(Book book) {
        this.refs.add(book);
    }

    public void print(String file) {
        try {
            this.printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        printWriter.println(this.title);
        printWriter.println(this.body);
        printWriter.println(this.conclusion);
        
        for (Book book : this.refs) {
            printWriter.println(book.createReference(this.title, 1, book.getPageCount()));
        }
    }
}
