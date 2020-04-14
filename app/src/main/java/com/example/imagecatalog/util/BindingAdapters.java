package com.example.imagecatalog.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.imagecatalog.util.view.LoadImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BindingAdapters {

    @BindingAdapter({"imageUrl", "errorImage"})
    public static void loadImageUrl(LoadImageView view, String imageUrl, Drawable errorImage) {
        Picasso.get()
                .load(imageUrl)
                .error(errorImage)
                .into(view.getIvLoadImage(), new Callback() {
                    @Override
                    public void onSuccess() {
                        view.completeLoading();
                    }

                    @Override
                    public void onError(Exception e) {
                        view.completeLoading();
                    }
                });
    }

    @BindingAdapter({"setDate"})
    public static void setDateForTimestamp(TextView textView, long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        textView.setText(sdf.format(d));
    }

}
