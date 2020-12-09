package com.xy.learn_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xy.learn_android.ui.dataSourceDemo.DataSourceDemoActivity;
import com.xy.learn_android.ui.listview.ListviewActivity;
import com.xy.learn_android.ui.recyclerView.ListAdapterActivity;
import com.xy.learn_android.ui.recyclerView.Rcr2Activity;
import com.xy.learn_android.ui.recyclerView.RcrViewActivity;
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

        Button btn3 = findViewById(R.id.bindingData2);
        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bindData2Activity.class);
                startActivity(intent);
            }
        });

        Button btn1 = findViewById(R.id.recycle1);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RcrViewActivity.class);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.recycle2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rcr2Activity.class);
                startActivity(intent);
            }
        });

        Button btn4 = findViewById(R.id.recycle3);
        btn4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListAdapterActivity.class);
                startActivity(intent);
            }
        });

        Button btn5 = findViewById(R.id.dataSource);
        btn5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataSourceDemoActivity.class);
                startActivity(intent);
            }
        });
    }
}