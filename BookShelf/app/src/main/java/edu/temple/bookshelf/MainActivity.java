package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface
{

   // Book bookShelf = new Book();
    HashMap<String, String> book = new HashMap<>();
    ArrayList<HashMap<String,String>> collection = new ArrayList<HashMap<String, String>>();
    FragmentManager manager;
    BookListFragment bookListFragment = new BookListFragment();
    static final String AUTHORS = "Authors";
    static final String TITLES  = "Titles";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] authors = MainActivity.this.getResources().getStringArray(R.array.authors);
        String[] titles = MainActivity.this.getResources().getStringArray(R.array.titles);
        if (authors.length == titles.length) {
            for(int x = 0; x < authors.length; x++) {
                book = new HashMap<>();
                book.put(AUTHORS, authors[x]);
                book.put(TITLES, titles[x]);
                collection.add(book);
            }


            bookListFragment = BookListFragment.newInstance(collection);
        }

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.container1,bookListFragment).commit();
    }

    @Override
    public void BookSelected(int index) {
        System.out.println(index);
    }
}
