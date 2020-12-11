package com.xy.learn_android.ui.bli;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchList extends AsyncTask<String, String, String> {

    private final OkHttpClient client = new OkHttpClient();
    private FetchListCallback cb;

    public FetchList(FetchListCallback cb) {
        this.cb = cb;
    }


    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson gson = new Gson();
        ResData grade = gson.fromJson(s, ResData.class);

        ArrayList<String> list = new ArrayList<>();
        ArrayList<ResData.ResItem> a = grade.getData().getCards();
        for (int i = 0; i < a.size(); i++) {
            ResData.Card crd = gson.fromJson(a.get(i).getCard(), ResData.Card.class);
            ArrayList<ResData.Card.Img> b = crd.getItem().getPictures();
            for (int j = 0; j < b.size(); j++) {
                list.add(b.get(j).getImg_src());
            }
        }

        cb.callback(list);
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = null;
        try {
            response = run("https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?visitor_uid=4166389&host_uid=412865440&offset_dynamic_id=0&need_top=1&platform=web");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public interface FetchListCallback {
        public void callback(ArrayList<String> a);
    }
}