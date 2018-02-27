package com.antont.imageloader.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.antont.imageloader.R;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    public static String ARG_ITEM_ID = "ARG_ITEM_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        Intent intent = getIntent();
        String path = intent.getStringExtra(ARG_ITEM_ID);

        FloatingActionButton actionButton = findViewById(R.id.itemDetailFAB);

        actionButton.setOnClickListener((View v) -> Toast.makeText(this, "Not Saved(", Toast.LENGTH_SHORT).show());

        ImageView image = findViewById(R.id.itemDetailImageView);
        Picasso.with(this).load(path).into(image);
    }
}
