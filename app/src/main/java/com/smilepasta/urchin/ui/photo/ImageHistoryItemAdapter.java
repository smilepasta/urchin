package com.smilepasta.urchin.ui.photo;

import android.content.Context;
import android.widget.ImageView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.widget.recyclerview.BasicRecyclerAdapter;

import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/19
 * Desc: 已上传图片列表适配
 * Version: 1.0
 */
public class ImageHistoryItemAdapter extends BasicRecyclerAdapter<String> {

    ImageHistoryItemAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_image);
    }

    @Override
    public void convert(ViewHolder holder, String item, int position) {
        if (item != null) {
            ImageView imageView = holder.getView(R.id.iv_img);
            if (StringUtil.isNotEmpty(item)) {
                GlideUtil.loadImage(item, imageView);
            }
        }
    }
}
