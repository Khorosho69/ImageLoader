package com.antont.imageloader.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antont.imageloader.R;

import java.util.ArrayList;
import java.util.List;

import com.antont.imageloader.adapters.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewInteractionListener {

    List<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageUrls.add("https://daphnecaruanagalizia.com/wp-content/uploads/2012/09/fox.jpg");
        mImageUrls.add("http://cdn6.whiskeyriff.com/wp-content/uploads/2018/01/111.jpg");
        mImageUrls.add("https://wallpaperscraft.com/image/fox_grass_snow_sit_hunting_31786_3840x2160.jpg");
        mImageUrls.add("https://lh3.googleusercontent.com/Voh_Yd-nzxyiq7MDl2sJTM7-74MusbRuX_cL1w3n1jmLQ0yKcwhmzfy3BIRzNuQTxQ=h900");
        mImageUrls.add("http://luchdesignstore.com/wp-content/uploads/2017/06/%D0%B0%D1%89%D1%87-%D0%B0%D1%84%D1%81%D1%83-%D0%B8%D0%B4.jpg");
        mImageUrls.add("http://www.animalspot.net/wp-content/uploads/2015/02/Fox-Head-Wallpaper.jpg");
        mImageUrls.add("https://static.boredpanda.com/blog/wp-content/uploads/2014/03/amazing-fox-photos-15.jpg");
        mImageUrls.add("https://hdwallsource.com/img/2015/7/wonderful-fox-wallpaper-46022-47305-hd-wallpapers.jpg");
        mImageUrls.add("https://i.ytimg.com/vi/H9UyWfzbxyQ/hqdefault.jpg");
        mImageUrls.add("http://i.imgur.com/eiR04mR.jpg");

        setupRecyclerView(mImageUrls);
    }

    private void setupRecyclerView(List<String> appItems) {
        RecyclerView recyclerView = findViewById(R.id.imageRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(appItems));
    }

    @Override
    public void onFragmentInteraction(View view, String imageUrl) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_ITEM_ID, imageUrl);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            intent.putExtra(DetailActivity.ARG_TRANSITION_NAME, view.getTransitionName());
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, view.getTransitionName());
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
