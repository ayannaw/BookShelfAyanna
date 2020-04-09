package edu.temple.bookshelf;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class BookListAdapter extends BaseAdapter {
   // @Override
    //private Activity parentActivity;
    private Context context;
    private ArrayList<Book> list = new ArrayList<Book>();
   /* private static final int AUTHOR_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final String AUTHOR_KEY = "Authors";
    private static final String TITLE_KEY  = "Titles";*/

    public BookListAdapter(Context c, ArrayList<Book> list) {
        context = c;
        this.list = list;
    }
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView title, author;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (!(convertView instanceof LinearLayout)) {
            convertView = inflater.inflate(R.layout.book_adapter_layout, parent, false);
        }

        title = convertView.findViewById(R.id.title);
        author = convertView.findViewById(R.id.author);

        title.setText(((Book) getItem(position)).getTitle());
        author.setText(((Book) getItem(position)).getAuthor());

        return convertView;
    }

}

