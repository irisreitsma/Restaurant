package com.example.irisreitsma.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItems;

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> menuItems) {
        super(context, resource, menuItems);
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // set image
        ImageView imageView = convertView.findViewById(R.id.image);
        new DownloadImage(imageView).execute(menuItems.get(position).getImageUrl());

        // set name
        TextView name = convertView.findViewById(R.id.name);
        name.setText(menuItems.get(position).getName());

        // set price
        TextView price = convertView.findViewById(R.id.price);
        price.setText("€ " + String.format("%.2f", menuItems.get(position).getPrice()));

        return convertView;
    }
}
