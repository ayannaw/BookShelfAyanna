package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface
{

   // Book bookShelf = new Book();
    Book book;
    ArrayList<Book> collection = new ArrayList<Book>();
    FragmentManager manager;
    BookListFragment bookListFragment;
    BookDetailsFragment detailsFragment;
    Button searchButton;
    EditText searchEditText;
    String searchString, url;
    private boolean isTwoContainers;
    RequestQueue queue;
    JsonObjectRequest objectRequest;
    JsonArrayRequest arrayRequest;

    final private static String BOOK_ID = "book_id";
    final private static String AUTHOR = "author";
    final private static String TITLE = "title";
    final private static String COVER_URL = "cover_url";
    final
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = (Button) findViewById(R.id.searchButton);

        queue = Volley.newRequestQueue(this);
        isTwoContainers = findViewById(R.id.container2) != null;

        url = "https://kamorris.com/lab/abp/booksearch.php?search=a";
        RequestAndResponse(url);
        Log.i("Collection Size", "size: " + collection.size());

        
    }

    private void RequestAndResponse(String url) {
        //final ArrayList<Book> list = new ArrayList<>();
        arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i("Response", response.toString());
                            /*for(int x = 0; x < response.length(); x++) {
                                JSONObject object = response.getJSONObject(x);

                                String id = object.getString(BOOK_ID);
                                int bookId = Integer.parseInt(id);
                                String author = object.getString(AUTHOR);
                                String title = object.getString(TITLE);
                                String coverURL = object.getString(COVER_URL);

                                book = new Book(bookId, author, title, coverURL);
                                collection.add(book);*/
                                GetBookObjects(response);

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", error.toString());
                    }
                });
        queue.add(arrayRequest);
        Log.i("RequestAndResponse", arrayRequest.toString());
    }

    private void GetBookObjects(JSONArray array) {
        if (array != null) {
            Log.i("Array", array.toString());
            try {
                for (int x = 0; x < array.length(); x++) {
                    JSONObject object = array.getJSONObject(x);
                    String id = object.getString(BOOK_ID);
                    int bookId = Integer.parseInt(id);
                    String author = object.getString(AUTHOR);
                    String title = object.getString(TITLE);
                    String coverURL = object.getString(COVER_URL);

                    collection.add(new Book(bookId, author, title, coverURL));
                    Log.i("Collection Size after adding books", String.valueOf(collection.size()));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.i("Array Empty", array.toString());
        }


    }

    @Override
    public void BookSelected(int index) {
        System.out.println(index);
        Book aBook = collection.get(index);
        if(!isTwoContainers) {
            manager.beginTransaction()
                    .replace(R.id.container1, BookDetailsFragment.newInstance(aBook))
                    .addToBackStack(null)
                    .commit();
        }
        else {
            detailsFragment.DisplayBook(aBook);
        }
    }
}
