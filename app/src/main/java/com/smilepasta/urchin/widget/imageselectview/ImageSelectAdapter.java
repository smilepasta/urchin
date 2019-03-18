package com.smilepasta.urchin.widget.imageselectview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.GlideUtil;

import java.util.List;

/**
 * Author:huangxiaoming
 * Date:2017/9/29 0029
 * Desc:
 */
public class ImageSelectAdapter extends RecyclerView.Adapter<ImageSelectAdapter.UriViewHolder> {

    //默认表示九宫格中默认增加图片的标记(加号)
    public final static int DEFAULT_ADD_IMAGE = 0;
    //默认表示九宫格中拍照增加的图片的标记
    public final static int ADD_CAMERA_IAMGE = 1;
    //调用图库选择图片
    public final static int OPEN_GALLERY = 2;

    private List<ImageBean> uriList;
    private Context mContext;

    public void setData(List<ImageBean> uris) {
        uriList = uris;
        notifyDataSetChanged();
    }

    public ImageSelectAdapter(Context context) {
        this.mContext = context;
    }


    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(int position);

        void onDeleteItemClick(int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public UriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UriViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview_image, parent, false));
    }

    @Override
    public void onBindViewHolder(UriViewHolder holder, final int position) {
        ImageBean imageBean = uriList.get(position);

        if (imageBean != null && imageBean.getUri() != null) {
            if (imageBean.getType() == DEFAULT_ADD_IMAGE) {
                holder.del.setClickable(false);
                holder.del.setVisibility(View.GONE);
                holder.img.setImageURI(imageBean.getUri());
                if (uriList.size() == ImageSelectView.IMAGE_UPLOAD_MAX_COUNT + 1) {
                    //判断是不是添加的数量达到最大数目
                    holder.img.setVisibility(View.GONE);
                    //防止下面的点击事件产生，点击最后一个空白处会弹出添加图片框
                    return;
                } else {
                    holder.img.setVisibility(View.VISIBLE);
                }
            } else {
                //选中添加的图片
                holder.del.setVisibility(View.VISIBLE);
                holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickLitener.onDeleteItemClick(position);
                    }
                });
                holder.img.setVisibility(View.VISIBLE);
                GlideUtil.loadImage(imageBean.getUri(), holder.img);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    class UriViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private ImageView del;

        UriViewHolder(View contentView) {
            super(contentView);
            img = (ImageView) contentView.findViewById(R.id.iv_img);
            del = (ImageView) contentView.findViewById(R.id.iv_del);
        }
    }

}
