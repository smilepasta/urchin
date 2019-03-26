package com.smilepasta.urchin.bean.req;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc: 上传图片对象
 * Version: 1.0
 */
public class AddImageReqBean {
    /**
     * {"images":"cdn.smilepasta.com/text.png","desc":"图片描述"}
     */
    private String images;
    private String desc;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
