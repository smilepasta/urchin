package com.smilepasta.urchin.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smilepasta.urchin.Constant;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.ui.main.MainActivity;
import com.smilepasta.urchin.utils.PreUtil;
import com.smilepasta.urchin.utils.UIUtil;
import com.smilepasta.urchin.widget.recyclerview.CustomDividerItemDecoration;

import java.util.ArrayList;

/**
 * Author: huangxiaoming
 * Date: 2018/11/29
 * Desc:
 * Version: 1.0
 */
public class LanguageCheckFragment extends BasicFragment {


    private ArrayList<LanguageCheckBean> languageCheckList;

    public LanguageCheckFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_recyclerview, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        CustomDividerItemDecoration divider = new CustomDividerItemDecoration(mContext, CustomDividerItemDecoration.VERTICAL);
        divider.setDrawable(UIUtil.getDrawable(R.drawable.recycler_line));
        recyclerView.addItemDecoration(divider);

        languageCheckList = getInitLanguageData();

        LanguageListAdapter languageListAdapter = new LanguageListAdapter(mContext, languageCheckList);
        languageListAdapter.setOnLanguageCheckedListener(new LanguageListAdapter.OnLanguageCheckedListener() {
            @Override
            public void onLanguageChecked(int position) {
                LanguageCheckBean languageCheckBean = languageCheckList.get(position);
                if (languageCheckBean != null) {
                    PreUtil.setPrefString(mContext, Constant.LANGUAGE_TYPE, languageCheckBean.getLanguageVal());
                    MainActivity.start((Activity) mContext, MainActivity.FROM_VALUE_LANGUAGE);
                }
            }
        });
        recyclerView.setAdapter(languageListAdapter);
    }

    private ArrayList<LanguageCheckBean> getInitLanguageData() {
        String currentLanguage = PreUtil.getPrefString(mContext, Constant.LANGUAGE_TYPE, Constant.LANGUAGE_ZH);
        ArrayList<LanguageCheckBean> mLanguageBeanList = new ArrayList<>();
        String[] languageNameArr = getResources().getStringArray(R.array.language_name_array);
        String[] languageValueArr = getResources().getStringArray(R.array.language_value_array);
        for (int i = 0; i < languageNameArr.length; i++) {
            mLanguageBeanList.add(new LanguageCheckBean(languageNameArr[i], languageValueArr[i], currentLanguage.equals(languageValueArr[i])));
        }
        return mLanguageBeanList;
    }

}
