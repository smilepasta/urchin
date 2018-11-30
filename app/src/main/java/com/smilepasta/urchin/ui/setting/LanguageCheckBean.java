package com.smilepasta.urchin.ui.setting;

/**
 * Author:huangxiaoming
 * Date:2017/12/5 0005
 * Desc:
 */

public class LanguageCheckBean extends BaseCheckBean {
    private String languageName;
    private String languageVal;

    public LanguageCheckBean(String languageName, String languageVal, boolean isChecked) {
        super(isChecked);
        this.languageName = languageName;
        this.languageVal = languageVal;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageVal() {
        return languageVal;
    }

    public void setLanguageVal(String languageVal) {
        this.languageVal = languageVal;
    }
}
