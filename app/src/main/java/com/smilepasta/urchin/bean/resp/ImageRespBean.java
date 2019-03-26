package com.smilepasta.urchin.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

import com.smilepasta.urchin.bean.resp.base.BaseRespBean;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/18
 * Desc:
 * Version: 1.0
 */
public class ImageRespBean extends BaseRespBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 21
         * images : https://cdn.smilepasta.com/1553505635767.jpg,https://cdn.smilepasta.com/1553505635789.jpg
         * image_flag : 2019-03-25
         * desc : 天空
         * createdAt : 2019-03-25T09:20:40.000Z
         * updatedAt : 2019-03-25T09:20:40.000Z
         */

        private int id;
        private String images;
        private String image_flag;
        private String desc;
        private String createdAt;
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getImage_flag() {
            return image_flag;
        }

        public void setImage_flag(String image_flag) {
            this.image_flag = image_flag;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.images);
            dest.writeString(this.image_flag);
            dest.writeString(this.desc);
            dest.writeString(this.createdAt);
            dest.writeString(this.updatedAt);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.images = in.readString();
            this.image_flag = in.readString();
            this.desc = in.readString();
            this.createdAt = in.readString();
            this.updatedAt = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
