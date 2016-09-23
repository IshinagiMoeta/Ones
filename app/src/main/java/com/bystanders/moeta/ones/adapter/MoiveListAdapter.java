package com.bystanders.moeta.ones.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.MoiveBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ishinagi_moeta on 2016/9/23.
 */
public class MoiveListAdapter extends BaseAdapter {
    List<MoiveBean.DataBean> data;
    Context context;

    public MoiveListAdapter(List<MoiveBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MoiveBean.DataBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.moive_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.get(position).getCover()).resize(985, 428).into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        View view;

        public ViewHolder(View view) {
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.movie_img);
            view.setTag(this);
        }
    }
}
