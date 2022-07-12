package com.techja.truyencuoik12.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.truyencuoik12.OnActionCallBack;
import com.techja.truyencuoik12.R;
import com.techja.truyencuoik12.model.StoryModel;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {
    private static final String KEY_SHOW_DETAIL = "KEY_SHOW_DETAIL";
    private final List<StoryModel> listData;
    private final Context context;
    private final OnActionCallBack callBack;

    public StoryAdapter(List<StoryModel> listData, Context context, OnActionCallBack callBack) {
        this.listData = listData;
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, int position) {
        StoryModel data = listData.get(position);
        holder.tvName.setText(data.getName());
        holder.tvName.setTag(data);
        holder.lnStory.setBackgroundResource(position % 2 == 0 ? R.color.white : R.color.purple_100);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        LinearLayout lnStory;

        public StoryHolder(@NonNull View itemView) {
            super(itemView);
            lnStory = itemView.findViewById(R.id.ln_story);
            tvName = itemView.findViewById(R.id.tv_story_name);
            itemView.setOnClickListener(view -> {
                view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                StoryModel data = (StoryModel) tvName.getTag();
                //Toast.makeText(context, data.getName() + "\n\n" + data.getContent(), Toast.LENGTH_LONG).show();
                callBack.callBack(KEY_SHOW_DETAIL, data);
            });
        }
    }
}
