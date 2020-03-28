package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   // Book bookShelf = new Book();
    HashMap<String, String> book = new HashMap<>();
    ArrayList<HashMap<String,String>> collection = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] authors = MainActivity.this.getResources().getStringArray(R.array.authors);
        String[] titles = MainActivity.this.getResources().getStringArray(R.array.titles);
        if (authors.length == titles.length) {
            for(int x = 0; x < authors.length; x++) {
                book.put(authors[x],titles[x]);
                collection.add(book);
            }
        }


        

    }
}
