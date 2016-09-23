package com.bystanders.moeta.ones.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * 懒加载的父类
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }
}
