package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Book bookShelf = new Book();
    HashMap<String, String> books = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] authors = MainActivity.this.getResources().getStringArray(R.array.authors);
        String[] titles = MainActivity.this.getResources().getStringArray(R.array.title);
        if (authors.length == titles.length) {
            for(int x = 0; x < authors.length; x++) {
                bookShelf.addToCollection(authors[x], titles[x]);
            }

            books  = bookShelf.GetCollection();

        }

    }
}
