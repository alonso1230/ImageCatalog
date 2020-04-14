package com.example.imagecatalog.di.module;

import com.example.imagecatalog.api.RequestField;
import com.example.imagecatalog.api.Service;
import com.example.imagecatalog.api.Urls;
import com.example.imagecatalog.api.deserializer.BaseResponseDeserializer;
import com.example.imagecatalog.api.response.BaseResponse;
import com.example.imagecatalog.app.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApiModule {

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(Constants.TIMEOUT_TIME, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT_TIME, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT_TIME, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader(RequestField.AUTHORIZATION, "Client-ID " + Constants.IMGUR_CLIENT_ID)
                            .build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new BaseResponseDeserializer())
                .create();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    static Service provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }
}