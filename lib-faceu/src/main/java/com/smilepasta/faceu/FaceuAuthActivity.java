package com.smilepasta.faceu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.zqzn.faceu.sdk.common.inf.CommonParam;
import com.zqzn.faceu.sdk.common.inf.ErrorCode;
import com.zqzn.faceu.sdk.common.inf.IDCardBackInfo;
import com.zqzn.faceu.sdk.common.inf.IDCardFrontInfo;
import com.zqzn.faceu.sdk.common.inf.IDCardOperation;
import com.zqzn.faceu.sdk.common.inf.LivenessCompareInfo;
import com.zqzn.faceu.sdk.common.inf.LivenessOperation;
import com.zqzn.faceu.sdk.common.inf.ZQEngineCallback;
import com.zqzn.faceu.sdk.inf.AuthEngine;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Author: huangxiaoming
 * Date: 2019/3/14
 * Desc: 授权页面
 * Version: 1.0
 */
public class FaceuAuthActivity extends AppCompatActivity implements ZQEngineCallback {

    //appkey
    private final static String appKey = BuildConfig.FACEU_APPKEY;
    //secretkey
    private final static String secretKey = BuildConfig.FACEU_SECRETKEY;

    //是否先OCR流程
    private boolean isOCRFirst = true;
    //是否人脸比对
    private boolean isFaceCompare = true;

