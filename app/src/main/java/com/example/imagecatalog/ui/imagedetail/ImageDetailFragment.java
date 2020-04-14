package com.example.imagecatalog.ui.imagedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.imagecatalog.R;
import com.example.imagecatalog.base.BaseFragment;
import com.example.imagecatalog.databinding.FragmentImageDetailBinding;
import com.example.imagecatalog.util.factory.ViewModelFactory;

import javax.inject.Inject;

public class ImageDetailFragment extends BaseFragment {

    public static final String DATA = "ImageDetailFragment.DATA";

    @Inject
    ViewModelFactory viewModelFactory;
    private ImageDetailViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageDetailViewModel.class);
        getArgs();
    }

    private void getArgs() {
        if (getArguments() != null) {
            viewModel.setData(getArguments().getParcelable(DATA));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentImageDetailBinding fragmentImageDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_detail, container, false);
        fragmentImageDetailBinding.setLifecycleOwner(this);
        fragmentImageDetailBinding.setViewModel(viewModel);
        return fragmentImageDetailBinding.getRoot();
    }
}
