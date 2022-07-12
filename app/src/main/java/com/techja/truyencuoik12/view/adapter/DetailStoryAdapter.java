package com.techja.truyencuoik12.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.techja.truyencuoik12.R;
import com.techja.truyencuoik12.model.StoryModel;

import java.util.List;

public class DetailStoryAdapter extends PagerAdapter {
    private final List<StoryModel> listData;
    private final Context context;

    public DetailStoryAdapter(List<StoryModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewPager, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_story, viewPager, false);
        TextView tvName = view.findViewById(R.id.tv_story_name);
        TextView tvContent = view.findViewById(R.id.tv_story_content);
        StoryModel data = listData.get(position);

        tvName.setText(data.getName());
        tvContent.setText(data.getContent());
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object object) {
        viewPager.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
