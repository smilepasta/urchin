package com.smilepasta.urchin.http.exception;

/**
 * Author: huangxiaoming
 * Date: 2019/3/4
 * Desc: 错误码
 * Version: 1.0
 */
public interface ExceptionCode {
    //200Code
//    public final static int RESPONSECODE_200 = 200;//请求成功
//    public final static int RESPONSECODE_202 = 202;//由于和被请求的资源的当前状态之间存在冲突，请求无法完成（已存在）
//    public final static int RESPONSECODE_204 = 204;//请求成功，不返回任何东西
//    public final static int RESPONSECODE_207 = 207;//如果请求的资源已永久删除，服务器就会返回此响应。（不存在）
//
//    //400Code
//    public final static int RESPONSECODE_400 = 400;//请求中有语法问题，或不能满足请求
//    public final static int RESPONSECODE_401 = 401;//没有证书
//    public final static int RESPONSECODE_402 = 402;//没有登陆权限
//    public final static int RESPONSECODE_403 = 403;//证书过期
//    public final static int RESPONSECODE_404 = 404;//（未找到） 服务器找不到请求的网页。
//    public final static int RESPONSECODE_407 = 407;//错误证书
//    public final static int RESPONSECODE_415 = 415;//请求中提交的实体并不是服务器中所支持的格式
//    public final static int RESPONSECODE_422 = 422;//请求格式正确，但是由于含有语义错误（参数值错误）
//    public final static int RESPONSECODE_423 = 423;//当前资源被锁定
//
//    //500Code
//    public final static int RESPONSECODE_500 = 500;//500   （服务器内部错误）  服务器遇到错误，无法完成请求。
//    public final static int RESPONSECODE_503 = 503;//503   （服务不可用） 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。
//    public final static int RESPONSECODE_504 = 504;//504   （网关超时）  服务器作为网关或代理，但是没有及时从上游服务器收到请求

    //自定义Code
    public final static int RESPONSECODE_1404 = 1404;//未知错误
    public final static int RESPONSECODE_1001 = 1001;//网络不可用
    public final static int RESPONSECODE_1002 = 1002;//请求网络超时
    public final static int RESPONSECODE_1003 = 1003;//数据解析错误
    public final static int RESPONSECODE_1004 = 1004;//服务器处理请求出错
    public final static int RESPONSECODE_1005 = 1005;//服务器无法处理请求
    public final static int RESPONSECODE_1006 = 1006;//请求被重定向到其他页面

}
