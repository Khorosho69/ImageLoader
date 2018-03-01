package com.antont.imageloader.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.antont.imageloader.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;

public class DetailActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback {

    public final static int PERMISSION_REQUEST_CODE = 1024;
    public final static String ARG_ITEM_ID = "ARG_IMAGE_URL";
    public final static String ARG_TRANSITION_NAME = "ARG_TRANSITION_NAME";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        setupImageView();

        FloatingActionButton actionButton = findViewById(R.id.itemDetailFAB);
        actionButton.setOnClickListener((View v) -> checkAndroidPermission());
    }

    private void setupImageView() {
        mImageView = findViewById(R.id.itemDetailImageView);

        Intent intent = getIntent();
        String path = intent.getStringExtra(ARG_ITEM_ID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName(intent.getStringExtra(ARG_TRANSITION_NAME));
        }

        Picasso.with(this).load(path).into(mImageView);
    }

    private void checkAndroidPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (isPermissionGranted()) {
                saveImageToGallery();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "Write External Storage permission allows us to do save images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                }
            }
        } else {
            saveImageToGallery();
        }
    }

    private boolean isPermissionGranted() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == PERMISSION_REQUEST_CODE) {
            saveImageToGallery();
        }
    }

    private void saveImageToGallery() {
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "image", "image");
        Toast.makeText(this, "Saved to gallery", Toast.LENGTH_SHORT).show();
    }
}
