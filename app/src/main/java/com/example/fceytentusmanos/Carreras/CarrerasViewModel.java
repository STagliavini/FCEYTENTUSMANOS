package com.example.fceytentusmanos.Carreras;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarrerasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CarrerasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Carreras fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
