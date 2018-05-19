package com.example.irisreitsma.restaurant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    // download image
    protected Bitmap doInBackground(String... strings) {

        String imageUrl = strings[0];
        Bitmap image = null;

        try {
            InputStream input = new URL(imageUrl). openStream();
            image = BitmapFactory.decodeStream(input);
        } catch(Exception e) {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }

        return image;
    }

    @Override
    // set image
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
