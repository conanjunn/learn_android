package com.xy.learn_android.ui.bli;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xy.learn_android.R;



public class BliActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bli);

        RecyclerView recycleV = findViewById(R.id.recycle);
        recycleV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        BliDataSource.ItemViewModel itemViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BliDataSource.ItemViewModel.class);

        BliAdapter adapter = new BliAdapter(this);

        recycleV.setAdapter(adapter);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<String>>() {
            @Override
            public void onChanged(@Nullable PagedList<String> list) {
                adapter.submitList(list);
            }
        });
    }
}