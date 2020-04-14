package com.example.imagecatalog.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.imagecatalog.R;

public class LoadImageView extends RelativeLayout {

    private ImageView ivLoadImage;
    private ProgressBar pbLoadImage;

    public LoadImageView(Context context) {
        super(context);
        init(context);
    }

    public LoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_load_image_view, this);
        ivLoadImage = view.findViewById(R.id.ivLoadImage);
        pbLoadImage = view.findViewById(R.id.pbLoadImage);
    }

    public ImageView getIvLoadImage() {
        return ivLoadImage;
    }

    public void startLoading() {
        ivLoadImage.setVisibility(GONE);
        pbLoadImage.setVisibility(VISIBLE);
    }

    public void completeLoading() {
        pbLoadImage.setVisibility(GONE);
        ivLoadImage.setVisibility(VISIBLE);
    }

}
