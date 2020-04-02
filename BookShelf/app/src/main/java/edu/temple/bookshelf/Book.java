package edu.temple.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String title;
    private String author;
    private String coverURL;

    public Book() {

    }

    public Book(int id, String author, String title, String coverURL) {
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

    public String toString() {
        return "ID: " + id + ", Author: " + author + ", Title: " + title + ", Cover_URL: " + coverURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
