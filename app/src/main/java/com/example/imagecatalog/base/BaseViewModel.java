package com.example.imagecatalog.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable disposableList = new CompositeDisposable();

    protected void addDisposable(Disposable disposable) {
        disposableList.add(disposable);
    }

    protected void dispose(Disposable disposable) {
        disposableList.remove(disposable);
        disposable.dispose();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableList.clear();
    }
}
