package com.smilepasta.urchin.ui.setting;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.List;

/**
 * Author:huangxiaoming
 * Date:2017/11/22 0022
 * Desc:语言选择
 */

public class LanguageListAdapter extends BasicRecyclerAdapter<LanguageCheckBean> {

    private int mSelectedPos = -1;
    private boolean onBind;

    LanguageListAdapter(Context context, List<LanguageCheckBean> list) {
        super(context, list, R.layout.item_check);
    }

    @Override
    public void convert(ViewHolder holder, LanguageCheckBean languageCheckBean, int position) {
        TextView nameTextView = holder.getView(R.id.tv_name);
        RadioButton selectorRadioButton = holder.getView(R.id.rb_select);
        if (languageCheckBean != null) {
            if (StringUtil.isNotEmpty(languageCheckBean.getLanguageName())) {
                nameTextView.setText(languageCheckBean.getLanguageName());
            }

            if (languageCheckBean.isChecked()) {
                mSelectedPos = position;
            }
            onBind = true;
            selectorRadioButton.setChecked(languageCheckBean.isChecked());
            onBind = false;
            selectorRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!onBind && mSelectedPos != position) {
                        //old
                        mList.get(mSelectedPos).setChecked(false);
                        notifyItemChanged(mSelectedPos);

                        //new
                        mSelectedPos = position;
                        mList.get(mSelectedPos).setChecked(true);
                        notifyItemChanged(mSelectedPos);

                        mOnLanguageCheckedListener.onLanguageChecked(position);
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectorRadioButton.setChecked(true);
                }
            });
        }
    }

    public interface OnLanguageCheckedListener {
        void onLanguageChecked(int position);
    }

    private LanguageListAdapter.OnLanguageCheckedListener mOnLanguageCheckedListener;

    public void setOnLanguageCheckedListener(LanguageListAdapter.OnLanguageCheckedListener mOnLanguageCheckedListener) {
        this.mOnLanguageCheckedListener = mOnLanguageCheckedListener;
    }

}
