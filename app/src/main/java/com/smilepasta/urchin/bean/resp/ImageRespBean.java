package com.smilepasta.urchin.bean.resp;

import com.smilepasta.urchin.bean.resp.base.BaseRespBean;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc:
 * Version: 1.0
 */
public class ImageRespBean extends BaseRespBean {

    /**
     * data : {"imageArr":[{"day":"2019-03-18","images":"https://cdn.smilepasta.com/1552900057884.jpg,https://cdn.smilepasta.com/1552900057903.jpg,https://cdn.smilepasta.com/1552900163503.jpg,https://cdn.smilepasta.com/1552900912050.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ImageArrBean> imageArr;

        public List<ImageArrBean> getImageArr() {
            return imageArr;
        }

        public void setImageArr(List<ImageArrBean> imageArr) {
            this.imageArr = imageArr;
        }

        public static class ImageArrBean {
            /**
             * day : 2019-03-18
             * images : https://cdn.smilepasta.com/1552900057884.jpg,https://cdn.smilepasta.com/1552900057903.jpg,https://cdn.smilepasta.com/1552900163503.jpg,https://cdn.smilepasta.com/1552900912050.jpg
             */

            private String day;
            private String images;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }
    }
}
