package com.bystanders.moeta.ones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.BroadCastBean;

import java.util.List;

/**
 * 广告轮播点击进入的页面的ListView的Adapter
 * Created by Ishinagi_moeta on 2016/9/21.
 */
public class BroadCastActivityListAdatper extends BaseAdapter {

    List<BroadCastBean.DataBean> data;
    Context context;

    public BroadCastActivityListAdatper(List<BroadCastBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BroadCastBean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.broadcast_list_item, null, true);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_num.setText(String.valueOf(position + 1));
        viewHolder.tv_title.setText(data.get(position).getTitle());
        viewHolder.tv_author.setText(data.get(position).getAuthor());
        viewHolder.tv_content.setText(data.get(position).getIntroduction());

        return convertView;
    }

    class ViewHolder {
        TextView tv_num;
        TextView tv_title;
        TextView tv_author;
        TextView tv_content;
        View view;

        public ViewHolder(View view) {
            this.view = view;
            tv_num = (TextView) view.findViewById(R.id.broadcast_list_num);
            tv_title = (TextView) view.findViewById(R.id.broadcast_list_title);
            tv_author = (TextView) view.findViewById(R.id.broadcast_list_author);
            tv_content = (TextView) view.findViewById(R.id.broadcast_list_content);
            view.setTag(this);
        }
    }
}
