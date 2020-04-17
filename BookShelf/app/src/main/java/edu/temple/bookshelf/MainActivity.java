package edu.temple.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.temple.audiobookplayer.AudiobookService;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface
{

   // Book bookShelf = new Book();
    private Book selectedBook;
    private ArrayList<Book> bookList = new ArrayList<Book>();
    boolean connected;
    AudiobookService audiobookService;
    Intent audioBookIntent;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connected = true;
           // audiobookService =
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };
    FragmentManager manager;

    BookListFragment bookListFragment;
    BookDetailsFragment bookDetailsFragment;

    Button searchButton;
    EditText searchEditText;

    private String searchString;
    private boolean isTwoContainers;

    RequestQueue queue;
    JsonArrayRequest arrayRequest;

    final private static String BOOK_LIST_KEY = "booklist";
    final private static String SELECTED_BOOK = "selectedBook";
    final private static String SEARCH_API = "https://kamorris.com/lab/abp/booksearch.php?search=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        audioBookIntent = new Intent (MainActivity.this, AudiobookService.class);

        bindService(audioBookIntent, serviceConnection, BIND_AUTO_CREATE);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchString = searchEditText.getText().toString();
                getBookList(searchString);
            }
        });

        if(savedInstanceState != null) {
            bookList = savedInstanceState.getParcelableArrayList(BOOK_LIST_KEY);
            selectedBook = savedInstanceState.getParcelable(SELECTED_BOOK);
        }
        else {
            bookList = new ArrayList<Book>();
        }

        isTwoContainers = findViewById(R.id.container2) != null;

        queue = Volley.newRequestQueue(this);

        bookListFragment = BookListFragment.newInstance(bookList);

        manager.beginTransaction().replace(R.id.container1, bookListFragment).commit();

        if(isTwoContainers) {
            if(selectedBook != null) {
                bookDetailsFragment = bookDetailsFragment.newInstance(selectedBook);
            }
            else {
                bookDetailsFragment = new BookDetailsFragment();
            }

            manager.beginTransaction().replace(R.id.container2, bookDetailsFragment).commit();
        }
        else {
            if(selectedBook != null) {
                manager.beginTransaction().replace(R.id.container1,
                        BookDetailsFragment.newInstance(selectedBook)).addToBackStack(null)
                        .commit();
            }
        }
    }

    private void getBookList(String searchString) {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(SEARCH_API + searchString,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            bookList.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject bookJSON;
                                    bookJSON = response.getJSONObject(i);
                                    Book aBook = new Book(bookJSON.getInt(Book.JSON_ID),
                                            bookJSON.getString(Book.JSON_TITLE),
                                            bookJSON.getString(Book.JSON_AUTHOR),
                                            bookJSON.getString(Book.JSON_COVER_URL),
                                            bookJSON.getInt(Book.JSON_DURATION));
                                    bookList.add(aBook);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            updateBooksDisplay();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    getString(R.string.search_error_message),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(arrayRequest);
    }

    private void updateBooksDisplay() {
        if(manager.findFragmentById(R.id.container1) instanceof
                BookDetailsFragment) {
            manager.popBackStack();
        }
        bookListFragment.UpdateBooks(bookList);
    }

    @Override
    public void BookSelected(int index) {
        System.out.println(index);
        selectedBook = bookList.get(index);
        if(isTwoContainers) {
            bookDetailsFragment.DisplayBook(selectedBook);
        }
        else {
            manager.beginTransaction().replace(R.id.container1, BookDetailsFragment.newInstance(selectedBook))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (bookList.size() > 0) {
            outState.putParcelableArrayList(BOOK_LIST_KEY, bookList);
            if(selectedBook != null) {
                outState.putParcelable(SELECTED_BOOK, selectedBook);
            }
        }

    }
}
