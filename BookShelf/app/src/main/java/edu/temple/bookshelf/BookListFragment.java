package edu.temple.bookshelf;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment {

   // private View layout;
  //  private ListView bookList;
    private ArrayList<Book> books;
   // private TextView titles;
   // private TextView authors;
    private BookSelectedInterface parentActivity;
    private static final String BOOK_LIST_KEY = "Books";
   // static final String AUTHORS = "Authors";
  //  static final String TITLES  = "Titles";

    public BookListFragment() {
        // Required empty public constructor
    }



    public static BookListFragment newInstance(ArrayList<Book> books) {
        BookListFragment fragment = new BookListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_LIST_KEY, books);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof BookSelectedInterface) {
            parentActivity = (BookSelectedInterface) context;
        }
        else {
            throw new RuntimeException("Please implement the BookSelectedInterface interface for program to work!");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            books = (ArrayList<Book>) bundle.getSerializable(BOOK_LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView listView = (ListView) inflater.inflate(R.layout.fragment_book_list, container, false);

        listView.setAdapter(new BookListAdapter(getContext(), books));

       // ArrayAdapter<ArrayList<HashMap<String,String>>> adapter = new ArrayAdapter<ArrayList<HashMap<String,String>>>(layout.getContext(), android.R.layout.simple_list_item_1, Collections.singletonList(books));
       // final BookListAdapter adapter = new BookListAdapter(layout.getContext(), books);
      //  final BookAuthorAdapter adapter = new BookAuthorAdapter(layout.getContext(), books);
        //final SimpleAdapter adapter = new SimpleAdapter(layout.getContext(), books, android.R.layout.simple_list_item_2, new String[]{TITLES, AUTHORS}, new int[] {android.R.id.text1, android.R.id.text2});
        //bookList.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int bookIndex = position;
                parentActivity.BookSelected(position);
            }
        });

        return listView;
    }

    interface BookSelectedInterface {
        public void BookSelected(int index);
    }
}
