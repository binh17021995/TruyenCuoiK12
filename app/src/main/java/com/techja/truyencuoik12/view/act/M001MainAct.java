package com.techja.truyencuoik12.view.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;

import com.techja.truyencuoik12.App;
import com.techja.truyencuoik12.R;
import com.techja.truyencuoik12.databinding.M001MainActBinding;
import com.techja.truyencuoik12.model.StoryModel;
import com.techja.truyencuoik12.view.adapter.StoryAdapter;
import com.techja.truyencuoik12.view.base.BaseAct;
import com.techja.truyencuoik12.viewmodel.M001MainVM;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class M001MainAct extends BaseAct<M001MainActBinding, M001MainVM> {
    private static final String TAG = M001MainAct.class.getName();

    @SuppressLint("SetTextI18n")
    @Override
    protected void initViews() {
        binding.actionBar.ivMenu.setOnClickListener(this);

        initPhotoTopics();
        initStoryData("Con gái");
    }

    private void initStoryData(String storyFileName) {
        try {
            binding.actionBar.tvName.setText(storyFileName);
            InputStream in = getAssets().open("story/" + storyFileName + ".txt");
            viewModel.readStoryFiles(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StoryAdapter adapter = new StoryAdapter(viewModel.getListStory(), this, this);
        binding.rvStory.setAdapter(adapter);
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_menu) {
            openMenuBar();
        }
    }

    private void openMenuBar() {
        binding.drawer.openDrawer(GravityCompat.START);
    }

    @Override
    protected Class<M001MainVM> initClassVM() {
        return M001MainVM.class;
    }

    @Override
    protected M001MainActBinding initViewBinding() {
        return M001MainActBinding.inflate(getLayoutInflater());
    }

    private void initPhotoTopics() {
        AssetManager assetMgr = getAssets();
        try {
            String[] listFileName = assetMgr.list("photo/");
            Log.i(TAG, Arrays.toString(listFileName));

            binding.include.lnTopic.removeAllViews();
            for (String photoPath : listFileName) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_topic, null);
                TextView tvName = itemView.findViewById(R.id.tv_photo);
                ImageView ivPhoto = itemView.findViewById(R.id.iv_photo);

                InputStream in = assetMgr.open("photo/" + photoPath);
                Bitmap img = BitmapFactory.decodeStream(in);
                ivPhoto.setImageBitmap(img);
                tvName.setText(photoPath.replace(".png", ""));

                itemView.setTag(tvName.getText().toString());
                itemView.setOnClickListener(view -> {
                    view.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
                    doClickTopic(((String) view.getTag()));
                });
                binding.include.lnTopic.addView(itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callBack(String key, Object data) {
        App.getInstance().getStorage().m001TopicName = binding.actionBar.tvName.getText().toString();
        App.getInstance().getStorage().m001Story = (StoryModel) data;
        App.getInstance().getStorage().m001ListStory = viewModel.getListStory();

        startActivity(new Intent(this, M002DetailStoryAct.class));
    }

    private void doClickTopic(String topicName) {
        initStoryData(topicName);
        binding.drawer.closeDrawers();
    }

}