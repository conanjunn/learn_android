package com.xy.learn_android.ui.databinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModel2 extends ViewModel {

    public void setCurrentName(String str) {
        currentName.setValue(str);
    }

    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<>();
            setCurrentName("eee");
        }
        return currentName;
    }

}
