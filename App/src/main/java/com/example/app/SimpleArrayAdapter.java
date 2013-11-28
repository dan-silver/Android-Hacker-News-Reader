package com.example.app;

/**
 * Created by dan on 11/27/13.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public SimpleArrayAdapter(Context context, int layout, String[] values) {
        super(context, R.layout.article_headline, layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.article_headline, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(values[position]);
        return rowView;
    }
}