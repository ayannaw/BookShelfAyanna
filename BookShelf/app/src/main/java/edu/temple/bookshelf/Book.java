package edu.temple.bookshelf;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id, duration;
    private String title, author, coverURL;
    public final static String JSON_ID = "book_id";
    public final static String JSON_TITLE = "title";
    public final static String JSON_AUTHOR = "author";
    public final static String JSON_COVER_URL = "cover_url";
    public final static String JSON_DURATION = "duration";

    public Book() {

    }

    public Book(int id, String title, String author, String coverURL, int duration) {
        this.author = author;
        this.title = title;
        this.id = id;
        this.coverURL = coverURL;
        this.duration = duration;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        coverURL = in.readString();
        duration = in.readInt();
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString() {
        return "ID: " + id + ", Author: " + author + ", Title: " + title + ", Cover_URL: " + coverURL + ", Duration: " + duration;
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
        dest.writeInt(duration);
    }
}
