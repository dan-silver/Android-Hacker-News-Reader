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

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleArrayAdapter extends ArrayAdapter<JSONObject> {
    private final Context context;
    private final JSONObject[] values;

    public SimpleArrayAdapter(Context context, int layout, JSONObject[] values) {
        super(context, R.layout.article_headline, layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.article_headline, parent, false);
        if (rowView != null) {
            TextView titleTextView = (TextView) rowView.findViewById(R.id.title);
            TextView commentsTextView = (TextView) rowView.findViewById(R.id.comments);
            try {
                titleTextView.setText((String) values[position].get("title"));
                commentsTextView.setText(values[position].get("commentCount") + " comments");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return rowView;
    }
}