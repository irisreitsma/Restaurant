package com.example.irisreitsma.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // get categories
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    // attach adapter to listview
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> category = new CategoryAdapter(this, R.layout.entry_row, categories);
        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(category);
        lv.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    // make toast when error
    public void gotCategoriesError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    // when category is clicked open menuactivity
    public class OnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get clicked category
            String category = (String) adapterView.getItemAtPosition(i);

            // open menuactivity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
