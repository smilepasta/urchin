package com.smilepasta.urchin;

/**
 * Author: huangxiaoming
 * Date: 2018/11/29
 * Desc:
 * Version: 1.0
 */
public class Constant {
    //关于我的页面
    public final static String ABOUT_ME_URL = "https://smilepasta.com/";

    //语言符号
    public final static String LANGUAGE_TYPE = "language_type";//语言类型的key
    public final static String LANGUAGE_ZH = "zh";//中文
    public final static String LANGUAGE_EN = "en";//英文

    //===============================七牛===========================
    public final static String QINIU_IMAGE_HOST = BuildConfig.QINIU_IMAGE_HOST;//qiniu 主机
    public final static String QINIU_ACCESS_KEY = BuildConfig.QINIU_ACCESS_KEY;//qiniu 两个参数
    public final static String QINIU_SECRET_KEY = BuildConfig.QINIU_SECRET_KEY;
    public final static String QINIU_BUCKET_NAME = BuildConfig.QINIU_BUCKET_NAME;//qiniu 图片存储在哪个文件夹
    //===============================七牛===========================
}
