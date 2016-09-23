package com.bystanders.moeta.ones.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.adapter.BroadCastActivityListAdatper;
import com.bystanders.moeta.ones.bean.BroadCastBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 广告轮播点击之后弹出的广告页
 */
public class ReadingBroadCastActivity extends AppCompatActivity implements View.OnClickListener {

    String id;
    String bottom_text;
    String picUrl;
    LinearLayout linearLayout;
    ImageView imageView;
    ListView listView;
    TextView textView;
    BroadCastBean broadCastBean;


    /**
     * 接受到网络请求进行异步操作，并且用得到的结果来更新UI
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String str = (String) msg.obj;
                Gson gson = GsonUtils.getGson();
                broadCastBean = gson.fromJson(str, BroadCastBean.class);
                List<BroadCastBean.DataBean> data = broadCastBean.getData();
                BroadCastActivityListAdatper activityListAdatpe =
                        new BroadCastActivityListAdatper(data, ReadingBroadCastActivity.this);
                listView.setAdapter(activityListAdatpe);
            }
        }
    };

    /**
     * 加载广告页的控件
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_broad_cast);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String bgcolor = intent.getStringExtra("bgcolor");
        bottom_text = intent.getStringExtra("bottom_text");
        id = intent.getStringExtra("id");
        picUrl = intent.getStringExtra("picUrl");

        linearLayout = (LinearLayout) findViewById(R.id.broadcast_background);
        imageView = (ImageView) findViewById(R.id.broadcast_close);
        listView = (ListView) findViewById(R.id.broadcast_list);
        textView = (TextView) findViewById(R.id.broadcast_title);

        linearLayout.setBackgroundColor(Color.parseColor(bgcolor));
        imageView.setOnClickListener(this);
        textView.setText(title);
    }

    /**
     * 向页面中加载基本数据，并且发送网络请求下载详细数据
     */
    @Override
    protected void onStart() {
        super.onStart();
        String path = String.format(Constants.READING.READING_AD_DETIL_PATH, id);
        OKHttpUtils.startOkhttp(handler, 0, path);
        View view = LayoutInflater.from(this).inflate(R.layout.broadcast_list_footer, null);
        TextView tv_footer = (TextView) view.findViewById(R.id.broadcast_list_footer_text);
        tv_footer.setText(bottom_text);
        listView.addFooterView(view);
        ImageView img = new ImageView(this);
        Picasso.with(this).load(picUrl)
                .resize(1500, 680)
                .placeholder(R.mipmap.ic_launcher)
                .into(img);
        listView.addFooterView(img);
    }

    /**
     * boardcastViewPager是关闭按钮的ID，关闭页面
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.broadcast_close:
                finish();
                break;
        }
    }

    /**
     * 对退出此Activity设置动画，载入动画，退出动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_down);
    }
}
