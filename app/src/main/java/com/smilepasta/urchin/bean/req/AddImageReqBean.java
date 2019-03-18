package com.smilepasta.urchin.bean.req;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc: 上传图片对象
 * Version: 1.0
 */
public class AddImageReqBean {
    /**
     * {"imageUrls":"cdn.smilepasta.com/text.png"}
     */
    private String imageUrls;

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
}
