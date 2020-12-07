package com.xy.learn_android.ui.databinding;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.xy.learn_android.R;
import com.xy.learn_android.databinding.ActivityBindData2Binding;

public class bindData2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_data2);

        ViewModel2 model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModel2.class);

        // ActivityBindData2Binding是由xml文件名自动生成的
        ActivityBindData2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_data2);
        binding.setData(model);
        binding.setLifecycleOwner(this);
    }
}