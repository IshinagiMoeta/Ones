package com.bystanders.moeta.ones.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.adapter.HomeFragmentAdapter;
import com.bystanders.moeta.ones.bean.HomeBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.OKHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Home界面的Fragment
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    FragmentManager fragmentManager;
    List<String> stringList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    HomeFragmentAdapter homeFragmentAdapter;
    ViewPager viewPager;


    /**
     * 耗时操作后，更新UI
     * 0 ：获得JSON解析后得到的ViewPager总数，先加载布局占位
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                getHomeView(msg);
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * 创建的时候就执行Okhttp下载ViewPager的数量，用于占位
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OKHttpUtils.startOkhttp(handler, 0, Constants.HOME.HOME_PATH);
    }

    /**
     * 绑定各种控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.home_vp);
        fragmentManager = getFragmentManager();
        homeFragmentAdapter = new HomeFragmentAdapter(fragmentManager, stringList, fragmentList);
        return view;
    }

    /**
     * 解析下载的str得到pager数量
     * 添加页面占位，设置viewpager
     *
     * @param msg
     */
    public void getHomeView(Message msg) {
        String str = (String) msg.obj;
        try {
            JSONObject object = new JSONObject(str);
            JSONArray data = object.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                String vol = (String) data.get(i);
                stringList.add(vol);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < stringList.size(); i++) {
            HomeContentFragment f = new HomeContentFragment();
            Bundle b = new Bundle();
            b.putString("vol", stringList.get(i));
            f.setArguments(b);
            fragmentList.add(f);
        }
        viewPager.setAdapter(homeFragmentAdapter);
    }
}
