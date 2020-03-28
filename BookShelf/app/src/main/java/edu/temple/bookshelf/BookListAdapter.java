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
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private static final int AUTHOR_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final String AUTHOR_KEY = "Authors";
    private static final String TITLE_KEY  = "Titles";

    public BookListAdapter(Context c, ArrayList<HashMap<String,String>> list) {
        context = c;
        this.list = list;
    }
    public int getCount() {
        return list.get(AUTHOR_INDEX).size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_book_list, parent, false);
        }

        TextView author = new TextView(context);
        TextView title = new TextView(context);

        author.setTextSize(24);
        title.setTextSize(24);

        //authors.setText(list.get(position).keySet().toString());
        String[] authors = GetKeySetArray(AUTHOR_INDEX);
        String[] titles = GetKeySetArray(TITLE_INDEX);

        author.setText(authors[0]);
        title.setText(titles[0]);

        RelativeLayout layout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
       // layoutParams.addRule(RelativeLayout.ABOVE, author.getId());

      /*  for (int x = 0; x <authors.length; x++) {
            author = new TextView(context);
            title = new TextView(context);
            author.setText(authors[x]);
            title.setText(titles[x]);

        }*/

        layout.addView(author);
        layout.addView(title);
        return layout;

    }

    private String[] GetKeySetArray(int index) {
        Object[] objects = list.get(index).values().toArray();
        String[] values = new String[objects.length];
        for (int x = 0; x < values.length; x++) {
            values[x] = objects[x].toString();
        }
        return values;
    }

}

