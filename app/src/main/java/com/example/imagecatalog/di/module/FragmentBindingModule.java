package com.example.imagecatalog.di.module;

import com.example.imagecatalog.ui.imagedetail.ImageDetailFragment;
import com.example.imagecatalog.ui.imagelist.ImageListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract ImageListFragment provideImageListFragment();

    @ContributesAndroidInjector
    abstract ImageDetailFragment provideImageDetailFragment();
}