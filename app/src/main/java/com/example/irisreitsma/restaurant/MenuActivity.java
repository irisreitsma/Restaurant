package com.example.irisreitsma.restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class MenuActivity extends AppCompatActivity implements MenuRequest.CallBack {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get menu
        MenuRequest request = new MenuRequest(this);
        Intent intent = getIntent();
        request.getMenu(this, intent.getStringExtra("category"));
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        ListView lv = findViewById(R.id.listview2);
        lv.setAdapter(new MenuItemAdapter(this, R.layout.item, menuItems));
        lv.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    // make toast when error
    public void gotMenuError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    // when item is clicked open info
    public class OnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get clicked item
            MenuItem item = (MenuItem) adapterView.getItemAtPosition(i);

            // open menuitemactivity
            Intent intent = new Intent(MenuActivity.this, MenuItemsActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        }
    }
}

