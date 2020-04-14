package com.example.imagecatalog.api;

import com.example.imagecatalog.api.response.BaseResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class Repository {

    private final Service service;

    @Inject
    public Repository(Service service) {
        this.service = service;
    }

    public Single<Response<BaseResponse>> getGalleryRandom(int page) {
        return service.getGalleryRandom(page);
    }

}
