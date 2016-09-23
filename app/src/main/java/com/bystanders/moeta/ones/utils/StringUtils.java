package com.bystanders.moeta.ones.utils;

/**
 * 处理字符串的单元
 * Created by Ishinagi_moeta on 2016/9/21.
 */
public class StringUtils {
    /**
     * 获得网络图片的名称
     */
    public static String getPictureName(String path) {
        String name;
        String names[] = path.split("/");
        name = names[names.length - 1];
        return name;
    }
}
