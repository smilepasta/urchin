package com.smilepasta.urchin.http.exception;

import android.content.Context;

import com.smilepasta.urchin.R;

/**
 * Author: huangxiaoming
 * Date: 2019/3/4
 * Desc: 将错误码转换成对应的文字描述
 * Version: 1.0
 */
public class ExceptionCodeUtil {

    public static String convertMsg(Context context, int code, String error) {
        String errorMsg = error;
        //非服务器自定义的code，获取本地的文字描述
        //如果是服务器的自定义code，直接使用服务器返回描述，不用再进行语言选择
        if (code > 1000 && code < 2000) {
            switch (code) {
                case ExceptionCode.RESPONSECODE_1001:
                    errorMsg = context.getString(R.string.tips_1001);
                    break;
                case ExceptionCode.RESPONSECODE_1002:
                    errorMsg = context.getString(R.string.tips_1002);
                    break;
                case ExceptionCode.RESPONSECODE_1003:
                    errorMsg = context.getString(R.string.tips_1003);
                    break;
                case ExceptionCode.RESPONSECODE_1004:
                    errorMsg = context.getString(R.string.tips_1004);
                    break;
                case ExceptionCode.RESPONSECODE_1005:
                    errorMsg = context.getString(R.string.tips_1005);
                    break;
                case ExceptionCode.RESPONSECODE_1006:
                    errorMsg = context.getString(R.string.tips_1006);
                    break;
                case ExceptionCode.RESPONSECODE_1404:
                    errorMsg = context.getString(R.string.tips_1404);
                    break;
                default:
                    errorMsg = context.getString(R.string.tips_1404);
            }
        }
        return errorMsg;
    }
}
