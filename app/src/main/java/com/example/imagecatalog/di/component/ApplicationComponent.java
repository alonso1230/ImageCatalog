package com.example.imagecatalog.di.component;

import android.app.Application;

import com.example.imagecatalog.app.App;
import com.example.imagecatalog.di.module.ActivityBindingModule;
import com.example.imagecatalog.di.module.ApiModule;
import com.example.imagecatalog.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(App app);
}
