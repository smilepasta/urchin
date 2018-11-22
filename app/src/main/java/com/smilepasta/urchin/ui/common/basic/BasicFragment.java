package com.smilepasta.urchin.ui.common.basic;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.smilepasta.urchin.UrchinApp;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class BasicFragment extends Fragment {

    protected Context mContext;

    public Context getContext() {
        if (mContext == null) {
            return UrchinApp.getInstance();
        }
        return mContext;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
