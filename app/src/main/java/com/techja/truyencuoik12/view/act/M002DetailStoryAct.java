package com.techja.truyencuoik12.view.act;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.techja.truyencuoik12.App;
import com.techja.truyencuoik12.R;
import com.techja.truyencuoik12.databinding.M002DetailStoryBinding;
import com.techja.truyencuoik12.model.StoryModel;
import com.techja.truyencuoik12.view.adapter.DetailStoryAdapter;
import com.techja.truyencuoik12.view.base.BaseAct;
import com.techja.truyencuoik12.viewmodel.CommonVM;

public class M002DetailStoryAct extends BaseAct<M002DetailStoryBinding, CommonVM> {
    private static final int LEVEL_BACK_ICON = 1;

    @Override
    protected Class<CommonVM> initClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        binding.actionBar.tvIndex.setVisibility(View.VISIBLE);
        binding.actionBar.vIndex.setVisibility(View.VISIBLE);

        binding.actionBar.ivMenu.setImageLevel(LEVEL_BACK_ICON);
        binding.actionBar.ivMenu.setOnClickListener(this);
        binding.actionBar.tvName.setText(App.getInstance().getStorage().m001TopicName);

        DetailStoryAdapter adapter = new DetailStoryAdapter(App.getInstance().getStorage().m001ListStory, this);
        binding.vpStory.setAdapter(adapter);
        StoryModel story = App.getInstance().getStorage().m001Story;
        int index = App.getInstance().getStorage().m001ListStory.indexOf(story);
        int size = App.getInstance().getStorage().m001ListStory.size();
        binding.vpStory.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int pos) {
                binding.actionBar.tvIndex.setText(String.format("%s/%s", pos+1, size));
            }
        });
        binding.vpStory.setCurrentItem(index, true);
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_menu) {
            finish();
        }
    }

    @Override
    protected M002DetailStoryBinding initViewBinding() {
        return M002DetailStoryBinding.inflate(getLayoutInflater());
    }
}
