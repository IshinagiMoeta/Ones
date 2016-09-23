package com.bystanders.moeta.ones.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Home界面的ViewPager的Adapter
 * Created by Ishinagi_moeta on 2016/9/19.
 */
public class HomeFragmentAdapter extends FragmentStatePagerAdapter {

    List<String> stringList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    public HomeFragmentAdapter(FragmentManager fm, List<String> stringList, List<Fragment> fragmentList) {
        super(fm);
        this.stringList = stringList;
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
