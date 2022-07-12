package com.techja.truyencuoik12.view.base;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.techja.truyencuoik12.OnActionCallBack;
import com.techja.truyencuoik12.viewmodel.BaseVM;

public abstract class BaseAct<T extends ViewBinding, M extends BaseVM>
        extends AppCompatActivity implements View.OnClickListener, OnActionCallBack {
    protected T binding;
    protected M viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(initClassVM());
        viewModel.setCallBack(this);

        initViews();
    }

    protected abstract Class<M> initClassVM();

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        //do nothing
    }

    @Override
    public void callBack(String key, Object data) {
        //do nothing
    }
}
