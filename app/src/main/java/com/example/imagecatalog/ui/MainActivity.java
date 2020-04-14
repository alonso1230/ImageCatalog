package com.example.imagecatalog.ui;

import android.os.Bundle;

import com.example.imagecatalog.R;
import com.example.imagecatalog.ui.imagelist.ImageListFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ImageListFragment()).commit();
    }

}
