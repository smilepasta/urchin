package com.smilepasta.urchin.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: huangxiaoming
 * Date: 2018/5/9
 * Desc:
 * Version: 1.0
 */
public class TextUtil {

    /**
     * 禁止EditText输入特殊字符(包括换行符|空格)
     *
     * @param editText
     */
    public static void setEditTextRefuseInputSpecialCharacter(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ \n]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static boolean isSpecialCharacter(CharSequence text) {
        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ \n]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher(text.toString());
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * edittext只能输入中英文和数字
     *
     * @param editText
     */
    public static void setEditTextOnlyInputZhongyinAndShuzi(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (!source.toString().matches("[\\u4e00-\\u9fa5_a-zA-Z0-9]+")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * phone = 18229833101 => 182****3101
     *
     * @param phone 手机号|座机号
     * @return
     */
    public static String hidePhone(String phone) {
        if (phone != null && phone.length() >= 7 && phone.length() <= 11) {
            return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        }
        return phone;
    }

    /**
     * 身份证中间8位隐藏
     * 隐藏出生年月
     *
     * @param idCard 身份证号
     */
    public static String hideIdCard(String idCard) {
        String idCardHide = idCard.replaceAll("(\\d{6})\\d{8}(\\w{4})", "$1*****$2");
        return idCardHide;
    }

    public static ArrayList<String> getListByString(String str) {
        if (StringUtil.isNotEmpty(str)) {
            return new ArrayList<>(Arrays.asList(str.split(",")));
        }
        return new ArrayList<>();
    }

    public static String getStringByList(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    public static String getStringByKeyAndVal(String content, String val) {
        if (StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(val)) {
            return String.format(content, val);
        }
        return "";
    }

    public static String getStringByEditText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString().trim();
        }
        return "";
    }

    public static String getStringByTextView(TextView textView) {
        if (textView != null) {
            return textView.getText().toString().trim();
        }
        return "";
    }

    public static String getTwoDigitName(String name) {
        if (StringUtil.isEmpty(name)) {
            return "";
        }
        String val = name.trim();
        if (val.length() > 0 && val.length() <= 2) {
            return val;
        }
        return val.substring(val.length() - 2, val.length());
    }

    public static List<String> getListByArray(String[] arr) {
        if (arr != null && arr.length > 0) {
            return Arrays.asList(arr);
        }
        return new ArrayList<>();
    }

    public static String[] numArray = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public static String transformNumberToStr(int num) {
        if (num >= 0 && num < numArray.length) {
            return numArray[num];
        }
        return "";
    }

    /**
     * 百分比
     *
     * @param x
     * @param total
     * @return
     */
    public static String percent(int x, int total) {
        String result = "";//接受百分比的值
        double x_double = x * 1.0;
        double tempresult = x / total;
        DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
        result = df1.format(tempresult);
        return result;
    }

}

