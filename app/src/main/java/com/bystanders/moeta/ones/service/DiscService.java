package com.bystanders.moeta.ones.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bystanders.moeta.ones.activity.EssayActivity;

import java.io.IOException;

/**
 * 音频后台播放的service
 * Created by Ishinagi_moeta on 2016/9/22.
 */
public class DiscService extends Service {

    MediaPlayer mediaPlayer;
    long total;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent();
                intent.setAction("com.bystanders.moeta.ones.completion  ");
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("====", "==错误信息==");
                return false;
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int state = intent.getIntExtra("state", -1);
        final Intent sendIntent = new Intent();

        switch (state) {
            case EssayActivity.START:
                String path = intent.getStringExtra("path");
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                    total = mediaPlayer.getDuration();
                    mediaPlayer.start();
                    sendIntent.setAction("com.bystanders.moeta.musicservice.start");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case EssayActivity.PAUSE:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    sendIntent.setAction("com.bystanders.moeta.musicservice.pause");
                }
                break;
            case EssayActivity.RESUME:
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    sendIntent.setAction("com.bystanders.moeta.musicservice.resume");
                }
                break;
            case EssayActivity.STOP:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    sendIntent.setAction("com.bystanders.moeta.musicservice.stop");
                }
                break;
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
