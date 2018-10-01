package com.zbwang.calendar.util;

/**
 * Created by bobomeilin on 2018/8/16.
 */
public class UrlUtils {

    /**
     * 返回图片地址
     */
    public static final String getAttachUrl(Integer id, String name) {
        return "/attach/" + id + "/" + name;
    }
}
