package com.example.irisreitsma.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class CategoryAdapter extends ArrayAdapter<String> {

    private ArrayList<String> categories;

    public CategoryAdapter(@NonNull Context context, int resource, ArrayList<String> categories) {
        super(context, resource, categories);
        this.categories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_row, parent, false);
        }

        // set text
        TextView textView = convertView.findViewById(R.id.category);
        textView.setText(categories.get(position));

        return convertView;
    }
}
