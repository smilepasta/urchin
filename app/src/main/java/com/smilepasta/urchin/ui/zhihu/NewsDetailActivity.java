package com.smilepasta.urchin.ui.zhihu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.presenter.implPresenter.INewsDetailPresenterImpl;
import com.smilepasta.urchin.presenter.implView.INewsDetailView;
import com.smilepasta.urchin.ui.common.PhotoViewActivity;
import com.smilepasta.urchin.ui.common.WebViewActivity;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.utils.GlideUtil;
import com.smilepasta.urchin.utils.StringUtil;
import com.smilepasta.urchin.utils.ToastUtil;
import com.smilepasta.urchin.utils.UIUtil;
import com.smilepasta.urchin.widget.CircleImageView;
import com.smilepasta.urchin.widget.bubbleview.ContextMenuItem;
import com.smilepasta.urchin.widget.bubbleview.SHContextMenu;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailActivity extends TextBarActivity implements INewsDetailView {

    public final static String KEY_DETAIL_ID = "detail_id";

    private INewsDetailPresenterImpl newsDetailPresenter;
    private String detailId;
    private CircleImageView avatarImageView;
    private TextView contentTextView;
    private NoUnderLineSpan noUnderLineSpan;
    private ZhiHuNewsDetailBean zhiHuNewsDetailBean;

    private void initData() {
        newsDetailPresenter = new INewsDetailPresenterImpl(this);
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
                parseBodyHtml(dataBean.getBody());
            }
        }
    }

    /**
     * 使用Html.fromHtml(String strHtml)转换html标签字符串，fromHtml()方法中会对html标签进行替换，并html标签封装成对应的格式对象。其中每一个<a>标签都会对应一个URLSpan对象。
     * 获取文本中所有的URLSpan对象，取出URLSpan对象的对应的位置、标识、以及对应的url地址后，使用ClickableSpan对象进行替换，并做自己的超链接逻辑处理。
     * Textview设置链接可点击，以及点击响应处理属性。
     *
     * @param bodyHtml
     */
    private void parseBodyHtml(String bodyHtml) {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                final LevelListDrawable drawable = new LevelListDrawable();
                Glide.with(NewsDetailActivity.this).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (resource != null) {
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                            drawable.addLevel(1, 1, bitmapDrawable);
                            drawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                            drawable.setLevel(1);
                            CharSequence text = contentTextView.getText();
                            contentTextView.setText(text);
                            contentTextView.refreshDrawableState();
                        }
                    }
                });
                return drawable;
            }
        };
        CharSequence charSequence;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(bodyHtml, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            charSequence = Html.fromHtml(bodyHtml, imageGetter, null);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(charSequence);
        URLSpan[] urlSpans = builder.getSpans(0, charSequence.length(), URLSpan.class);
        for (URLSpan span : urlSpans) {
            int start = builder.getSpanStart(span);
            int end = builder.getSpanEnd(span);
            String link = span.getURL();
            noUnderLineSpan = new NoUnderLineSpan(this, link);
            builder.setSpan(noUnderLineSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.removeSpan(span);
        }
        contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        contentTextView.setText(builder);
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
    public void showError(String error) {
        showRetryDialog(new IRetryListener() {
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

    public class NoUnderLineSpan extends URLSpan {
        private Context context;
        private String url;

        public NoUnderLineSpan(Context context, String url) {
            super(url);
            this.context = context;
            this.url = url;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
//            ds.setUnderlineText(false);
            ds.setColor(UIUtil.getColor(R.color.colorAccent));
        }

        @Override
        public void onClick(View widget) {
            WebViewActivity.start(NewsDetailActivity.this, url);
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
