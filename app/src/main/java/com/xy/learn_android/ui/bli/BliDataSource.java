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

    public static class ItemDataSource extends PageKeyedDataSource<String, String> {
        private final OkHttpClient client = new OkHttpClient();
        private final String url = "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?visitor_uid=4166389&host_uid=412865440&offset_dynamic_id=%s&need_top=1&platform=web";

        @Override
        public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, String> callback) {
            RunRet ret = run("0");
            callback.onResult(ret.list, null, ret.next);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, String> callback) {
        }

        @Override
        public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, String> callback) {
            RunRet ret = run(params.key);
            callback.onResult(ret.list, ret.next);
        }

        private RunRet run(String param) {
            String response;
            Request request = new Request.Builder()
                    .url(String.format(url, param))
                    .build();

            try (Response resp = client.newCall(request).execute()) {
                response = resp.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            Gson gson = new Gson();
            ResData grade = gson.fromJson(response, ResData.class);

            ArrayList<String> list = new ArrayList<>();
            ArrayList<ResData.ResItem> a = grade.getData().getCards();

            Long nextId = grade.getData().getNext_offset();
            String nextOffset = String.valueOf(nextId);

            a.forEach(new Consumer<ResData.ResItem>() {
                @Override
                public void accept(ResData.ResItem resItem) {
                    ResData.Card crd = gson.fromJson(resItem.getCard(), ResData.Card.class);
                    ResData.Card.Pic c = crd.getItem();
                    if (c != null) {
                        ArrayList<ResData.Card.Img> b = c.getPictures();
                        if (b != null) {
                            b.forEach(new Consumer<ResData.Card.Img>() {
                                @Override
                                public void accept(ResData.Card.Img img) {
                                    list.add(img.getImg_src());
                                }
                            });
                        }
                    }
                }
            });
            return new RunRet(list, nextOffset);
        }
    }

    public static class RunRet {
        public RunRet(ArrayList<String> list, String next) {
            this.list = list;
            this.next = next;
        }

        public ArrayList<String> list;
        public String next;
    }

    public static class ItemDataFactory extends DataSource.Factory<String, String> {
        @NonNull
        @Override
        public DataSource<String, String> create() {
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
                            .setPageSize(5).build();

            itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, pagedListConfig))
                    .build();
        }
    }
}
