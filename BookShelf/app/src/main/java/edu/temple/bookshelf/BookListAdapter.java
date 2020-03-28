package edu.temple.bookshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class BookListAdapter extends BaseAdapter {
   // @Override
    private Context context;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    public BookListAdapter(Context c, ArrayList<HashMap<String,String>> list) {
        context = c;
        this.list = list;
    }
    public int getCount() {
        return list.size();

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
        TextView authors = new TextView(context);
        TextView titles = new TextView(context);

        authors.setTextSize(24);
        titles.setTextSize(24);
        for(int x = 0; x < getCount(); x++) {
            authors.setText(list.get(x).keySet().toString());
            titles.setText(list.get(x).values().toString());
        }

       LinearLayout ll = new LinearLayout(context);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);

        ll.addView(authors);
        ll.addView(titles);

        return ll;
    }
}

