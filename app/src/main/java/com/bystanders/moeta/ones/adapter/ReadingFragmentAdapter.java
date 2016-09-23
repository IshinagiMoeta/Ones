package com.bystanders.moeta.ones.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 阅读页面的内容的ViewPager
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class ReadingFragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();

    public ReadingFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
