package com.bystanders.moeta.ones.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.activity.EssayActivity;
import com.bystanders.moeta.ones.activity.QuestionActivity;
import com.bystanders.moeta.ones.activity.SerialActivity;
import com.bystanders.moeta.ones.bean.ReadingContentBean;
import com.bystanders.moeta.ones.callback.ReadingContentClick;
import com.bystanders.moeta.ones.holder.ReadingContentViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingContentFragment extends Fragment implements ReadingContentClick {

    LinearLayout linearLayout;
    ReadingContentViewHolder essayHolder;
    ReadingContentViewHolder serialHolder;
    ReadingContentViewHolder questionHolder;

    ReadingContentBean.DataBean.EssayBean essayBean;
    ReadingContentBean.DataBean.SerialBean serialBean;
    ReadingContentBean.DataBean.QuestionBean questionBean;

    public ReadingContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();

        essayBean = b.getParcelable("essayBean");
        serialBean = b.getParcelable("serialBean");
        questionBean = b.getParcelable("questionBean");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading_content, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.reading_content);

        View essayView = LayoutInflater.from(getActivity()).inflate(R.layout.reading_content_item, null, true);
        View serialView = LayoutInflater.from(getActivity()).inflate(R.layout.reading_content_item, null, true);
        View questionView = LayoutInflater.from(getActivity()).inflate(R.layout.reading_content_item, null, true);

        linearLayout.addView(essayView);
        linearLayout.addView(serialView);
        linearLayout.addView(questionView);

        essayHolder = new ReadingContentViewHolder(essayView, this);
        serialHolder = new ReadingContentViewHolder(serialView, this);
        questionHolder = new ReadingContentViewHolder(questionView, this);

        essayHolder.setEssayContent(essayBean);
        serialHolder.setSerialContent(serialBean);
        questionHolder.setQuestionContent(questionBean);

        return view;
    }

    /**
     * 接口传回的View点击事件
     *
     * @param type 0表示短篇
     *             1表示连载
     *             2表示问答
     */
    @Override
    public void clickView(int type) {
        Intent intent;
        Log.e("====", "====" + type);
        switch (type) {
            case 0:
                intent = new Intent(getActivity(), EssayActivity.class);
                intent.putExtra("id", essayBean.getContent_id());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.no_anim);
                break;
            case 1:
                intent = new Intent(getActivity(), SerialActivity.class);
                intent.putExtra("id", serialBean.getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.no_anim);
                break;
            case 2:
                intent = new Intent(getActivity(), QuestionActivity.class);
                intent.putExtra("id", questionBean.getQuestion_id());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.no_anim);
                break;
        }
    }
}
