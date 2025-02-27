package edu.temple.bookshelf;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {
    private View layout;
    private TextView title;
    private TextView author;
    private HashMap<String, String> Book;
    final private static String AUTHOR_KEY = "Authors";
    final private static String TITLE_KEY = "Titles";
    final private static String BOOK = "Book";
    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(HashMap<String, String>book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK, book);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            Book = (HashMap<String, String>) bundle.getSerializable(BOOK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_book_details, container, false);

     //   DisplayBook(Book);

        author = layout.findViewById(R.id.author);
        title = layout.findViewById(R.id.title);

        return layout;
    }


    public void DisplayBook(HashMap<String, String> book) {
        if (layout != null) {
            author.setText(book.get(AUTHOR_KEY).toString());
            title.setText(book.get(TITLE_KEY).toString());
        }
    }
}
