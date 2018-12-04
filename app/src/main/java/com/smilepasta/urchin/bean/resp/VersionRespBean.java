package com.smilepasta.urchin.bean.resp;

/**
 * Author: huangxiaoming
 * Date: 2018/12/4
 * Desc:
 * Version: 1.0
 */
public class VersionRespBean {

    /**
     * code : 200
     * data : {"tips":"发现新版本，请更新","apkUrl":"http://smilepasta.com","isFocreUpdate":1}
     */

    private int code;
    private VersionDetailBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public VersionDetailBean getData() {
        return data;
    }

    public void setData(VersionDetailBean data) {
        this.data = data;
    }

    public static class VersionDetailBean {
        /**
         * tips : 发现新版本，请更新
         * apkUrl : http://smilepasta.com
         * isFocreUpdate : 1
         */

        private String tips;
        private String apkUrl;
        private String versionCode;
        private int isFocreUpdate;

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public int getIsFocreUpdate() {
            return isFocreUpdate;
        }

        public void setIsFocreUpdate(int isFocreUpdate) {
            this.isFocreUpdate = isFocreUpdate;
        }
    }
}
