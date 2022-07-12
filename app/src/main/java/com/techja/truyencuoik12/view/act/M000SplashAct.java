package com.techja.truyencuoik12.view.act;

import android.content.Intent;
import android.os.Handler;

import com.techja.truyencuoik12.databinding.M000SplashActBinding;
import com.techja.truyencuoik12.view.base.BaseAct;
import com.techja.truyencuoik12.viewmodel.CommonVM;

public class M000SplashAct extends BaseAct<M000SplashActBinding, CommonVM> {
    @Override
    protected Class<CommonVM> initClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, M001MainAct.class));
        }, 2000);
    }

    @Override
    protected M000SplashActBinding initViewBinding() {
        return M000SplashActBinding.inflate(getLayoutInflater());
    }
}
