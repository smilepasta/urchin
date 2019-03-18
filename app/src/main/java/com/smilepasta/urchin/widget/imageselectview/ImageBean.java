package com.smilepasta.urchin.widget.imageselectview;

import android.net.Uri;

/**
 * Author:huangxiaoming
 * Date:2017/12/14 0014
 * Desc:
 */

public class ImageBean {

    /**
     * 图片Uri对象
     */
    private Uri uri;
    /**
     * 图片的本地路径
     */
    private String localPath;
    /**
     * 图片上传后的路径
     */
    private String url;
    /**
     * 图片类型分为默认的加号按钮图片、要上传的图片
     */
    private int type;

    public ImageBean(Uri uri, int type) {
        this.uri = uri;
        this.type = type;
    }


    public ImageBean(Uri uri, String localPath, int type) {
        this.uri = uri;
        this.type = type;
        this.localPath = localPath;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
