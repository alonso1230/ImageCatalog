package com.example.imagecatalog.ui.imagelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.imagecatalog.api.Repository;
import com.example.imagecatalog.api.response.BaseResponse;
import com.example.imagecatalog.base.BaseViewModel;
import com.example.imagecatalog.model.Resource;
import com.example.imagecatalog.model.dataclass.Data;
import com.example.imagecatalog.util.factory.DataSourceFactory;

import javax.inject.Inject;

public class ImageListViewModel extends BaseViewModel {

    private final Repository repository;

    private LiveData<PagedList<Data>> pagedListLiveData;
    private MutableLiveData<Resource<BaseResponse>> galleryRandomLiveData = new MediatorLiveData<>();

    @Inject
    public ImageListViewModel(Repository repository) {
        this.repository = repository;
        init();
    }

    private void init() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory(repository);
        dataSourceFactory.setGalleryRandomLiveData(galleryRandomLiveData);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(dataSourceFactory, config).build();
    }

    public LiveData<PagedList<Data>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public MutableLiveData<Resource<BaseResponse>> getGalleryRandomLiveData() {
        return galleryRandomLiveData;
    }

}
