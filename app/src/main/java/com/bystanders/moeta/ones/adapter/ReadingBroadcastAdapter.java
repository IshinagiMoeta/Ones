package com.bystanders.moeta.ones.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 阅读页面的广告轮播的ViewPager的Adapter
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class ReadingBroadcastAdapter extends PagerAdapter {
    List<View> viewList;

    public ReadingBroadcastAdapter(List<View> viewList) {
        this.viewList = viewList;
    }


    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化ViewPager容器中的页page
     *
     * @param container:代指的ViewPager
     * @param position:根据指定创建新的View
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //根据指定pos位置取出View
        View view = viewList.get(position);
        //把View添加到ViewGroup容器中
        container.addView(view);
        return view;
    }

    //根据指定位置把不需要的内容移除掉
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super调用父类，父类直接抛出异常让自己处理
        // super.destroyItem(container, position, object);
        container.removeView(viewList.get(position));
    }
}
