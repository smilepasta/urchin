package com.smilepasta.urchin.ui.setting;

/**
 * Author:huangxiaoming
 * Date:2017/11/22 0022
 * Desc:
 */

public class BaseCheckBean {

    private boolean isChecked;

    public BaseCheckBean(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
