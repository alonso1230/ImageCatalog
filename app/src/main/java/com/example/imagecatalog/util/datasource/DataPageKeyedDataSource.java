package com.example.imagecatalog.util.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.imagecatalog.api.Repository;
import com.example.imagecatalog.api.response.BaseResponse;
import com.example.imagecatalog.model.Resource;
import com.example.imagecatalog.model.dataclass.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DataPageKeyedDataSource extends PageKeyedDataSource<Integer, Data> {

    private Repository repository;

    private MutableLiveData<Resource<BaseResponse>> galleryRandomLiveData = new MutableLiveData<>();
    private CompositeDisposable disposableList = new CompositeDisposable();

    public DataPageKeyedDataSource(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        disposableList.add(repository.getGalleryRandom(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> galleryRandomLiveData.postValue(new Resource<>(Resource.Status.LOADING, null, null)))
                .subscribe(s -> {
                            if (s.isSuccessful()) {
                                BaseResponse baseResponse = s.body();
                                if (baseResponse.isSuccess()) {
                                    filterDataList(s.body().getDataArray());
                                    callback.onResult(s.body().getDataArray(), null, 1);
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.SUCCESS, baseResponse, null));
                                } else {
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, new Throwable(baseResponse.getDataObject().getError())));
                                }
                            } else {
                                try {
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, new Throwable(s.errorBody().string())));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, e));
                                }
                            }
                        },
                        throwable -> {
                            galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, throwable));
                        }
                ));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        disposableList.add(repository.getGalleryRandom(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            if (s.isSuccessful()) {
                                BaseResponse baseResponse = s.body();
                                if (baseResponse.isSuccess()) {
                                    filterDataList(s.body().getDataArray());
                                    callback.onResult(s.body().getDataArray(), params.key + 1);
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.SUCCESS, baseResponse, null));
                                } else {
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, new Throwable(baseResponse.getDataObject().getError())));
                                }
                            } else {
                                try {
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, new Throwable(s.errorBody().string())));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, e));
                                }
                            }
                        },
                        throwable -> {
                            galleryRandomLiveData.setValue(new Resource<>(Resource.Status.ERROR, null, throwable));
                        }));
    }

    public MutableLiveData<Resource<BaseResponse>> getGalleryRandomLiveData() {
        return galleryRandomLiveData;
    }

    public void setGalleryRandomLiveData(MutableLiveData<Resource<BaseResponse>> galleryRandomLiveData) {
        this.galleryRandomLiveData = galleryRandomLiveData;
    }

    private void filterDataList(ArrayList<Data> dataList) {
        Iterator<Data> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            if (!data.getLink().endsWith(".png") && !data.getLink().endsWith(".jpg")) {
                iterator.remove();
            }
        }
    }
}
