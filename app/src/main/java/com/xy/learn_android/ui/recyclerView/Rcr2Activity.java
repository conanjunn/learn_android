package com.xy.learn_android.ui.recyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xy.learn_android.R;
import com.xy.learn_android.databinding.RecycleBindItemBinding;

import java.util.ArrayList;

public class Rcr2Activity extends AppCompatActivity {
    private ArrayList<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcr2);

        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mItems.add(String.valueOf(i));
        }

        RecyclerView mRecyclerView = findViewById(R.id.rcr2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ItemAdapter mItemAdapter = new ItemAdapter();
//        ItemAdapter2 mItemAdapter = new ItemAdapter2();
        mRecyclerView.setAdapter(mItemAdapter);

    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        public RecycleBindItemBinding getBinding() {
            return binding;
        }

        public void setBinding(RecycleBindItemBinding binding) {
            this.binding = binding;
        }

        private RecycleBindItemBinding binding;

        public ItemViewHolder(RecycleBindItemBinding binding) {
            super(binding.getRoot());
            setBinding(binding);
        }
    }

    protected static class ItemViewHolder2 extends RecyclerView.ViewHolder {
        public ItemViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }


    private class ItemAdapter2 extends RecyclerView.Adapter<ItemViewHolder2> {

        @NonNull
        @Override
        public ItemViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            RecycleBindItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycle_bind_item, viewGroup, false);
            return new ItemViewHolder2(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ItemViewHolder2 viewHolder, int i) {
            RecycleBindItemBinding bind = DataBindingUtil.bind(viewHolder.itemView);
            if (bind != null) {
                bind.setData(mItems.get(i));
                bind.executePendingBindings();
            }
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            RecycleBindItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycle_bind_item, viewGroup, false);
            return new ItemViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder viewHolder, int i) {
            viewHolder.getBinding().setData(mItems.get(i));
            viewHolder.getBinding().executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}