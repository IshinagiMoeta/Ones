package com.bystanders.moeta.ones.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.SerialBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.bystanders.moeta.ones.utils.TimeUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class SerialActivity extends AppCompatActivity implements View.OnClickListener {

    SerialBean serialBean;
    AnimationDrawable drawable;

    RelativeLayout relativeLayout;
    TextView loading_img;
    String content_Path;

    ImageView img_userimg_1;
    ImageView img_userimg_2;
    ImageView img_close;

    CheckBox cb_good;
    CheckBox cb_comment;
    CheckBox cb_share;

    TextView tv_username_1;
    TextView tv_username_2;
    TextView tv_data;
    TextView tv_title;
    TextView tv_content;
    TextView tv_editer;
    TextView tv_author;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    updataSerial(msg);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial);

        img_close = (ImageView) findViewById(R.id.serial_close);
        img_userimg_1 = (ImageView) findViewById(R.id.serial_author_img1);
        img_userimg_2 = (ImageView) findViewById(R.id.serial_author_img2);

        cb_good = (CheckBox) findViewById(R.id.serial_laud);
        cb_comment = (CheckBox) findViewById(R.id.serial_comment);
        cb_share = (CheckBox) findViewById(R.id.serial_share);

        tv_username_1 = (TextView) findViewById(R.id.serial_author_name);
        tv_username_2 = (TextView) findViewById(R.id.serial_author_name2);
        tv_data = (TextView) findViewById(R.id.question_data);
        tv_title = (TextView) findViewById(R.id.question_title);
        tv_content = (TextView) findViewById(R.id.question_answercontent);
        tv_editer = (TextView) findViewById(R.id.serial_editer);
        tv_author = (TextView) findViewById(R.id.serial_author_indent);

        relativeLayout = (RelativeLayout) findViewById(R.id.serial_context);
        loading_img = (TextView) findViewById(R.id.serial_loading);
        drawable = (AnimationDrawable) loading_img.getBackground();
        drawable.start();
        relativeLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        content_Path = String.format(Constants.READING.SERIAL_CONTENT, id);
        OKHttpUtils.startOkhttp(handler, 0, content_Path);

        img_close.setOnClickListener(this);
        cb_good.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.serial_close:
                finish();
                break;
            case R.id.serial_laud:
                int num = Integer.parseInt((String) cb_good.getText());
                if (cb_good.isChecked()) {
                    num = num + 1;
                } else {
                    num = num - 1;
                }
                cb_good.setText(String.valueOf(num));
                break;
        }
    }

    public void updataSerial(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        serialBean = gson.fromJson(str, SerialBean.class);
        SerialBean.DataBean data = serialBean.getData();

        tv_username_1.setText(data.getAuthor().getUser_name());
        String date = TimeUtils.time2data(data.getLast_update_date());
        tv_data.setText(date);
        tv_title.setText(data.getTitle());
        tv_content.setText(Html.fromHtml(data.getContent()));
        tv_editer.setText(data.getCharge_edt());

        tv_username_2.setText(data.getAuthor().getUser_name());
        tv_author.setText(data.getAuthor().getDesc());
        String imgPic = data.getAuthor().getWeb_url();
        Picasso.with(SerialActivity.this).load(imgPic).resize(100, 100).into(img_userimg_1);
        Picasso.with(SerialActivity.this).load(imgPic).resize(100, 100).into(img_userimg_2);

        drawable.stop();
        loading_img.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);

        cb_good.setText(String.valueOf(data.getPraisenum()));
        cb_share.setText(String.valueOf(data.getSharenum()));
        cb_comment.setText(String.valueOf(data.getCommentnum()));
    }
}
