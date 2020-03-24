package edu.temple.bookshelf;

import java.util.ArrayList;
import java.util.HashMap;

public class Book {
    private String author;
    private String title;

    private HashMap<String, String> book;

    private ArrayList<HashMap<String,String>> collection;

    public Book () {

    }
    public Book(String author, String title) {
        this.author = author;
        this.title = title;

        book.put(author, title);

    }

    public HashMap GetBook() {
        return book;
    }

    public ArrayList<HashMap<String, String>> GetCollection() {
        return collection;
    }

    public void addToCollection(String author, String title) {
        book.put(author, title);
        collection.add(book);
    }

}
