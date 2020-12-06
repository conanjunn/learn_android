package com.xy.learn_android.ui.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xy.learn_android.R;

import java.util.ArrayList;

public class Rcr2Activity extends AppCompatActivity {

    private ItemAdapter mItemAdapter;
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

        mItemAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(mItemAdapter);

    }


    public static class Model1 extends ViewModel {

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

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        private ViewDataBinding binding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycle_bind_item, viewGroup, false);
            ItemViewHolder holder = new ItemViewHolder(binding);
            return holder;
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