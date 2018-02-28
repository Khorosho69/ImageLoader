package com.antont.imageloader.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.antont.imageloader.R;
import com.antont.imageloader.utilities.TextDrawerUtility;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import com.antont.imageloader.models.ImageItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ImageItem> mDataset;

    private OnRecyclerViewInteractionListener mListener;

    public RecyclerViewAdapter(List<ImageItem> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        if (parent.getContext() instanceof OnRecyclerViewInteractionListener) {
            mListener = (OnRecyclerViewInteractionListener) parent.getContext();
        } else {
            throw new RuntimeException(parent.getContext().toString()
                    + " must implement OnRecyclerViewInteractionListener");
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ImageItem item = mDataset.get(position);

        holder.mImageView.setBackgroundColor(getRandomColor());

        loadImageAndSet(holder, item);

        holder.mImageView.setOnClickListener((v) -> onImageViewPressed(mDataset.get(position).getImageURL()));
    }

    private void loadImageAndSet(ViewHolder holder, ImageItem item) {
        Picasso.with(holder.mImageView.getContext())
                .load(item.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mImageView.setBackgroundColor(Color.WHITE);
                        holder.mProgressBar.setVisibility(View.GONE);

                        TextDrawerUtility drawer = new TextDrawerUtility();
                        drawer.drawTextOverImage(holder.mImageView, "New");
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        ProgressBar mProgressBar;

        ViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.itemImageView);
            mProgressBar = v.findViewById(R.id.itemProgressBar);
        }
    }

    private void onImageViewPressed(String imageUrl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(imageUrl);
        }
    }

    public interface OnRecyclerViewInteractionListener {
        void onFragmentInteraction(String imageUrl);
    }
}