    //身份证国徽页面信息
    private IDCardBackInfo mIdCardBackInfo;
    //身份证人像页面信息
    private IDCardFrontInfo mIdCardFrontInfo;
    //活体检测识别、人脸比对返回结果
    private LivenessCompareInfo mLivenessCompareInfo;
    //身份证国徽页识别成功
    private boolean idCardFrontSuccess = false;
    //身份证人像页识别成功
    private boolean idCardBackSuccess = false;
    //活体检测成功
    private boolean livenessSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth();
    }

    private String getTraceId() {
        return UUID.randomUUID().toString();
    }

    private void auth() {
        //创建人脸核身引擎实例
        AuthEngine authEngine = new AuthEngine();
        //创建通用参数实例
        CommonParam commonParam = new CommonParam();
        //设置context参数为activity(必选)
        commonParam.setContext(FaceuAuthActivity.this);
        //设置AppKey(必选)
        commonParam.setAppKey(appKey);
        //设置SecretKey(必选)
        commonParam.setSecretKey(secretKey);
        //设置商户追踪号，建议唯一(必选)
        commonParam.setTraceId(getTraceId());
        //设置服务异步通知地址(可选参数，当需要接收异步通知时设置)
//        commonParam.setNotifyUrl(notifyUrl);
        //设置额外的异步通知信息(可选参数，当需要接收异步通知且有其他额外需要添加的信息时设置)
//        commonParam.setExtensionInfo(extensionInfo);
        //设置是否显示用户操作说明弹窗(可选，默认true)
        commonParam.setNeedUserGuide(true);

        //创建OCR操作模式实例
        IDCardOperation idCardOperation = new IDCardOperation();
        //设置操作模式(必选)
        //3——正面检测识别；可用IDCardOperation.FRONT_RECOGNIZE表示
        //19——正面检测识别+反面检测；可用IDCardOperation.FRONT_RECOGNIZE_BACK_DETECT表示
        //51——正面检测识别+反面检测识别；可用IDCardOperation.FRONT_RECOGNIZE_BACK_RECOGNIZE表示
        idCardOperation.setMode(IDCardOperation.FRONT_RECOGNIZE_BACK_RECOGNIZE);
        //设置是否显示识别结果页(可选,默认true)
        idCardOperation.setDisplayText(true);
        //创建活体检测操作模式实例
        LivenessOperation livenessOperation = new LivenessOperation();
        //设置是否开启动作语音播报(可选,默认true)
        livenessOperation.setOpenSpeaker(true);
        //设置动作数量(可选,默认3个动作)
        livenessOperation.setActionNum(3);
        //设置动作池集合(可选,默认5个)
        //0--眨眼(ACTION_BLINK);可以通过idlivenessOperation.addBlink()设置
        //1--微笑(ACTION_SMILE);可以通过idlivenessOperation.addSmile()设置
        //2--左转头(ACTION_FACE_TO_LEFT);可以通过idlivenessOperation.addFaceToLeft()设置
        //3--右转头(ACTION_FACE_TO_RIGHT);可以通过idlivenessOperation.addFaceToRight()设置
        //6--左右摇头(ACTION_SWING_HEAD);可以通过idlivenessOperation.addSwingHead()设置
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(LivenessOperation.ACTION_BLINK);
        integerSet.add(LivenessOperation.ACTION_SMILE);
        integerSet.add(LivenessOperation.ACTION_FACE_TO_LEFT);
        integerSet.add(LivenessOperation.ACTION_FACE_TO_RIGHT);
        integerSet.add(LivenessOperation.ACTION_SWING_HEAD);
        livenessOperation.setActions(integerSet);
        authEngine.startRealAuth(commonParam, isOCRFirst, isFaceCompare, idCardOperation, livenessOperation, FaceuAuthActivity.this);

    }


    /**
     * 引擎错误回调，如参数校验失败，鉴权失败。该种情况下不会启动sdk内置页面，不会有其他业务数据返回
     *
     * @param resultCode:错误结果码
     * @param resultMessage：错误描述
     * @param resultDetail：错误详情
     */
    @Override
    public void notifyEngineError(int resultCode, String resultMessage, String resultDetail) {
        Toast.makeText(this, resultMessage + resultCode, Toast.LENGTH_SHORT).show();
        Log.d("鉴权完成", resultCode + "..." + resultMessage + "..." + resultDetail);
    }

    /**
     * 当完成身份证正面检测识别后回调
     *
     * @param resultCode:错误结果码
     * @param resultMessage：错误描述
     * @param resultDetail：错误详情
     * @param zqOrderId：智趣订单号
     * @param idCardFrontInfo:当成功后，正面照相关信息
     */
    @Override
    public void notifyIDCardFrontResult(int resultCode, String resultMessage, String resultDetail, String zqOrderId, IDCardFrontInfo idCardFrontInfo) {
        //把回调数据保存在成员变量中，以待处理
        mIdCardFrontInfo = idCardFrontInfo;
        if (resultCode != ErrorCode.SUCCESS.getCode()) {
            idCardFrontSuccess = false;
            Toast.makeText(this, resultMessage + resultCode, Toast.LENGTH_SHORT).show();
            Log.d("正面识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "...." + zqOrderId);
        } else {
            idCardFrontSuccess = true;
            Log.d("正面识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "...." + zqOrderId + "...." + idCardFrontInfo.toString());
        }
    }

    /**
     * 当完成身份证反面检测识别后回调
     *
     * @param resultCode:错误结果码
     * @param resultMessage：错误描述
     * @param resultDetail：错误详情
     * @param zqOrderId：智趣订单号
     * @param idCardBackInfo:当成功后，反面照相关信息
     */
    @Override
    public void notifyIDCardBackResult(int resultCode, String resultMessage, String resultDetail, String zqOrderId, IDCardBackInfo idCardBackInfo) {
        //把回调数据保存在成员变量中，以待处理
        mIdCardBackInfo = idCardBackInfo;
        if (resultCode != ErrorCode.SUCCESS.getCode()) {
            idCardBackSuccess = false;
            Toast.makeText(this, resultMessage + resultCode, Toast.LENGTH_SHORT).show();
            Log.d("背面识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId);
        } else {
            idCardBackSuccess = true;
            Log.d("背面识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId + "...." + idCardBackInfo.toString());
        }
    }

    /**
     * 当完成身份证正面姓名修改回调（当显示识别结果页且有姓名修改时生效）
     *
     * @param resultCode
     * @param resultMessage
     * @param resultDetail
     * @param zqOrderId
     * @param newName
     * @param newIdNo
     */
    @Override
    public void notifyIDCardFrontModifyResult(int resultCode, String resultMessage, String resultDetail, String zqOrderId, String newName, String newIdNo) {
        if (resultCode != ErrorCode.SUCCESS.getCode()) {
            Log.d("修改完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId);
        } else {
            Log.d("修改完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId + "...." + newName);
        }
    }

    /**
     * 当进行活体检测识别、人脸比对后的回调
     *
     * @param resultCode:错误结果码
     * @param resultMessage：错误描述
     * @param resultDetail：错误详情
     * @param zqOrderId：智趣订单号
     * @param livenessCompareInfo
     */
    @Override
    public void notifyLivenessCompareResult(int resultCode, String resultMessage, String resultDetail, String zqOrderId, LivenessCompareInfo livenessCompareInfo) {
        //把回调数据保存在成员变量中，以待处理
        mLivenessCompareInfo = livenessCompareInfo;
        if (resultCode != ErrorCode.SUCCESS.getCode()) {
            livenessSuccess = false;
            Toast.makeText(this, resultMessage + resultCode, Toast.LENGTH_SHORT).show();
            Log.d("活体识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId);
        } else {
            livenessSuccess = true;
            Log.d("活体识别完成", resultCode + "..." + resultMessage + "..." + resultDetail + "..." + zqOrderId + "...." + livenessCompareInfo.toString());
        }
    }

    /**
     * 建议在onResume方法中处理回调结果
     */
    @Override
    protected void onResume() {
        super.onResume();
        CardInfoInstance.getInstance().clear();
        boolean isCardSuccess = idCardFrontSuccess || idCardBackSuccess;
        if (idCardFrontSuccess) {
            //正面操作成功
            idCardFrontSuccess = false;
            CardInfoInstance.getInstance().addFrontText(mIdCardFrontInfo);
        }
        if (idCardBackSuccess) {
            //反面操作成功
            idCardBackSuccess = false;
            CardInfoInstance.getInstance().addBackText(mIdCardBackInfo);
        }
        if (livenessSuccess && isCardSuccess) {
            //活体检测成功
            livenessSuccess = false;
            CardInfoInstance.getInstance().addLiveness(mLivenessCompareInfo);
            //跳转活体检测结果页面
            Intent intent = new Intent(this, LivenessActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
