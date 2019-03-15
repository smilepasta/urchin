package com.smilepasta.faceu;

import android.graphics.Bitmap;

import com.zqzn.faceu.sdk.common.inf.IDCardBackInfo;
import com.zqzn.faceu.sdk.common.inf.IDCardFrontInfo;
import com.zqzn.faceu.sdk.common.inf.LivenessCompareInfo;

/**
 * Author: huangxiaoming
 * Date: 2019/3/14
 * Desc: 程用于临时保存SDK回调数据(IDCardFrontInfo,IDCardBackInfo,DemoLivenessInfo)
 * Version: 1.0
 */
public class CardInfoInstance {
    private static CardInfoInstance instance;
    private String name="";
    private String idNo="";
    private String gender="";
    private String birth="";
    private String race="";
    private String address="";
    //修改后的姓名
    private String name2="";
    //修改后身份证号
    String idNo2="";
    private Bitmap frontImage;
    private Bitmap faceImage;

    private String issuedBy="";
    private String validDate="";
    private Bitmap backImage;

    private float livenessScore=0.0f;
    private float similarity=0.0f;


    private int verifyStatus;
    private String reason;
    private int reasonCode;
    private float realAuthSimilarity = 0.0F;
    private Bitmap livenessFaceImage=null;
    public static CardInfoInstance getInstance() {
        if (null == instance) {
            instance = new CardInfoInstance();
        }
        return instance;
    }

    public void addFrontText(IDCardFrontInfo idCardFrontInfo){
        this.idNo=idCardFrontInfo.getIdNo();
        this.name=idCardFrontInfo.getName();
        this.gender=idCardFrontInfo.getGender();
        this.birth=idCardFrontInfo.getBirth();
        this.race=idCardFrontInfo.getRace();
        this.address=idCardFrontInfo.getAddress();
        this.frontImage=idCardFrontInfo.getCardImage();
        this.faceImage=idCardFrontInfo.getFaceImage();
    }
    public void addBackText(IDCardBackInfo idCardBackInfo){
        this.issuedBy=idCardBackInfo.getIssuedBy();
        this.validDate=idCardBackInfo.getValidDate();
        this.backImage=idCardBackInfo.getCardImage();
    }
    public void addLiveness(LivenessCompareInfo livenessCompareInfo){
        livenessFaceImage=livenessCompareInfo.getFaceImage();
        livenessScore=livenessCompareInfo.getLivenessScore();
        similarity=livenessCompareInfo.getSimilarity();
        verifyStatus=livenessCompareInfo.getVerifyStatus();
        reason=livenessCompareInfo.getReason();
        reasonCode=livenessCompareInfo.getReasonCode();
        realAuthSimilarity=livenessCompareInfo.getRealAuthSimilarity();
    }
    public void clear(){
        name="";
        idNo="";
        gender="";
        birth="";
        race="";
        address="";
        //修改后的姓名
        name2="";
        frontImage=null;
        faceImage=null;

        issuedBy="";
        validDate="";
        backImage=null;

        livenessScore=0.0f;
        similarity=0.0f;
        verifyStatus=0;
        reason="";
        reasonCode=0;
        realAuthSimilarity=0.0f;
        livenessFaceImage=null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo2() {
        return idNo2;
    }

    public void setIdNo2(String idNo2) {
        this.idNo2 = idNo2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Bitmap getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Bitmap frontImage) {
        this.frontImage = frontImage;
    }

    public Bitmap getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(Bitmap faceImage) {
        this.faceImage = faceImage;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public Bitmap getBackImage() {
        return backImage;
    }

    public void setBackImage(Bitmap backImage) {
        this.backImage = backImage;
    }

    public float getLivenessScore() {
        return livenessScore;
    }

    public void setLivenessScore(float livenessScore) {
        this.livenessScore = livenessScore;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }

    public Bitmap getLivenessFaceImage() {
        return livenessFaceImage;
    }

    public void setLivenessFaceImage(Bitmap livenessFaceImage) {
        this.livenessFaceImage = livenessFaceImage;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }

    public float getRealAuthSimilarity() {
        return realAuthSimilarity;
    }

    public void setRealAuthSimilarity(float realAuthSimilarity) {
        this.realAuthSimilarity = realAuthSimilarity;
    }
}
