package edu.temple.bookshelf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BookTitleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private static final int TITLE_INDEX = 1;
    private static final String TITLE_KEY = "Titles";

    public BookTitleAdapter(Context c, ArrayList<HashMap<String,String>> list) {
        context = c;
        this.list = list;
    }

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).get(TITLE_KEY).toString();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] titles = GetKeySetArray(TITLE_INDEX,TITLE_KEY);
        TextView textView = new TextView(context);
        textView.setText(titles[position]);
        textView.setTextSize(20);
        return textView;

    }

    private String[] GetKeySetArray(int index, String key) {
        int size = list.size();
        // Object[] objects = list.get(index).values().toArray();
        String[] returnArray = new String[size];
        for (int x = 0; x < returnArray.length; x++) {
            returnArray[x] = list.get(x).get(TITLE_KEY).toString();
        }
        return returnArray;
    }
}
