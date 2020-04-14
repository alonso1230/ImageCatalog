package com.example.imagecatalog.util.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.imagecatalog.api.Repository;
import com.example.imagecatalog.api.response.BaseResponse;
import com.example.imagecatalog.model.Resource;
import com.example.imagecatalog.model.dataclass.Data;
import com.example.imagecatalog.util.datasource.DataPageKeyedDataSource;

public class DataSourceFactory extends DataSource.Factory<Integer, Data> {

    private Repository repository;

    private MutableLiveData<Resource<BaseResponse>> galleryRandomLiveData;

    public DataSourceFactory(Repository repository) {
        this.repository = repository;
    }

    @Override
    public DataSource<Integer, Data> create() {
        DataPageKeyedDataSource dataPageKeyedDataSource = new DataPageKeyedDataSource(repository);
        dataPageKeyedDataSource.setGalleryRandomLiveData(galleryRandomLiveData);
        return dataPageKeyedDataSource;
    }

    public MutableLiveData<Resource<BaseResponse>> getGalleryRandomLiveData() {
        return galleryRandomLiveData;
    }

    public void setGalleryRandomLiveData(MutableLiveData<Resource<BaseResponse>> galleryRandomLiveData) {
        this.galleryRandomLiveData = galleryRandomLiveData;
    }
}
