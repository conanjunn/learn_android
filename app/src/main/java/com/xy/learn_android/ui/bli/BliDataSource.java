package com.xy.learn_android.ui.bli;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BliDataSource {

    public static class ItemDataSource extends PageKeyedDataSource<Integer, String> {
        private final OkHttpClient client = new OkHttpClient();

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, String> callback) {
            String response = null;
            try {
                response = run("https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?visitor_uid=4166389&host_uid=412865440&offset_dynamic_id=0&need_top=1&platform=web");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            ResData grade = gson.fromJson(response, ResData.class);

            ArrayList<String> list = new ArrayList<>();
            ArrayList<ResData.ResItem> a = grade.getData().getCards();
            a.forEach(new Consumer<ResData.ResItem>() {
                @Override
                public void accept(ResData.ResItem resItem) {
                    ResData.Card crd = gson.fromJson(resItem.getCard(), ResData.Card.class);
                    ArrayList<ResData.Card.Img> b = crd.getItem().getPictures();
                    if (b != null) {
                        b.forEach(new Consumer<ResData.Card.Img>() {
                            @Override
                            public void accept(ResData.Card.Img img) {
                                list.add(img.getImg_src());
                            }
                        });
                    }
                }
            });


            callback.onResult(list, null, null);

        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, String> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, String> callback) {

        }

        private String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }

    public static class ItemDataFactory extends DataSource.Factory<Integer, String> {
        @NonNull
        @Override
        public DataSource<Integer, String> create() {
            return new ItemDataSource();
        }
    }

    public static class ItemViewModel extends ViewModel {
        LiveData<PagedList<String>> itemPagedList;

        public ItemViewModel() {
            ItemDataFactory itemDataSourceFactory = new ItemDataFactory();

            PagedList.Config pagedListConfig =
                    (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(2).build();

            itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, pagedListConfig))
                    .build();
        }
    }
}
