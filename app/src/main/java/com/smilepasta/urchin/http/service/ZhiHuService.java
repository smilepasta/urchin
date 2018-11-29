package com.smilepasta.urchin.http.service;

import com.smilepasta.urchin.ui.zhihu.ZhiHuNewsBean;
import com.smilepasta.urchin.http.UrlParser;
import com.smilepasta.urchin.ui.zhihu.ZhiHuNewsDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc: 知乎日报
 * Version: 1.0
 */
public interface ZhiHuService {

    /**
     * 最新消息
     * date : 日期
     * stories : 当日新闻
     * title : 新闻标题
     * images : 图像地址（官方 API 使用数组形式。目前暂未有使用多张图片的情形出现，曾见无 images 属性的情况，请在使用中注意 ）
     * ga_prefix : 供 Google Analytics 使用
     * type : 作用未知
     * id : url 与 share_url 中最后的数字（应为内容的 id）
     * multipic : 消息是否包含多张图片（仅出现在包含多图的新闻中）
     * top_stories : 界面顶部 ViewPager 滚动显示的显示内容（子项格式同上）（请注意区分此处的 image 属性与 stories 中的 images 属性）
     *
     * @return
     */
    @GET(UrlParser.API_LATEST)
    Observable<ZhiHuNewsBean> getLatestNews();

    @GET(UrlParser.API_BEFORE)
    Observable<ZhiHuNewsBean> getBeforeNews(@Path("pageDate") int pageDate);

    @GET(UrlParser.API_NEWS_DETAIL)
    Observable<ZhiHuNewsDetailBean> getNewsDetail(@Path("detailId") String detailId);


}
