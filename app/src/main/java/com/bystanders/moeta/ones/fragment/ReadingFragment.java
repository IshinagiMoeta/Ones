package com.bystanders.moeta.ones.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.activity.ReadingBroadCastActivity;
import com.bystanders.moeta.ones.adapter.ReadingBroadcastAdapter;
import com.bystanders.moeta.ones.adapter.ReadingFragmentAdapter;
import com.bystanders.moeta.ones.bean.ReadingBroadCastBean;
import com.bystanders.moeta.ones.bean.ReadingContentBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 阅读页面的Fragment
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ReadingBroadCastBean broadCastBean;
    List<ReadingBroadCastBean.DataBean> broadBeanList;
    AutoScrollViewPager boardcastViewPager;
    LinearLayout layout_point_container;

    ReadingContentBean contentBean;
    List<Fragment> fragmentList = new ArrayList<>();
    FragmentManager fragmentManager;
    ViewPager viewPager;
    ReadingFragmentAdapter readingFragmentAdapter;

    boolean flag = false;

    /**
     * 用来处理耗时操作后得到的数据来更新UI
     * 0：下载广告轮播图片，执行广告轮播
     * 1：下载阅读的内容，加载内容ViewPager
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                broadCastHandler(msg);
            } else if (msg.what == 1) {
                readingContentHandler(msg);
            }
        }
    };

    public ReadingFragment() {
        // Required empty public constructor
    }

    /**
     * ReadingFragment显示才加载
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
        } else {
            if (broadBeanList == null) {
                OKHttpUtils.startOkhttp(handler, 0, Constants.READING.READING_AD_PATH);
            } else {
            }
            if (contentBean == null) {
                OKHttpUtils.startOkhttp(handler, 1, Constants.READING.READING_CONTENT_PATH);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 绑定各种控件
     * 初始化 fragmentManager
     * 初始化 readingFragmentAdapter
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        boardcastViewPager = (AutoScrollViewPager) view.findViewById(R.id.reading_broadcast_vp);
        layout_point_container = (LinearLayout) view.findViewById(R.id.reading_broadcast_ponit);
        viewPager = (ViewPager) view.findViewById(R.id.reading_vp);
        fragmentManager = getFragmentManager();
        readingFragmentAdapter = new ReadingFragmentAdapter(fragmentManager, fragmentList);
        return view;
    }

    /**
     * @param broadBeanList:表示图片地址的数组集合
     * @return :返回的是添加的图片页数以及内容
     */
    public List<View> initAd(List<ReadingBroadCastBean.DataBean> broadBeanList) {
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < broadBeanList.size(); i++) {
            ImageView img = new ImageView(getActivity());
            img.setId(i);
            //设置图片的点击事件的监听
            img.setOnClickListener(ReadingFragment.this);
            Picasso.with(getActivity()).load(broadBeanList.get(i).getCover())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(img);
            //自己绘制导航点，添加进UI
            ImageView img_point = new ImageView(getActivity());
            img_point.setId(i);
            img_point.setPadding(15, 0, 15, 0);
            if (i == 0) {
                img_point.setImageResource(R.drawable.point_selected);
            } else {
                img_point.setImageResource(R.drawable.point_default);
            }
            layout_point_container.addView(img_point);
        }
        return viewList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 广告轮播的滚动时间监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        int count = layout_point_container.getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView img = (ImageView) layout_point_container.getChildAt(i);
            if (i == position) {
                img.setImageResource(R.drawable.point_selected);
            } else {
                img.setImageResource(R.drawable.point_default);
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 设置广告轮播
     *
     * @param msg
     */
    public void broadCastHandler(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        broadCastBean = gson.fromJson(str, ReadingBroadCastBean.class);
        broadBeanList = broadCastBean.getData();

        List<View> viewList = initAd(broadBeanList);
        ReadingBroadcastAdapter adapter = new ReadingBroadcastAdapter(viewList);
        boardcastViewPager.setAdapter(adapter);
        boardcastViewPager.addOnPageChangeListener(ReadingFragment.this);

        boardcastViewPager.startAutoScroll();
        boardcastViewPager.setCycle(true);
        boardcastViewPager.setInterval(4000);
        boardcastViewPager.setDirection(AutoScrollViewPager.RIGHT);
        boardcastViewPager.setStopScrollWhenTouch(true);
    }

    /**
     * 设置内容fragment，并传输数据
     *
     * @param msg
     */
    public void readingContentHandler(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        contentBean = gson.fromJson(str, ReadingContentBean.class);
        ReadingContentBean.DataBean data = contentBean.getData();
        for (int i = 0; i < data.getEssay().size(); i++) {

            ReadingContentBean.DataBean.EssayBean essayBean = data.getEssay().get(i);
            ReadingContentBean.DataBean.SerialBean serialBean = data.getSerial().get(i);
            ReadingContentBean.DataBean.QuestionBean questionBean = data.getQuestion().get(i);

            ReadingContentFragment r = new ReadingContentFragment();
            Bundle b = new Bundle();
            b.putParcelable("essayBean", essayBean);
            b.putParcelable("serialBean", serialBean);
            b.putParcelable("questionBean", questionBean);
            r.setArguments(b);
            fragmentList.add(r);
        }
        viewPager.setAdapter(readingFragmentAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = true;
    }


    /**
     * 广告轮播的图片的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int pos = v.getId();
        String title = broadBeanList.get(pos).getTitle();
        String bgcolor = broadBeanList.get(pos).getBgcolor();
        String bottom_text = broadBeanList.get(pos).getBottom_text();
        String id = broadBeanList.get(pos).getId();
        String picUrl = broadBeanList.get(pos).getCover();

        Intent intent = new Intent(getActivity(), ReadingBroadCastActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("bgcolor", bgcolor);
        intent.putExtra("bottom_text", bottom_text);
        intent.putExtra("picUrl", picUrl);
        intent.putExtra("id", id);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.no_anim);
    }
}
