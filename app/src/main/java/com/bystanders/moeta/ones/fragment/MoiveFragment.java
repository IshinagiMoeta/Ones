package com.bystanders.moeta.ones.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.adapter.MoiveListAdapter;
import com.bystanders.moeta.ones.bean.MoiveBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoiveFragment extends Fragment {

    ListView listView;
    String page = "48";
    MoiveBean moiveBean;
    List<MoiveBean.DataBean> data;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String str = (String) msg.obj;
                    Gson gson = GsonUtils.getGson();
                    moiveBean = gson.fromJson(str, MoiveBean.class);
                    data = moiveBean.getData();
                    MoiveListAdapter adapter = new MoiveListAdapter(data, getActivity());
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };

    public MoiveFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = String.format(Constants.MOVIE.MOVIE_PATH, page);
        OKHttpUtils.startOkhttp(handler, 0, path);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moive, container, false);
        listView = (ListView) view.findViewById(R.id.movie_list);


        return view;
    }

}
