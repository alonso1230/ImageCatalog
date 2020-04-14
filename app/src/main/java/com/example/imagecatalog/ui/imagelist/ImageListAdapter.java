package com.example.imagecatalog.ui.imagelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagecatalog.R;
import com.example.imagecatalog.model.dataclass.Data;
import com.example.imagecatalog.util.view.LoadImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageListAdapter extends PagedListAdapter<Data, ImageListAdapter.ImageListViewHolder> {

    private OnItemClickListener onItemClickListener;

    public ImageListAdapter(DiffUtil.ItemCallback<Data> diffUtilCallback, OnItemClickListener onItemClickListener) {
        super(diffUtilCallback);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list, parent, false);
        ImageListViewHolder holder = new ImageListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, final int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onItemClicked(getItem(position));
        });
    }

    static class ImageListViewHolder extends RecyclerView.ViewHolder {

        private LoadImageView loadImageView;

        public ImageListViewHolder(@NonNull View itemView) {
            super(itemView);
            loadImageView = itemView.findViewById(R.id.vLoadImage);
        }

        public void bind(Data data) {
            Picasso.get()
                    .load(data.getLink())
                    .error(R.drawable.ic_error)
                    .into(loadImageView.getIvLoadImage(), new Callback() {
                        @Override
                        public void onSuccess() {
                            loadImageView.completeLoading();
                        }

                        @Override
                        public void onError(Exception e) {
                            loadImageView.completeLoading();
                        }
                    });
        }
    }

    interface OnItemClickListener {
        void onItemClicked(Data data);
    }
}
