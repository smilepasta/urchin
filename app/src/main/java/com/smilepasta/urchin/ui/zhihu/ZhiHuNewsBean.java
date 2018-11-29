package com.smilepasta.urchin.ui.zhihu;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class ZhiHuNewsBean {

    /**
     * date : 20181116
     * stories : [{"images":["https://pic1.zhimg.com/v2-59e5ecbd08b7a74ca531440ca5215640.jpg"],"type":0,"id":9701730,"ga_prefix":"111622","title":"小事 · 被安排的一辈子"},{"images":["https://pic4.zhimg.com/v2-9719b5c7c447f98b1b30dc11acef8d1f.jpg"],"type":0,"id":9701698,"ga_prefix":"111621","title":"我不能对抗格林德沃\u2026\u2026可能这就是爱情吧"},{"images":["https://pic3.zhimg.com/v2-6521b149c027759c354a492fb070615e.jpg"],"type":0,"id":9701441,"ga_prefix":"111619","title":"马未都：那些年，我与缝纫机"},{"images":["https://pic4.zhimg.com/v2-97633178541935524d0225673365272f.jpg"],"type":0,"id":9701704,"ga_prefix":"111618","title":"死螃蟹真的不能吃吗？"},{"images":["https://pic2.zhimg.com/v2-8c088687d2828ef7e3d068bc956546d9.jpg"],"type":0,"id":9701647,"ga_prefix":"111615","title":"人类最早穿衣服的时候，就已经有「女装大佬」了"},{"images":["https://pic3.zhimg.com/v2-00f314241adc8706db157aa634326b9e.jpg"],"type":0,"id":9701690,"ga_prefix":"111614","title":"《知乎者耶》：两个人在一起久了，就能成为真爱吗？"},{"images":["https://pic4.zhimg.com/v2-5196b2629e79f698f2b8c808bdc118c3.jpg"],"type":0,"id":9701418,"ga_prefix":"111613","title":"大脑跟小孩子一样，得不到奖励就会不开心"},{"images":["https://pic4.zhimg.com/v2-635dcf969da2dbe331a8f2ed7733c90f.jpg"],"type":0,"id":9701609,"ga_prefix":"111612","title":"大误 · 这就是一个利益集团啊"},{"images":["https://pic1.zhimg.com/v2-53e1fe8918de45d87c4c7110b5ee28e8.jpg"],"type":0,"id":9701547,"ga_prefix":"111610","title":"你乱扔的那些塑料垃圾，终于盼来了被你吃下肚的这一天"},{"images":["https://pic3.zhimg.com/v2-47c693952cb734afa922158a185e97a2.jpg"],"type":0,"id":9701369,"ga_prefix":"111609","title":"单子齐全，该有的都有\u2026\u2026可它就是假的"},{"images":["https://pic1.zhimg.com/v2-81937907058eb09b5d69e844cf6accc0.jpg"],"type":0,"id":9701329,"ga_prefix":"111608","title":"- 为什么古人能创造出那么多的神？\r\n- 因为他们见过啊"},{"images":["https://pic3.zhimg.com/v2-944a72e513fc0dbcdb836bc26c6521c2.jpg"],"type":0,"id":9701464,"ga_prefix":"111607","title":"不甘心只做一个平凡的粉丝的我，开始把哆啦 A 梦当科幻片看"},{"images":["https://pic4.zhimg.com/v2-2ff8a6868566d15d5a3f65250b2ca73f.jpg"],"type":0,"id":9701595,"ga_prefix":"111607","title":"每次大跌都这么明显，当时怎么就没发现呢？"},{"images":["https://pic1.zhimg.com/v2-f4ebc981fc695302ed1c9d520365d1e4.jpg"],"type":0,"id":9701604,"ga_prefix":"111606","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-59e5ecbd08b7a74ca531440ca5215640.jpg"]
         * type : 0
         * id : 9701730
         * ga_prefix : 111622
         * title : 小事 · 被安排的一辈子
         */

        private int type;
        private String id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
