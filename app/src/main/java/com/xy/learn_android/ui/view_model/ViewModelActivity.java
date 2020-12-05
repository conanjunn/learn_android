package com.xy.learn_android.ui.view_model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.xy.learn_android.R;

public class ViewModelActivity extends AppCompatActivity {
    private ViewModelModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        TextView txt = findViewById(R.id.txt);

        model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelModel.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                txt.setText(newName);
            }
        };

        model.getCurrentName().observe(this, nameObserver);

        model.getCurrentName().setValue("abc");
    }
}