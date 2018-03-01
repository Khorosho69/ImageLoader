package com.antont.imageloader.adapters;

import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
        holder.mImageView.setBackgroundColor(getRandomColor());

        setImageToImageView(holder, position);

        holder.mImageView.setOnClickListener((v) -> onImageViewPressed(holder.mImageView, mDataset.get(position).getImageURL()));
    }

    private void setImageToImageView(ViewHolder holder, int position) {
        Picasso.with(holder.mImageView.getContext())
                .load(mDataset.get(position).getImageURL())
                .fit()
                .centerCrop()
                .into(holder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mImageView.setBackgroundColor(Color.WHITE);
                        holder.mProgressBar.setVisibility(View.GONE);

                        TextDrawerUtility drawer = new TextDrawerUtility();
                        drawer.drawTextOverImage(holder.mImageView, "New");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.mImageView.setTransitionName("imageView" + position);
                        }
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

    private void onImageViewPressed(View view, String imageUrl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(view, imageUrl);
        }
    }

    public interface OnRecyclerViewInteractionListener {
        void onFragmentInteraction(View view, String imageUrl);
    }
}