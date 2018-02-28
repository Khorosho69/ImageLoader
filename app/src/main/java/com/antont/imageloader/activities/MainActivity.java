package com.antont.imageloader.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.antont.imageloader.R;

import java.util.ArrayList;
import java.util.List;

import com.antont.imageloader.adapters.RecyclerViewAdapter;
import com.antont.imageloader.architecture_components.ImageItemsViewModel;
import com.antont.imageloader.models.ImageItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageItemsViewModel viewModel = ViewModelProviders.of(this).get(ImageItemsViewModel.class);

        if (viewModel.getImageItems().isEmpty()) {
            viewModel.addImageItem(new ImageItem("https://daphnecaruanagalizia.com/wp-content/uploads/2012/09/fox.jpg"));
//            viewModel.addImageItem(new ImageItem("http://cdn6.whiskeyriff.com/wp-content/uploads/2018/01/111.jpg"));
//            viewModel.addImageItem(new ImageItem("https://wallpaperscraft.com/image/fox_grass_snow_sit_hunting_31786_3840x2160.jpg"));
//            viewModel.addImageItem(new ImageItem("https://lh3.googleusercontent.com/Voh_Yd-nzxyiq7MDl2sJTM7-74MusbRuX_cL1w3n1jmLQ0yKcwhmzfy3BIRzNuQTxQ=h900"));
//            viewModel.addImageItem(new ImageItem("http://luchdesignstore.com/wp-content/uploads/2017/06/%D0%B0%D1%89%D1%87-%D0%B0%D1%84%D1%81%D1%83-%D0%B8%D0%B4.jpg"));
//            viewModel.addImageItem(new ImageItem("http://www.animalspot.net/wp-content/uploads/2015/02/Fox-Head-Wallpaper.jpg"));
//            viewModel.addImageItem(new ImageItem("https://static.boredpanda.com/blog/wp-content/uploads/2014/03/amazing-fox-photos-15.jpg"));
//            viewModel.addImageItem(new ImageItem("https://hdwallsource.com/img/2015/7/wonderful-fox-wallpaper-46022-47305-hd-wallpapers.jpg"));
//            viewModel.addImageItem(new ImageItem("https://i.ytimg.com/vi/H9UyWfzbxyQ/hqdefault.jpg"));
//            viewModel.addImageItem(new ImageItem("http://i.imgur.com/eiR04mR.jpg"));
        }
        setupRecyclerView(viewModel.getImageItems());
    }

    private void setupRecyclerView(List<ImageItem> appItems) {
        RecyclerView recyclerView = findViewById(R.id.imageRecyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new RecyclerViewAdapter(appItems);
        recyclerView.setAdapter(adapter);

        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
