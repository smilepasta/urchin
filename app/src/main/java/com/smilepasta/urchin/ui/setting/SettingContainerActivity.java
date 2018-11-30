package com.smilepasta.urchin.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.UIUtil;

public class SettingContainerActivity extends TextBarActivity {

    public final static String SETTING_KEY_NAME = "name";
    public final static String SETTING_KEY_VALUE_PROTOBUF = "language_switch";

    @Override
    protected void menuIconAction(Bundle bundle) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String value = intent.getStringExtra(SETTING_KEY_NAME);
            if (StringUtil.isNotEmpty(value)) {
                if (value.equals(SETTING_KEY_VALUE_PROTOBUF)) {
                    startFragment(new LanguageCheckFragment());
                    setDefaultConfig(R.layout.common_fragment, UIUtil.getString(this, R.string.language_switch));
                }
            }
        } else {
            finish();
        }
    }

    public static void start(Activity activity, String keyVal) {
        Intent intent = new Intent(activity, SettingContainerActivity.class);
        intent.putExtra(SettingContainerActivity.SETTING_KEY_NAME, keyVal);
        activity.startActivity(intent);
    }

    private void startFragment(BasicFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }
}
