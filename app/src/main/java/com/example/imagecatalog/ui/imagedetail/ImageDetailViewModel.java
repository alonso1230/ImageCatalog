package com.example.imagecatalog.ui.imagedetail;

import androidx.lifecycle.MutableLiveData;

import com.example.imagecatalog.api.Repository;
import com.example.imagecatalog.base.BaseViewModel;
import com.example.imagecatalog.model.Resource;
import com.example.imagecatalog.model.dataclass.Data;

import javax.inject.Inject;

public class ImageDetailViewModel extends BaseViewModel {

    private final MutableLiveData<Resource<Data>> dataLiveData = new MutableLiveData<>();

    @Inject
    public ImageDetailViewModel() {
    }

    public void setData(Data data) {
        dataLiveData.setValue(new Resource<>(Resource.Status.SUCCESS, data, null));
    }

    public MutableLiveData<Resource<Data>> getDataLiveData() {
        return dataLiveData;
    }

}
