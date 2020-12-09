package com.xy.learn_android.ui.dataSourceDemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xy.learn_android.R;

import java.util.ArrayList;

public class DataSourceDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_source_demo);

        //setting up recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this);

        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);
    }

    public static class Item {
        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;
    }


    public static class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

        private final Context mCtx;

        ItemAdapter(Context mCtx) {
            super(DIFF_CALLBACK);
            this.mCtx = mCtx;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item2, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Item item = getItem(position);
            if (item != null) {
                holder.textView.setText(String.valueOf(item.id));
                holder.textView2.setText(item.name);
            } else {
                Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
            }
        }

        private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
                new DiffUtil.ItemCallback<Item>() {
                    @Override
                    public boolean areItemsTheSame(Item oldItem, Item newItem) {
                        return oldItem.id == newItem.id;
                    }


                    @Override
                    public boolean areContentsTheSame(Item oldItem, Item newItem) {
                        return oldItem.name.equals(newItem.name);
                    }
                };


        static class ItemViewHolder extends RecyclerView.ViewHolder {

            TextView textView;
            TextView textView2;

            public ItemViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv1);
                textView2 = itemView.findViewById(R.id.tv2);
            }
        }
    }


    public static class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

        private final int key = 0;

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {
            ArrayList<Item> ret = new ArrayList<>();
            ret.add(new Item(1, "a"));
            ret.add(new Item(2, "b"));
            callback.onResult(ret, null, key);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
            ArrayList<Item> ret = new ArrayList<>();
            ret.add(new Item(0, "0"));
            callback.onResult(ret, key - 1);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
            ArrayList<Item> ret = new ArrayList<>();
            ret.add(new Item(3, "c"));
            callback.onResult(ret, key + 1);
        }
    }

    public static class ItemDataSourceFactory extends DataSource.Factory<Integer, Item> {
        public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
            return itemLiveDataSource;
        }

        private final MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

        @NonNull
        @Override
        public DataSource<Integer, Item> create() {
            ItemDataSource itemDataSource = new ItemDataSource();
            itemLiveDataSource.postValue(itemDataSource);
            return itemDataSource;
        }
    }

    public static class ItemViewModel extends ViewModel {
        LiveData<PagedList<Item>> itemPagedList;
        LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

        //constructor
        public ItemViewModel() {
            //getting our data source factory
            ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

            //getting the live data source from data source factory
            liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

            //Getting PagedList config
            PagedList.Config pagedListConfig =
                    (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(2).build();

            //Building the paged list
            itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, pagedListConfig))
                    .build();
        }
    }
}
