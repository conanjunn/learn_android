package com.xy.learn_android.ui.recyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xy.learn_android.R;

import java.util.ArrayList;
import java.util.List;

public class RcrViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcr_view);

        List<Character> characterList = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            characterList.add(c);
        }

        LetterAdapter mLetterAdapter = new LetterAdapter(characterList);
        RecyclerView letterReView = findViewById(R.id.rcr);
        letterReView.setAdapter(mLetterAdapter);
        letterReView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    private static class LetterAdapter extends RecyclerView.Adapter<VH> {

        private final List<Character> dataList;

        public LetterAdapter(List<Character> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 指定用哪个xml布局。事件监听需写在这里
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            // 当此item出现在视图里时，用什么数据填充此视图。
            // 此方法在滚动时会多次调用，且holder参数有可能是之前已经用过的
            Character c = dataList.get(position);
            holder.tv1.setText(c.toString());
            holder.tv2.setText(String.valueOf(Integer.valueOf(c)));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    private static class VH extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        // ViewHolder 用于缓存适配器里onCreateViewHolder方法return的视图
        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }

}