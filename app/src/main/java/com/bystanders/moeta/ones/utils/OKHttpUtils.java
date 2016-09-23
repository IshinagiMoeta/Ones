package com.bystanders.moeta.ones.utils;

import android.os.Handler;
import android.os.Message;

import com.bystanders.moeta.ones.bean.ReadingBroadCastBean;
import com.bystanders.moeta.ones.constants.Constants;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHTTP的工具类
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class OKHttpUtils {
    private static OkHttpClient okHttpClient;
    private static Request request;

    /**
     * 传入handler，what和下载地址，handler会返回下载得到的obj
     */
    static public void startOkhttp(final Handler handler, final int what, String path) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        request = new Request.Builder().url(path).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                Message message = new Message();
                message.what = what;
                message.obj = str;
                handler.sendMessage(message);
            }
        });
    }
}
