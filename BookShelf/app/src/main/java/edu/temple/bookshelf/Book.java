package edu.temple.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String title, author, coverURL;
    public final static String JSON_ID = "book_id";
    public final static String JSON_TITLE = "title";
    public final static String JSON_AUTHOR = "author";
    public final static String JSON_COVER_URL = "cover_url";

    public Book() {

    }

    public Book(int id, String title, String author, String coverURL) {
        this.author = author;
        this.title = title;
        this.id = id;
        this.coverURL = coverURL;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        coverURL = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle()    {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String  getAuthor()  {
        return author;
    }
    public void setAuthor(String author) { this.author = author; }

    public String getCoverURL() { return coverURL; }
    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
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
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverURL);
    }
}
