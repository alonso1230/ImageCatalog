package com.example.imagecatalog.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.imagecatalog.di.util.ViewModelKey;
import com.example.imagecatalog.ui.imagedetail.ImageDetailViewModel;
import com.example.imagecatalog.ui.imagelist.ImageListViewModel;
import com.example.imagecatalog.util.factory.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel.class)
    abstract ViewModel bindImageListViewModel(ImageListViewModel imageListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailViewModel.class)
    abstract ViewModel bindImageDetailViewModel(ImageDetailViewModel imageDetailViewModel);
}