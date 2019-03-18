package com.smilepasta.urchin.widget.imageselectview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smilepasta.urchin.BuildConfig;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.TextUtil;
import com.smilepasta.urchin.utils.UIUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: huangxiaoming
 * Date: 2019/3/15
 * Desc: 图片选择器
 * Version: 1.0
 */
public class ImageSelectView extends LinearLayout {

    public final static int IMAGE_UPLOAD_MAX_COUNT = 100;

    private TextView countTextView;
    private RecyclerView recyclerView;
    private Activity activity;

    private List<ImageBean> imageList = new ArrayList<>(); //上传的图片凭证的数据源
    private List<String> urlImageList = new ArrayList<>();//上传到七牛后返回的图片路径
    private ImageSelectAdapter imageSelectAdapter;


    public ImageSelectView(Context context) {
        super(context);
    }

    public ImageSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_image_select, this);
        countTextView = findViewById(R.id.tv_image_count);
        recyclerView = findViewById(R.id.rv_image_select);

        countTextView.setText(String.format(UIUtil.getString(R.string.tips_22), IMAGE_UPLOAD_MAX_COUNT, 0));
    }

    public ImageSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(Activity activity) {
        this.activity = activity;
        imageSelectAdapter = new ImageSelectAdapter(activity);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), UIUtil.getGridViewSuitableCount((Activity) getContext())));
        Uri uri = UIUtil.idToUri(R.mipmap.ic_add_img);
        imageList.add(new ImageBean(uri, ImageSelectAdapter.DEFAULT_ADD_IMAGE));
        imageSelectAdapter.setData(imageList);
        imageSelectAdapter.setOnItemClickLitener(new ImageSelectAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                ImageBean imageBean = imageList.get(position);
                if (imageBean.getType() == ImageSelectAdapter.DEFAULT_ADD_IMAGE) {
                    startMatisseGalleryActivity(activity, getImageList().size());
                } else {
                    startPhotoViewActivity(getImageList(), position, activity);
                }
            }

            @Override
            public void onDeleteItemClick(int position) {
                imageList.remove(position);
                imageSelectAdapter.setData(imageList);
                refreshImageCountTips();
            }
        });
        recyclerView.setAdapter(imageSelectAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * @param uriList  图片的Uri  用于显示图片
     * @param pathList 图片的绝对路径 用于压缩图片以及上传图片
     */
    public void addImage(List<Uri> uriList, List<String> pathList) {
        ImageBean addImage = imageList.get(imageList.size() - 1);
        imageList.remove(imageList.size() - 1);
        for (int i = 0; i < uriList.size(); i++) {
            imageList.add(new ImageBean(uriList.get(i), pathList.get(i), ImageSelectAdapter.ADD_CAMERA_IAMGE));
        }
        imageList.add(addImage);
        imageSelectAdapter.setData(imageList);
        refreshImageCountTips();
    }

    /**
     * 刷新已经上传的图片总数
     */
    private void refreshImageCountTips() {
        if (imageList != null && imageList.size() > 0) {
            countTextView.setText(String.format(UIUtil.getString(R.string.tips_22), IMAGE_UPLOAD_MAX_COUNT, imageList.size() - 1));
        }
    }

    /**
     * 上传图片之前先压缩图片
     */
    public void uploadImage() {

        String[] imgArr = getFilePathArrayByImageBeanList(getImageList());

        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        //覆盖源文件，图片路径包括名字都不该变
        options.overrideSource = false;
        Tiny.getInstance().source(imgArr).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile, Throwable t) {
                if (isSuccess) {
                    List<String> outFilePathList = TextUtil.getListByArray(outfile);
                    uploadQiniu(outFilePathList);
                }
            }
        });
    }

    public interface IImageUploadQiniuListener {
        void uploadSuccess(String imageUrls);

        void uploadFailed(String error);
    }

    IImageUploadQiniuListener imageUploadQiniuListener;

    public void setImageUploadQiniuListener(IImageUploadQiniuListener imageUploadQiniuListener) {
        this.imageUploadQiniuListener = imageUploadQiniuListener;
    }

    /**
     * 上传图片到七牛
     *
     * @param outFilePathList
     */
    private void uploadQiniu(List<String> outFilePathList) {
        DialogUtil.progress(activity, UIUtil.getString(R.string.tips_24));
        if (outFilePathList.size() > 0) {
            QiniuAuth.getInstance().init(new QiniuAuth.IQiniuListener() {
                @Override
                public void uploadImageSuccess(String url, int uploadSuccessCount) {
                    urlImageList.add(url);
                    if (outFilePathList.size() == uploadSuccessCount) {
                        DialogUtil.cancel();
                        String imageUrls = TextUtil.getStringByList(urlImageList);
                        if (StringUtil.isNotEmpty(imageUrls)) {
                            urlImageList.clear();
                            if (imageUploadQiniuListener != null) {
                                imageUploadQiniuListener.uploadSuccess(imageUrls);
                            }
                        }
                    }
                }

                @Override
                public void uploadImageFailed(String error) {
                    DialogUtil.cancel();
                    if (imageUploadQiniuListener != null) {
                        imageUploadQiniuListener.uploadFailed(error);
                    }
                }
            }, outFilePathList, activity);
        }
    }

    /**
     * 还没有选择图片
     */
    public boolean isNoPresenceImage() {
        return !(getImageList().size() > 0);
    }

    /**
     * 返回去掉加号的图片项
     *
     * @return
     */
    private List<ImageBean> getImageList() {
        return imageList.subList(0, imageList.size() - 1);
    }


    /**
     * 获取图片文件的绝对路径数组
     *
     * @param imageBeanList
     * @return
     */
    public String[] getFilePathArrayByImageBeanList(List<ImageBean> imageBeanList) {
        String[] imgArr = new String[imageBeanList.size()];
        for (int i = 0; i < imageBeanList.size(); i++) {
            String filePath = imageBeanList.get(i).getLocalPath();
            if (StringUtil.isNotEmpty(filePath)) {
                imgArr[i] = filePath;
            }
        }
        return imgArr;
    }

    /**
     * 打开图片选择器
     *
     * @param activity
     * @param selectedCount
     */
    public void startMatisseGalleryActivity(Activity activity, int selectedCount) {
        Matisse.from(activity)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))//图片类型
                .theme(R.style.Matisse_Zhihu)
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .capture(true)//选择照片时，是否显示拍照)
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".provider"))
                .maxSelectable(ImageSelectView.IMAGE_UPLOAD_MAX_COUNT - selectedCount)//可选的最大数 - 已选择的图片数量
                .imageEngine(new GlideEngine())
                .forResult(ImageSelectAdapter.OPEN_GALLERY);
    }

    /**
     * 打开图片预览窗口
     *
     * @param imageList
     * @param currentIndex
     * @param activity
     */
    public void startPhotoViewActivity(List<ImageBean> imageList, int currentIndex, Activity activity) {
        ArrayList<Uri> uriList = new ArrayList<>();
        for (ImageBean image : imageList) {
            uriList.add(image.getUri());
        }
        Intent intent = new Intent(activity, PhotoViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", PhotoViewActivity.IMAGE_PATH_TYPE_URI);
        bundle.putParcelableArrayList("list", uriList);
        //从第几张图片打开的预览图片
        bundle.putInt("index", currentIndex);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


}
