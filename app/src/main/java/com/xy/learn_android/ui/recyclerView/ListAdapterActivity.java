package com.xy.learn_android.ui.recyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xy.learn_android.R;
import com.xy.learn_android.databinding.RecycleBindItemBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ListAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter);

        ArrayList<User> list = new ArrayList<>();
        list.add(new User("aaa"));
        list.add(new User("bbb"));

        RecyclerView mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.submitList(list);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> list2 = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    list2.add(list.get(i));
                }
                list2.add(new User("ccc"));
                list2.add(new User("ddd"));
                adapter.submitList(list2);
            }
        });
    }

    private static class User {
        public String username;

        public User(String username) {
            this.username = username;
        }
    }


    private static class MyAdapter extends ListAdapter<User, Holder> {
        protected MyAdapter() {
            super(new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return Objects.equals(oldItem.username, newItem.username);
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return Objects.equals(oldItem.username, newItem.username);
                }
            });
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  // viewType由getItemViewType提供
            Log.i("aaaa", String.valueOf(viewType));
            RecycleBindItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycle_bind_item, parent, false);
            return new Holder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.binding.setData(getItem(position).username);
            holder.binding.executePendingBindings();
        }

        @Override
        public int getItemViewType(int position) {
            return position * 2;
        }
    }

    private static class Holder extends RecyclerView.ViewHolder {
        public RecycleBindItemBinding binding;

        public Holder(@NonNull RecycleBindItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}