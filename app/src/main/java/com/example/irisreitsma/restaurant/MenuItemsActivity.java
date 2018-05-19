package com.example.irisreitsma.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        // set info
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        ImageView image = findViewById(R.id.image);

        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText("€" + String.format("%.2f", item.getPrice()));
        new DownloadImage(image).execute(item.getImageUrl());
    }
}
