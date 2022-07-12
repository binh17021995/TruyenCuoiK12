package com.techja.truyencuoik12.viewmodel;

import androidx.lifecycle.ViewModel;

import com.techja.truyencuoik12.OnActionCallBack;

public abstract class BaseVM extends ViewModel {
    protected OnActionCallBack listener;

    public void setCallBack(OnActionCallBack callBack) {
        this.listener = callBack;
    }
}
