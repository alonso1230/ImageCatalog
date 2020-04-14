package com.example.imagecatalog.base;

import androidx.fragment.app.Fragment;

import com.example.imagecatalog.R;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    protected void openScreen(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss();
    }

}
