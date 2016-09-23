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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.QuestionBean;
import com.bystanders.moeta.ones.bean.ReadingContentBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.bystanders.moeta.ones.utils.TimeUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    AnimationDrawable drawable;
    QuestionBean questionBean;

    ImageView img_close;

    RelativeLayout relativeLayout;
    TextView loading_img;
    String content_Path;

    CheckBox cb_good;
    CheckBox cb_comment;
    CheckBox cb_share;

    TextView tv_title;
    TextView tv_question;
    TextView tv_answer;
    TextView tv_data;
    TextView tv_answercontent;
    TextView tv_editer;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    updataQuestion(msg);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        img_close = (ImageView) findViewById(R.id.question_close);

        cb_good = (CheckBox) findViewById(R.id.question_laud);
        cb_comment = (CheckBox) findViewById(R.id.question_comment);
        cb_share = (CheckBox) findViewById(R.id.question_share);

        tv_data = (TextView) findViewById(R.id.question_data);
        tv_title = (TextView) findViewById(R.id.question_title);
        tv_editer = (TextView) findViewById(R.id.question_editer);
        tv_question = (TextView) findViewById(R.id.question_question);
        tv_answer = (TextView) findViewById(R.id.question_anwser);
        tv_answercontent = (TextView) findViewById(R.id.question_answercontent);

        relativeLayout = (RelativeLayout) findViewById(R.id.question_context);
        loading_img = (TextView) findViewById(R.id.question_loading);
        drawable = (AnimationDrawable) loading_img.getBackground();
        drawable.start();
        relativeLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        content_Path = String.format(Constants.READING.QUESTION_CONTENT, id);
        OKHttpUtils.startOkhttp(handler, 0, content_Path);

        img_close.setOnClickListener(this);
        cb_good.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void updataQuestion(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        questionBean = gson.fromJson(str, QuestionBean.class);
        QuestionBean.DataBean data = questionBean.getData();

        String date = TimeUtils.time2data_var2(data.getLast_update_date());
        tv_data.setText(date);
        tv_title.setText(data.getQuestion_title());
        tv_editer.setText(data.getCharge_edt());
        tv_question.setText(data.getQuestion_content());
        tv_answer.setText(data.getAnswer_title());
        tv_answercontent.setText(Html.fromHtml(data.getAnswer_content()));

        drawable.stop();
        loading_img.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);

        cb_good.setText(String.valueOf(data.getPraisenum()));
        cb_share.setText(String.valueOf(data.getSharenum()));
        cb_comment.setText(String.valueOf(data.getCommentnum()));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_close:
                finish();
                break;
            case R.id.question_laud:
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
}
