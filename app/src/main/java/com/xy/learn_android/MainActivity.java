package com.xy.learn_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xy.learn_android.ui.listview.ListviewActivity;
import com.xy.learn_android.ui.view_model.ViewModelActivity;
import com.xy.learn_android.ui.databinding.bindDataActivity;
import com.xy.learn_android.ui.databinding.bindData2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewModelBtn = findViewById(R.id.view_model);
        viewModelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewModelActivity.class);
                startActivity(intent);
            }
        });

        Button listviewBtn = findViewById(R.id.listview);
        listviewBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListviewActivity.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.bindingData);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bindDataActivity.class);
                startActivity(intent);
            }
        });

        Button btn1 = findViewById(R.id.bindingData2);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bindData2Activity.class);
                startActivity(intent);
            }
        });
    }
}