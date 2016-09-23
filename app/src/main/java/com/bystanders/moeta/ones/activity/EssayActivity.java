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
import com.bystanders.moeta.ones.bean.EssayContent;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.service.DiscService;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.bystanders.moeta.ones.utils.TimeUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class EssayActivity extends AppCompatActivity implements View.OnClickListener {

    final static public int START = 0;
    final static public int PAUSE = 1;
    final static public int RESUME = 2;
    final static public int STOP = 3;

    boolean noplay = true;

    EssayContent essayContent;
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
    CheckBox cb_play;
    CheckBox cb_disc;


    TextView tv_username_1;
    TextView tv_username_2;
    TextView tv_data;
    TextView tv_title;
    TextView tv_content;
    TextView tv_editer;
    TextView tv_author;
    TextView tv_weibo;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    updataEssay(msg);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

        img_close = (ImageView) findViewById(R.id.essay_close);
        img_userimg_1 = (ImageView) findViewById(R.id.serial_author_img1);
        img_userimg_2 = (ImageView) findViewById(R.id.serial_author_img2);

        cb_disc = (CheckBox) findViewById(R.id.essay_disc);
        cb_good = (CheckBox) findViewById(R.id.essay_laud);
        cb_comment = (CheckBox) findViewById(R.id.essay_comment);
        cb_share = (CheckBox) findViewById(R.id.essay_share);
        cb_play = (CheckBox) findViewById(R.id.essay_play);

        tv_username_1 = (TextView) findViewById(R.id.serial_author_name);
        tv_username_2 = (TextView) findViewById(R.id.serial_author_name2);
        tv_data = (TextView) findViewById(R.id.question_data);
        tv_title = (TextView) findViewById(R.id.question_title);
        tv_content = (TextView) findViewById(R.id.question_answercontent);
        tv_editer = (TextView) findViewById(R.id.essay_editer);
        tv_author = (TextView) findViewById(R.id.serial_author_indent);
        tv_weibo = (TextView) findViewById(R.id.essay_author_weibo);

        relativeLayout = (RelativeLayout) findViewById(R.id.essay_context);
        loading_img = (TextView) findViewById(R.id.essay_loading);
        drawable = (AnimationDrawable) loading_img.getBackground();
        drawable.start();
        relativeLayout.setVisibility(View.GONE);
        cb_disc.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        content_Path = String.format(Constants.READING.ESSAY_CONTENT, id);
        OKHttpUtils.startOkhttp(handler, 0, content_Path);

        img_close.setOnClickListener(this);
        cb_good.setOnClickListener(this);
        cb_play.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(EssayActivity.this, DiscService.class);
        startService(intent);
    }

    public void updataEssay(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        essayContent = gson.fromJson(str, EssayContent.class);
        EssayContent.DataBean data = essayContent.getData();

        tv_username_1.setText(data.getHp_author());

        String date = TimeUtils.time2data_var2(data.getLast_update_date());
        tv_data.setText(date);
        tv_title.setText(data.getHp_title());
        tv_content.setText(Html.fromHtml(data.getHp_content()));
        tv_editer.setText(data.getHp_author_introduce());

        tv_username_2.setText(data.getAuthor().get(0).getUser_name());
        tv_author.setText(data.getAuthor().get(0).getDesc());
        tv_weibo.setText(data.getAuthor().get(0).getWb_name());
        String imgPic = data.getAuthor().get(0).getWeb_url();
        Picasso.with(EssayActivity.this).load(imgPic).resize(100, 100).into(img_userimg_1);
        Picasso.with(EssayActivity.this).load(imgPic).resize(100, 100).into(img_userimg_2);

        drawable.stop();
        loading_img.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);

        cb_good.setText(String.valueOf(data.getPraisenum()));
        cb_share.setText(String.valueOf(data.getSharenum()));
        cb_comment.setText(String.valueOf(data.getCommentnum()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.essay_close:
                finish();
                break;
            case R.id.essay_laud:
                int num = Integer.parseInt((String) cb_good.getText());
                if (cb_good.isChecked()) {
                    num = num + 1;
                } else {
                    num = num - 1;
                }
                cb_good.setText(String.valueOf(num));
                break;
            case R.id.essay_play:
                cb_disc.setVisibility(View.VISIBLE);
                cb_disc.setChecked(cb_play.isChecked());
                if (cb_play.isChecked()) {
                    cb_play.setText("暂停");
                    if (noplay) {
                        play();
                        noplay = false;
                    } else {
                        resume();
                    }
                } else {
                    cb_play.setText("收听");
                    pause();
                }
                break;
        }
    }

    public void play() {
        Intent intent = new Intent(EssayActivity.this, DiscService.class);
        intent.putExtra("path", essayContent.getData().getAudio());
        intent.putExtra("state", START);
        startService(intent);
    }

    public void pause() {
        Intent intent = new Intent(EssayActivity.this, DiscService.class);
        intent.putExtra("state", PAUSE);
        startService(intent);
    }

    public void resume() {
        Intent intent = new Intent(EssayActivity.this, DiscService.class);
        intent.putExtra("state", RESUME);
        startService(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_right);
    }
}
