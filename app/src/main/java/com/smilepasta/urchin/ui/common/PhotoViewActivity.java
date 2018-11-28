package com.smilepasta.urchin.ui.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.StatusBarUtil;
import com.smilepasta.urchin.widget.photoview.PhotoView;

import java.util.ArrayList;

/**
 * Author:huangxiaoming
 * Date:2018/4/19
 * Desc:图片预览
 * Version:1.0
 */
public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TextView indexTextView;

    private ArrayList<String> imgStrList = new ArrayList<>();

    private ArrayList<Uri> imgUriList = new ArrayList<>();

    public final static String IMAGE_PATH_TYPE_URI = "uri";
    public final static String IMAGE_PATH_TYPE_URL = "str";

    private String type;
    private int index;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        StatusBarUtil.setTranslucent(this);

        initViewPager();

        findViewById(R.id.leftImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViewPager() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        indexTextView = findViewById(R.id.indexText);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                type = bundle.getString("type");
                index = bundle.getInt("index");
                //用户传入的是uri list
                if (type.equals(IMAGE_PATH_TYPE_URI)) {
                    imgUriList = bundle.getParcelableArrayList("list");
                    if (imgUriList != null && imgUriList.size() > 0) {
                        count = imgUriList.size();
                        mPager.setAdapter(new UriPagerAdapter());
                    }
                } else if (type.equals(IMAGE_PATH_TYPE_URL)) {
                    imgStrList = bundle.getStringArrayList("list");
                    if (imgStrList != null && imgStrList.size() > 0) {
                        count = imgStrList.size();
                        mPager.setAdapter(new StrPagerAdapter());

                    }
                }
                indexTextView.setText((index + 1) + "/" + count);
                mPager.setCurrentItem(index >= 0 ? index : 0);
                mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        indexTextView.setText((position + 1) + "/" + count);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

        }
    }

    class UriPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgUriList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(PhotoViewActivity.this);
            view.enable();
            Glide.with(PhotoViewActivity.this)
                    .load(imgUriList.get(position))
                    .error(R.mipmap.error)
                    .crossFade()
                    .fitCenter()
                    .into(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class StrPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgStrList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(PhotoViewActivity.this);
            view.enable();
            Glide.with(PhotoViewActivity.this)
                    .load(imgStrList.get(position))
                    .error(R.mipmap.error)
                    .crossFade()
                    .fitCenter()
                    .into(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}