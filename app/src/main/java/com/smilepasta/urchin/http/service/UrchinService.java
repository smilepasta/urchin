package com.smilepasta.urchin.http.service;

import com.smilepasta.urchin.bean.req.VersionReqBean;
import com.smilepasta.urchin.bean.resp.VersionRespBean;
import com.smilepasta.urchin.http.UrlParser;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author: huangxiaoming
 * Date: 2018/12/4
 * Desc:
 * Version: 1.0
 */
public interface UrchinService {

    @POST(UrlParser.API_VERSION_UPGRADE)
    Observable<VersionRespBean> getVersionInfo(@Body VersionReqBean versionReqBean);
}
