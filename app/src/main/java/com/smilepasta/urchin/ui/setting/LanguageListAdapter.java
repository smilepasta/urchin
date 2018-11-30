package com.smilepasta.urchin.ui.setting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smilepasta.urchin.R;

import java.util.List;

/**
 * Author:huangxiaoming
 * Date:2017/11/22 0022
 * Desc:语言选择
 */

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.LanguageViewHolder> {

    private List<LanguageCheckBean> mLanguageList;
    private Context mContext;
    private int mSelectedPos = -1;
    private boolean onBind;

    public LanguageListAdapter(Context context, List<LanguageCheckBean> mLanguageList) {
        this.mContext = context;
        this.mLanguageList = mLanguageList;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LanguageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_check, parent, false));
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, final int position) {
        LanguageCheckBean languageCheckBean = mLanguageList.get(position);
        holder.mName.setText(languageCheckBean.getLanguageName());
        if (languageCheckBean.isChecked()) {
            mSelectedPos = position;
        }
        onBind = true;
        holder.mSelector.setChecked(languageCheckBean.isChecked());
        onBind = false;
        holder.mSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!onBind && mSelectedPos != position) {
                    //old
                    mLanguageList.get(mSelectedPos).setChecked(false);
                    notifyItemChanged(mSelectedPos);

                    //new
                    mSelectedPos = position;
                    mLanguageList.get(mSelectedPos).setChecked(true);
                    notifyItemChanged(mSelectedPos);

                    mOnLanguageCheckedListener.onLanguageChecked(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mSelector.setChecked(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mLanguageList == null ? 0 : mLanguageList.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {

        public TextView mName;
        public RadioButton mSelector;

        public LanguageViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name);
            mSelector = itemView.findViewById(R.id.rb_select);
        }
    }

    public interface OnLanguageCheckedListener {
        void onLanguageChecked(int position);
    }

    private OnLanguageCheckedListener mOnLanguageCheckedListener;

    public void setOnLanguageCheckedListener(OnLanguageCheckedListener mOnLanguageCheckedListener) {
        this.mOnLanguageCheckedListener = mOnLanguageCheckedListener;
    }

}
