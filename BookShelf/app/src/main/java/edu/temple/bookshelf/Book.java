package edu.temple.bookshelf;

import java.util.HashMap;

public class Book {
    private String author;
    private String title;

    private HashMap<String, String> bookCollection;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;

        bookCollection.put(author, title);

    }

    public HashMap GetCollection() {
        return bookCollection;
    }

    public void addToCollection(String author, String title) {
        bookCollection.put(author, title);
    }

}
