package com.smilepasta.urchin.bean.req;

/**
 * Author: huangxiaoming
 * Date: 2018/12/4
 * Desc:
 * Version: 1.0
 */
public class VersionReqBean {

    /**
     * version_code : 100
     * platform_code : android
     */

    private int version_code;
    private String platform_code;

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }
}
