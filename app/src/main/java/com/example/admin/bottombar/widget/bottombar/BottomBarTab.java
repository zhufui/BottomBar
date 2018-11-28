package com.example.admin.bottombar.widget.bottombar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bottombar.R;
import com.example.admin.bottombar.widget.DotView;

public class BottomBarTab extends FrameLayout {
    private ImageView mImgBottomTab;
    private TextView mTvBottomTab;
    private DotView mDvBottomTab;

    public BottomBarTab(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomBarTab(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomBarTab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_tab, this, true);
        mImgBottomTab = findViewById(R.id.imgBottomTab);
        mTvBottomTab = findViewById(R.id.tvBottomTab);
        mDvBottomTab = findViewById(R.id.dvBottomTab);
    }

    public void setImageResource(int resId) {
        mImgBottomTab.setImageResource(resId);
    }

    public void setBottomTabText(int resId) {
        mTvBottomTab.setText(resId);
    }

    public void showRedPoint() {
        mDvBottomTab.setVisibility(VISIBLE);
    }

    public void hideRedPoint() {
        mDvBottomTab.setVisibility(GONE);
    }

}
