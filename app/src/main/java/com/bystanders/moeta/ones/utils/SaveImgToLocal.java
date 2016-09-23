package com.bystanders.moeta.ones.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存图片到本地
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class SaveImgToLocal {
    public static File saveImageToLocal(Context context, Bitmap bmp, String bmpName) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Ones");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = bmpName + ".jpg";
        File file = new File(appDir, fileName);
        if (file.length() == 0) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static boolean insertMediaStore(Context context, File file) {
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
        return true;
    }


}
