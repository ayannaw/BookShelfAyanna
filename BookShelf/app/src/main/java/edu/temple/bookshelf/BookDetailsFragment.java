package edu.temple.bookshelf;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
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
    private boolean serviceConnected;
    private AudiobookService.MediaControlBinder audioBookBinder;
    final private static String AUTHOR_KEY = "Authors";
    final private static String TITLE_KEY = "Titles";
    final private static String BOOK = "Book";
    final private static String SERVICE_CONNECTION = "service_connection";
    final private static String BINDER = "audio_book_binder";
    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book book, AudiobookService.MediaControlBinder audioBookBinder, boolean serviceConnection) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        bundle.putBoolean(SERVICE_CONNECTION, serviceConnection);
        bundle.putBinder(BINDER, audioBookBinder);
        bundle.putParcelable(BOOK, book);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            book = (Book) bundle.getParcelable(BOOK);
            audioBookBinder = (AudiobookService.MediaControlBinder) bundle.getBinder(BINDER);
            serviceConnected = bundle.getBoolean(SERVICE_CONNECTION);
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
                    if(serviceConnected) {
                        if(audioBookBinder.isPlaying()) {
                            audioBookBinder.pause();
                        }
                        else {
                            audioBookBinder.play(book.getId());
                        }
                    }
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
}
