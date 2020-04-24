package edu.temple.bookshelf;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;

import edu.temple.audiobookplayer.AudiobookService;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {
    private View layout;
    private TextView title;
    private TextView author;
    private ImageView cover;
    private ImageButton playButton;
    private Book book;
    private PlayBookInterface parentActivity;

    final private static String AUTHOR_KEY = "Authors";
    final private static String TITLE_KEY = "Titles";
    final private static String BOOK = "Book";
    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BOOK, book);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof PlayBookInterface) {
            parentActivity = (PlayBookInterface)context;
        }
        else {
            throw new RuntimeException("Please implement PlayBookInterface interface for program to work");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            book = (Book) bundle.getParcelable(BOOK);
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
        cover = layout.findViewById(R.id.coverImageView);
        playButton = layout.findViewById(R.id.playImageButton);


        if(book != null) {
            DisplayBook(book);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        parentActivity.playBook(book.getId(), book.getTitle());
                        parentActivity.getCurrentAudioBookDuration(book.getDuration());
                    }
                });
        }


        return layout;
    }

    public void DisplayBook(Book book) {
        if (layout != null) {
            author.setText(book.getAuthor());
            title.setText(book.getTitle());
            Picasso.get().load(book.getCoverURL()).into(cover);
        }
    }

    interface PlayBookInterface {
        public void playBook(int id, String bookTitle);
        public void getCurrentAudioBookDuration(int duration);
    }
}
