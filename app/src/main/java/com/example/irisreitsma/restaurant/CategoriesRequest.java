package com.example.irisreitsma.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // callback method
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    private Context context;
    private Callback delegate;

    @Override
    // get error contents
    public void onErrorResponse(VolleyError error) {
        delegate.gotCategoriesError(error.getMessage());
    }

    @Override
    // transform JSONObject to ArrayList
    public void onResponse(JSONObject response) {

        ArrayList<String> categories = new ArrayList<>();

        // extract categories from response and fill in list
        try {
            JSONArray array = response.getJSONArray("categories");
            for (int i = 0; i < array.length(); i++) {
                categories.add(array.getString(i));
            }
        }
        catch (JSONException j) {System.out.println(j.getMessage());}

        // pass list
        delegate.gotCategories(categories);
    }

    // constuctor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // method for retrieving categories from API
    public void getCategories(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        delegate = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories",null, this, this);
        queue.add(jsonObjectRequest);
    }
}
