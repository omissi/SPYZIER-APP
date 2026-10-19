package com.rana_aditya.parent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class screenview extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenshotview);

        imageView = findViewById(R.id.issimage);

        Intent intent = getIntent();
        String url = intent.getStringExtra("bitmapinfo");

        Glide.with(this)
                .load(url)
                .into(imageView);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] decodedBytes = Base64.decode(
                    encodedString,
                    Base64.DEFAULT
            );

            return BitmapFactory.decodeByteArray(
                    decodedBytes,
                    0,
                    decodedBytes.length
            );
        } catch (Exception e) {
            return null;
        }
    }
}
