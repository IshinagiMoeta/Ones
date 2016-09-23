package com.bystanders.moeta.ones.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.fragment.HomeFragment;
import com.bystanders.moeta.ones.fragment.MoiveFragment;
import com.bystanders.moeta.ones.fragment.MusicFragment;
import com.bystanders.moeta.ones.fragment.ReadingFragment;

/**
 * Ones主界面，初始化四个基础的Fragment，并隐藏除了Home之外的Fragment
 */
public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView tv_title;
    FragmentManager fragmentManager;

    HomeFragment homeFragment;
    ReadingFragment readingFragment;
    MusicFragment musicFragment;
    MoiveFragment moiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.main_rg);
        tv_title = (TextView) findViewById(R.id.main_title);

        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        if (readingFragment == null) {
            readingFragment = new ReadingFragment();
        }
        if (musicFragment == null) {
            musicFragment = new MusicFragment();
        }
        if (moiveFragment == null) {
            moiveFragment = new MoiveFragment();
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.main_fragment, homeFragment);
        ft.add(R.id.main_fragment, readingFragment).hide(readingFragment);
        ft.add(R.id.main_fragment, musicFragment).hide(musicFragment);
        ft.add(R.id.main_fragment, moiveFragment).hide(moiveFragment);
        ft.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragment(ft);
                switch (checkedId) {
                    case R.id.main_rg_home:
                        ft.show(homeFragment);
                        tv_title.setText("");
                        tv_title.setTextSize(0);
                        tv_title.setBackgroundResource(R.mipmap.nav_title);
                        break;
                    case R.id.main_rg_reading:
                        ft.show(readingFragment);
                        tv_title.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_title.setTextSize(25);
                        tv_title.setText("阅读");
                        break;
                    case R.id.main_rg_music:
                        ft.show(musicFragment);
                        tv_title.setTextSize(25);
                        tv_title.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_title.setText("音乐");
                        break;
                    case R.id.main_rg_movie:
                        ft.show(moiveFragment);
                        tv_title.setTextSize(25);
                        tv_title.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tv_title.setText("电影");
                        break;
                }
                ft.commit();
            }
        });
    }

    /**
     * 隐藏所有的fragment，注意，没有执行commit操作
     */
    public void hideFragment(FragmentTransaction ft) {
        ft.hide(homeFragment);
        ft.hide(readingFragment);
        ft.hide(musicFragment);
        ft.hide(moiveFragment);
    }
}
