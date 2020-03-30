package edu.temple.bookshelf;

public class Book {
    private int id;
    private String title;
    private String author;
    private String coverURL;

    public Book() {

    }

    public Book(int id, String title, String author, String coverURL) {
        this.author = author;
        this.title = title;
        this.id = id;
        this.coverURL = coverURL;
    }

    public int getId() {
        return id;
    }

    public String getTitle()    {
        return title;
    }

    public String  getAuthor()  {
        return author;
    }

}
