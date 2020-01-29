package com.example.fceytentusmanos.CarreraDetalle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarreraDetalleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CarreraDetalleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Carreras fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
