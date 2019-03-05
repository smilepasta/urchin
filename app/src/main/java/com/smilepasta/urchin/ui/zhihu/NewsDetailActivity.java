package com.smilepasta.urchin.ui.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.http.exception.ExceptionCodeUtil;
import com.smilepasta.urchin.presenter.implPresenter.NewsDetailPresenterImpl;
import com.smilepasta.urchin.presenter.implView.INewsDetailView;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.ui.common.WebViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.LogUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.TextUtil;
import com.smilepasta.urchin.utils.ToastUtil;
import com.smilepasta.urchin.widget.CircleImageView;
import com.smilepasta.urchin.widget.bubbleview.ContextMenuItem;
import com.smilepasta.urchin.widget.bubbleview.SHContextMenu;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailActivity extends TextBarActivity implements INewsDetailView {

    public final static String KEY_DETAIL_ID = "detail_id";

    private NewsDetailPresenterImpl newsDetailPresenter;
    private String detailId;
    private CircleImageView avatarImageView;
    private TextView contentTextView;
    private ZhiHuNewsDetailBean zhiHuNewsDetailBean;

    private void initData() {
        LogUtil.defLog("main current id "+android.os.Process.myPid());
        newsDetailPresenter = new NewsDetailPresenterImpl(this);
        Intent intent = getIntent();
        if (intent != null) {
            detailId = intent.getStringExtra(KEY_DETAIL_ID);
            if (StringUtil.isNotEmpty(detailId)) {
                doGetNewsDetail();
            }
        }
    }



    private void doGetNewsDetail() {
        newsDetailPresenter.getNewsDetail(detailId);
    }

    private void initView() {
        findViewById(R.id.goBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        avatarImageView = findViewById(R.id.iv_avatar);
        contentTextView = findViewById(R.id.tv_content);
    }

    @Override
    public void getNewsDetail(ZhiHuNewsDetailBean dataBean) {
        if (dataBean != null) {
            this.zhiHuNewsDetailBean = dataBean;
            if (StringUtil.isNotEmpty(dataBean.getImage())) {
                GlideUtil.loadImage(dataBean.getImage(), avatarImageView);
                avatarImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> imgList = new ArrayList<>();
                        imgList.add(dataBean.getImage());
                        PhotoViewActivity.start(NewsDetailActivity.this, imgList, PhotoViewActivity.IMAGE_PATH_TYPE_URL, 0);
                    }
                });
            }
            if (StringUtil.isNotEmpty(dataBean.getTitle())) {
                setTitleTextView(dataBean.getTitle());
            }
            if (StringUtil.isNotEmpty(dataBean.getBody())) {
                TextUtil.setHtmlToTextView(this, contentTextView, dataBean.getBody());
            }
        }
    }

    @Override
    public void showProgressDialog() {
        DialogUtil.progress(this);
    }

    @Override
    public void hideProgressDialog() {
        DialogUtil.cancel();
    }

    @Override
    public void showError(int code, String error) {

        showRetryDialog(ExceptionCodeUtil.convertMsg(NewsDetailActivity.this, code, error), new IRetryListener() {
            @Override
            public void retry() {
                doGetNewsDetail();
            }
        });
    }
    @Override
    protected void menuIconAction(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString(ACTION_TYPE);
            if (action.equals(ACTION_TYPE_MENU)) {
                initBubbleView();
            }
        }
    }

    private void initBubbleView() {
        SHContextMenu shContextMenu = new SHContextMenu(this);
        List<ContextMenuItem> itemList = new ArrayList<>();
        itemList.add(new ContextMenuItem(getResources().getDrawable(R.mipmap.pointer), getString(R.string.open_source_page)));
        itemList.add(new ContextMenuItem(getResources().getDrawable(R.mipmap.pointer), getString(R.string.share_page)));
        shContextMenu.setItemList(itemList);
        shContextMenu.setOnItemSelectListener(new SHContextMenu.OnItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                switchMenu(position);
            }
        });
        shContextMenu.showAsDropDown(titleLayout, 0, 2);
    }

    private void switchMenu(int position) {
        switch (position) {
            case 0://打开原网页
                String url = zhiHuNewsDetailBean.getShare_url();
                if (StringUtil.isNotEmpty(url)) {
                    WebViewActivity.start(this, url);
                }
                break;
            case 1://分享
                ToastUtil.show(this, getString(R.string.tips_6));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_news_detail, "");
        setMenu();
        initView();
        initData();
    }
}
