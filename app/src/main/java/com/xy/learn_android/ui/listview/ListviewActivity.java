package com.xy.learn_android.ui.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xy.learn_android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        // ArrayAdapter
        ListView listview = findViewById(R.id.mylistview);
        List<String> listdata = new ArrayList<>();
        listdata.add("上海");
        listdata.add("北京");
        listdata.add("天津");
        listdata.add("江苏");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, listdata);//listdata和str均可
        listview.setAdapter(arrayAdapter);

        //SimpleAdapter
        ListView listview1 = findViewById(R.id.mylistview1);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, putData(), R.layout.list_item2,
                new String[]{"name", "age"}, new int[]{R.id.tv1, R.id.tv2});
        listview1.setAdapter(simpleAdapter);

        //BaseAdapter
        ListView listview2 = findViewById(R.id.mylistview2);
        MyAdapter myAdapter = new MyAdapter(this, putData());
        listview2.setAdapter(myAdapter);
    }

    private List<Map<String, Object>> putData() {

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("age", "19岁");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "李四");
        map2.put("age", "17岁");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "王五");
        map3.put("age", "17岁");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }


    private static class MyAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;
        private final List<Map<String, Object>> list;


        public MyAdapter(Context context, List<Map<String, Object>> list) {
            this.mInflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item2, null);
                holder.name = convertView.findViewById(R.id.tv1);
                holder.age = convertView.findViewById(R.id.tv2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText((String) list.get(position).get("name"));
            holder.age.setText((String) list.get(position).get("age"));

            return convertView;
        }


        public final static class ViewHolder {
            public TextView name;
            public TextView age;
        }

    }

}