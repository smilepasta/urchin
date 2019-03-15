package com.smilepasta.faceu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Author: huangxiaoming
 * Date: 2019/3/14
 * Desc: 工程展示活体检测的回调信息
 * Version: 1.0
 */
public class LivenessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demolivenessresult);
        ImageView iv_card_image = findViewById(R.id.iv_card_image);
        TextView tv_compare_result = findViewById(R.id.tv_compare_result);
        ImageView iv_liveness_image = findViewById(R.id.iv_liveness_image);
        LinearLayout ll_liveness_score = findViewById(R.id.ll_liveness_score);
        TextView tv_liveness_score = findViewById(R.id.tv_liveness_score);
        TextView tv_finish = findViewById(R.id.tv_finish);
        TextView tv_realresult = findViewById(R.id.tv_realresult);
        TextView tv_nopass_reason = findViewById(R.id.tv_nopass_reason);
        TextView tv_realauth_score = findViewById(R.id.tv_realauth_score);
        TextView tv_nopass_code = findViewById(R.id.tv_nopass_code);
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.tv_cardinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LivenessActivity.this, OCRResultActivity.class);
                startActivity(intent);
            }
        });
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2);
        if (CardInfoInstance.getInstance().getLivenessScore() != 0) {
            ll_liveness_score.setVisibility(View.VISIBLE);
            tv_liveness_score.setText(nt.format(Double.parseDouble(CardInfoInstance.getInstance().getLivenessScore() + "")));
        } else {
            ll_liveness_score.setVisibility(View.GONE);
        }
        tv_compare_result.setText(nt.format(Double.parseDouble(CardInfoInstance.getInstance().getSimilarity() + "")));
        iv_card_image.setImageBitmap(CardInfoInstance.getInstance().getFaceImage());
        iv_liveness_image.setImageBitmap(CardInfoInstance.getInstance().getLivenessFaceImage());

        tv_realresult.setText(CardInfoInstance.getInstance().getVerifyStatus() == 0 ? "不通过" : "通过");
        tv_nopass_reason.setText(CardInfoInstance.getInstance().getReason());
        tv_realauth_score.setText(CardInfoInstance.getInstance().getRealAuthSimilarity() + "");
        tv_nopass_code.setText(CardInfoInstance.getInstance().getReasonCode() + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CardInfoInstance.getInstance().getLivenessFaceImage() != null && !CardInfoInstance.getInstance().getLivenessFaceImage().isRecycled()) {
            CardInfoInstance.getInstance().getLivenessFaceImage().recycle();
        }
    }
}
