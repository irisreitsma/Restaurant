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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    // callback method
    public interface CallBack {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    private Context context;
    private CallBack delegate;
    private String category;

    @Override
    // get error contents
    public void onErrorResponse(VolleyError error) {
        delegate.gotMenuError(error.getMessage());
    }

    @Override
    // transform JSONObject to ArrayList
    public void onResponse(JSONObject response) {

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        // extract categories from response and fill in list
        try {
            JSONArray array = response.getJSONArray("items");
            for(int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                if(itemObject.getString("category").equals(category)) {
                    MenuItem currentItem = new MenuItem();
                    currentItem.setCategory(itemObject.getString("category"));
                    currentItem.setDescription(itemObject.getString("description"));
                    currentItem.setImageUrl(itemObject.getString("image_url"));
                    currentItem.setPrice(itemObject.getDouble("price"));
                    currentItem.setName(itemObject.getString("name"));
                    menuItems.add(currentItem);
                }
            }
        }
        catch(JSONException j) { System.out.println(j); }

        // pass list
        delegate.gotMenu(menuItems);
    }

    public MenuRequest(Context context) {
        this.context = context;
    }

    public void getMenu(MenuRequest.CallBack activity, String aCategory) {

        RequestQueue queue = Volley.newRequestQueue(context);
        delegate = activity;
        category = aCategory;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu",null, this, this);
        queue.add(jsonObjectRequest);
    }
}
