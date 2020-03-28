package edu.temple.bookshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BookAuthorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private static final int AUTHOR_INDEX = 0;
    private static final String AUTHOR_KEY = "Authors";

    public BookAuthorAdapter(Context c, ArrayList<HashMap<String, String>> list) {
        context = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).get(AUTHOR_KEY).toString();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] authors = GetKeySetArray(AUTHOR_INDEX, AUTHOR_KEY);
        TextView textView = new TextView(context);
        textView.setText(authors[position]);
        textView.setTextSize(20);
        return textView;
    }


    private String[] GetKeySetArray(int index, String key) {
        int size = list.size();
       // Object[] objects = list.get(index).values().toArray();
        String[] returnArray = new String[size];
        for (int x = 0; x < returnArray.length; x++) {
            returnArray[x] = list.get(x).get(AUTHOR_KEY).toString();
        }
        return returnArray;
    }
}
