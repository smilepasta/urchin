package com.smilepasta.urchin.bean.resp;

import com.smilepasta.urchin.bean.resp.base.BaseRespBean;

/**
 * Author: huangxiaoming
 * Date: 2018/12/4
 * Desc:
 * Version: 1.0
 */
public class VersionRespBean extends BaseRespBean{

    /**
     * data : {"id":1,"download_url":"https://cdn.smilepasta.com/urchin_v1.0.2_100_20181204_debug.apk","is_focre_update":null,"version_name":"1.0.1","version_code":100,"platform_code":"android","createdAt":"2019-03-25T06:22:53.000Z","updatedAt":"2019-03-25T06:22:53.000Z"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * download_url : https://cdn.smilepasta.com/urchin_v1.0.2_100_20181204_debug.apk
         * is_focre_update : null
         * version_name : 1.0.1
         * version_code : 100
         * platform_code : android
         * createdAt : 2019-03-25T06:22:53.000Z
         * updatedAt : 2019-03-25T06:22:53.000Z
         */

        private int id;
        private String download_url;
        private boolean is_focre_update;
        private String version_name;
        private int version_code;
        private String platform_code;
        private String createdAt;
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public boolean getIs_focre_update() {
            return is_focre_update;
        }

        public void setIs_focre_update(boolean is_focre_update) {
            this.is_focre_update = is_focre_update;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
