package com.example.imagecatalog.api;

import com.example.imagecatalog.api.response.BaseResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET(Urls.GALLERY_RANDOM)
    Single<Response<BaseResponse>> getGalleryRandom(@Path(RequestField.PAGE) int page);

}
