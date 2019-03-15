package com.smilepasta.faceu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author: huangxiaoming
 * Date: 2019/3/14
 * Desc: 工程展示扫描完成的回调信息
 * Version: 1.0
 */
public class OCRResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demoocrresult);

        LinearLayout llBack = findViewById(R.id.ll_back);
        ImageView ocrIdCard = findViewById(R.id.ocr_id_card);
        ImageView ocrIdCardBack = findViewById(R.id.ocr_id_card_back);

        ImageView ivHeader = findViewById(R.id.iv_header);
        EditText etName = findViewById(R.id.et_name);
        TextView tvCardNo = findViewById(R.id.tv_cardno);
        TextView tv_gender = findViewById(R.id.tv_gender);
        TextView tv_nation = findViewById(R.id.tv_nation);
        TextView tv_birth = findViewById(R.id.tv_birth);
        TextView tv_address = findViewById(R.id.tv_address);
        TextView tv_police = findViewById(R.id.tv_police);
        TextView tv_enddate = findViewById(R.id.tv_enddate);
        TextView tv_newname = findViewById(R.id.tv_newname);
        TextView tv_newcardno = findViewById(R.id.tv_newcardno);

        llBack.setOnClickListener(this);
        etName.setText(CardInfoInstance.getInstance().getName());
        tvCardNo.setText(CardInfoInstance.getInstance().getIdNo());
        tv_nation.setText(CardInfoInstance.getInstance().getRace());
        tv_birth.setText(CardInfoInstance.getInstance().getBirth());
        tv_address.setText(CardInfoInstance.getInstance().getAddress());
        tv_enddate.setText(CardInfoInstance.getInstance().getValidDate());
        tv_gender.setText(CardInfoInstance.getInstance().getGender());
        tv_police.setText(CardInfoInstance.getInstance().getIssuedBy());
        tv_newname.setText(CardInfoInstance.getInstance().getName2());
        tv_newcardno.setText(CardInfoInstance.getInstance().getIdNo2());
        if (CardInfoInstance.getInstance().getFrontImage() != null) {
            ocrIdCard.setImageBitmap(CardInfoInstance.getInstance().getFrontImage());
        }
        if (CardInfoInstance.getInstance().getBackImage() != null) {
            ocrIdCardBack.setImageBitmap(CardInfoInstance.getInstance().getBackImage());
        }
        if (CardInfoInstance.getInstance().getFaceImage() != null) {
            ivHeader.setImageBitmap(CardInfoInstance.getInstance().getFaceImage());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_sure) {
            finish();
        } else if (id == R.id.ll_back) {
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CardInfoInstance.getInstance().getFrontImage()!=null&&!CardInfoInstance.getInstance().getFrontImage().isRecycled()){
            CardInfoInstance.getInstance().getFrontImage().recycle();
        }
        if (CardInfoInstance.getInstance().getBackImage()!=null&&!CardInfoInstance.getInstance().getBackImage().isRecycled()){
            CardInfoInstance.getInstance().getBackImage().recycle();
        }
        if (CardInfoInstance.getInstance().getFaceImage()!=null&&!CardInfoInstance.getInstance().getFaceImage().isRecycled()){
            CardInfoInstance.getInstance().getFaceImage().recycle();
        }
    }
}
