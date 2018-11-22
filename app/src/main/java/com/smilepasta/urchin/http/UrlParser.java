package com.smilepasta.urchin.http;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc: API
 * Version: 1.0
 */
public class UrlParser {

    //================= 知乎日报 =================
    public final static String HOST = "http://news-at.zhihu.com";

    public final static String API_PREFIX = "/api/4";

    public final static String API_LATEST = API_PREFIX + "/news/latest";//最新消息
    public final static String API_BEFORE = API_PREFIX + "/news/before/{pageDate}";//过往消息


}