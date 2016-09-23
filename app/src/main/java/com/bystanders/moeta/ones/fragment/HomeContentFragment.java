package com.bystanders.moeta.ones.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bystanders.moeta.ones.R;
import com.bystanders.moeta.ones.bean.HomeBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.bystanders.moeta.ones.utils.GsonUtils;
import com.bystanders.moeta.ones.utils.OKHttpUtils;
import com.bystanders.moeta.ones.utils.SaveImgToLocal;
import com.bystanders.moeta.ones.utils.StringUtils;
import com.bystanders.moeta.ones.utils.TimeUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Home的ViewPager的内容Fragment
 * A simple {@link Fragment} subclass.
 */
public class HomeContentFragment extends Fragment implements View.OnClickListener, MenuItem.OnMenuItemClickListener {

    String vol;
    String path;
    HomeBean homeBean;
    ImageView imageView;
    TextView tv_vol;
    TextView tv_author;
    TextView tv_context;
    TextView tv_data;
    CheckBox cb_good;
    ImageView img;

    File bmpFile;
    boolean flag = false;

    AlertDialog dialog;

    /**
     * 接受异步任务并更新UI
     * 0是解析JSON数据，并且下载图片到本地
     * 1是发送消息给媒体库，告知显示图片加入图库
     * 2是图片下载好了之后来加载数据和预览图片
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                downImgToLoacl(msg);
            } else if (msg.what == 1) {
                SaveImgToLocal.insertMediaStore(getActivity(), bmpFile);
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                loadHomeData();
            }
        }
    };


    public HomeContentFragment() {
        // Required empty public constructor
    }


    /**
     * 加载来自HomeFragment的信息，并且开启OKHTTP下载具体信息
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        vol = bundle.getString("vol");
        path = String.format(Constants.HOME.HOME_DETAIL_PATH, vol);
        OKHttpUtils.startOkhttp(handler, 0, path);
    }

    /**
     * 绑定各个控件，并且设置点击事件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        imageView = (ImageView) view.findViewById(R.id.home_image);
        tv_vol = (TextView) view.findViewById(R.id.home_vol);
        tv_author = (TextView) view.findViewById(R.id.home_author);
        tv_context = (TextView) view.findViewById(R.id.home_context);
        tv_data = (TextView) view.findViewById(R.id.home_data);
        cb_good = (CheckBox) view.findViewById(R.id.home_good);

        cb_good.setOnClickListener(this);
        imageView.setOnClickListener(this);
        return view;
    }


    /**
     * 对点击事件进行处理
     * home_good ：点赞，赞数+1
     * home_image：弹出窗口显示具体图片，并且注册ContextMenu，实现图片的保存功能
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_good:
                int num = Integer.parseInt((String) cb_good.getText());
                if (cb_good.isChecked()) {
                    num = num + 1;
                } else {
                    num = num - 1;
                }
                cb_good.setText(String.valueOf(num));
                break;
            case R.id.home_image:
                if (flag) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(R.layout.home_imgdialog);
                    dialog = builder.create();
                    dialog.show();
                    Window window = dialog.getWindow();
                    //加载Dialog显示动画
                    window.setWindowAnimations(R.style.dialogWindowAnim);
                    //设置背景透明
                    window.setBackgroundDrawableResource(R.color.transparent_background);

                    img = (ImageView) window.findViewById(R.id.home_dialog_img);
                    Bitmap bitmap = BitmapFactory.decodeFile(bmpFile.getAbsolutePath());
                    img.setImageBitmap(bitmap);
                    bitmap = null;
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    registerForContextMenu(img);
                }
                break;
        }
    }

    /**
     * 创建contextMenu菜单，并且对菜单中的Item设置点击事件
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.home_img_menu, menu);
        menu.setHeaderTitle("更多操作");
        MenuItem item_1 = menu.findItem(R.id.home_saveimg);
        item_1.setOnMenuItemClickListener(this);
    }

    /**
     * 点击菜单事件
     * home_saveimg：保存图片到本地
     * P.S. Toast在handler中无法正常显示，所以移动到点击按钮之后
     * 可以优化
     *
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_saveimg:
                handler.sendEmptyMessage(1);
                break;
            case R.id.home_cancel:
                break;
        }
        return true;
    }

    /**
     * 加载该Fragment的JSON数据，并且先下载图片到本地，
     *
     * @param msg 从Okhttp下载下来的信息
     */
    public void downImgToLoacl(Message msg) {
        String str = (String) msg.obj;
        //Gson解析
        Gson gson = GsonUtils.getGson();
        homeBean = gson.fromJson(str, HomeBean.class);
        //先下载图片到本地
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    Log.e("====", "====");
                    String picUrl = homeBean.getData().getHp_img_url();
                    String bmpName = StringUtils.getPictureName(picUrl);
                    Bitmap bmp = Picasso.with(getActivity()).load(picUrl).get();
                    bmpFile = SaveImgToLocal.saveImageToLocal(getActivity(), bmp, bmpName);
                    bmp = null;
                    handler.sendEmptyMessage(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 等图片下载到本地之后
     * 加载数据到界面上
     */
    public void loadHomeData() {
        HomeBean.DataBean data = homeBean.getData();
        Picasso.with(getActivity()).load(bmpFile.getAbsoluteFile()).resize(500, 300).centerCrop().into(imageView);
        tv_vol.setText(data.getHp_title());
        tv_author.setText(data.getHp_author());
        tv_context.setText(data.getHp_content());
        String strData = data.getHp_makettime();
        tv_data.setText(TimeUtils.time2data(strData));
        cb_good.setText(String.valueOf(data.getPraisenum()));
        flag = true;
    }

    /**
     * Destory该Fragment，并且注销ContextMenu
     * P.S. 没有停止下载图片的进程，可以优化
     * 还有就是如果图片点击太多会OOM
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (img != null) {
            unregisterForContextMenu(img);
            img = null;
            bmpFile = null;
        }
    }
}
