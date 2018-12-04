package com.smilepasta.urchin.bean.req;

/**
 * Author: huangxiaoming
 * Date: 2018/12/4
 * Desc:
 * Version: 1.0
 */
public class VersionReqBean {

    /**
     * versionCode : 10
     * language : zh
     */

    private int versionCode;
    private String language;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
