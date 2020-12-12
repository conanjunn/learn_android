package com.xy.learn_android.ui.bli;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xy.learn_android.R;

public class BliAdapter extends PagedListAdapter<String, BliAdapter.VH> {
    private final Context mCtx;
    private final  RequestManager glideManager;

    public BliAdapter(Context mCtx) {
        super(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.mCtx = mCtx;
        glideManager = Glide.with(this.mCtx);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mCtx).inflate(R.layout.bli_item, parent, false);
        return new VH(view, view.findViewById(R.id.img), view.findViewById(R.id.txt));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String img = getItem(position);
        glideManager.load(img).placeholder(new ColorDrawable(Color.GRAY)).into(holder.getImg());
    }

    public static class VH extends RecyclerView.ViewHolder {
        private final ImageView img;

        public ImageView getImg() {
            return img;
        }

        public TextView getTxt() {
            return txt;
        }

        private final TextView txt;

        public VH(@NonNull View itemView, ImageView img, TextView txt) {
            super(itemView);
            this.img = img;
            this.txt = txt;
        }
    }
}
