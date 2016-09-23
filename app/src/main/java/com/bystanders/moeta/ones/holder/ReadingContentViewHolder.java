package com.bystanders.moeta.ones.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.ReadingContentBean;
import com.bystanders.moeta.ones.callback.ReadingContentClick;

/**
 * 阅读页面的内容fragment
 * Created by Ishinagi_moeta on 2016/9/21.
 */
public class ReadingContentViewHolder {
    TextView title;
    TextView author;
    TextView content;
    ImageView image;
    int type = 0;

    View view;

    public ReadingContentViewHolder(View view, final ReadingContentClick readingContentClick) {
        this.view = view;
        title = (TextView) view.findViewById(R.id.reading_content_title);
        author = (TextView) view.findViewById(R.id.reading_content_author);
        content = (TextView) view.findViewById(R.id.reading_content_content);
        image = (ImageView) view.findViewById(R.id.reading_content_img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readingContentClick.clickView(type);
            }
        });
        view.setTag(this);
    }

    public void setEssayContent(ReadingContentBean.DataBean.EssayBean essayBean) {
        type = 0;
        title.setText(essayBean.getHp_title());
        author.setText(essayBean.getAuthor().get(0).getUser_name());
        content.setText(essayBean.getGuide_word());
        image.setImageResource(R.mipmap.essay_image);
    }

    public void setSerialContent(ReadingContentBean.DataBean.SerialBean serialBean) {
        type = 1;
        title.setText(serialBean.getTitle());
        author.setText(serialBean.getAuthor().getUser_name());
        content.setText(serialBean.getExcerpt());
        image.setImageResource(R.mipmap.serial_image);
    }

    public void setQuestionContent(ReadingContentBean.DataBean.QuestionBean questionBean) {
        type = 2;
        title.setText(questionBean.getQuestion_title());
        author.setText(questionBean.getAnswer_title());
        content.setText(questionBean.getAnswer_content());
        image.setImageResource(R.mipmap.question_image);
    }
}
