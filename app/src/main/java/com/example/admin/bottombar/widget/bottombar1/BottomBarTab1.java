package com.example.admin.bottombar.widget.bottombar1;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.admin.bottombar.R;
import com.example.admin.bottombar.widget.DotView;

public class BottomBarTab1 extends FrameLayout {
    private RadioButton rbBottomTab;
    private DotView mDvBottomTab;

    public BottomBarTab1(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomBarTab1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomBarTab1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_tab1, this, true);
        rbBottomTab = findViewById(R.id.rbBottomTab);
        mDvBottomTab = findViewById(R.id.dvBottomTab);
    }

    /**
     * 设置顶部的drawable
     *
     * @param resId
     */
    public void setDrawableTop(int resId) {
        Drawable top = getResources().getDrawable(resId);
        rbBottomTab.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
    }

    public void setBottomTabTextColor(int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(getContext(), resId);
        rbBottomTab.setTextColor(colorStateList);
    }

    public void setBottomTabText(int resId) {
        rbBottomTab.setText(resId);
    }

    public void setChecked(boolean checked) {
        rbBottomTab.setChecked(checked);
    }

    public void setRadioButtonListener(OnClickListener listener) {
        rbBottomTab.setOnClickListener(listener);
    }

    public void showRedPoint() {
        mDvBottomTab.setVisibility(VISIBLE);
    }

    public void hideRedPoint() {
        mDvBottomTab.setVisibility(GONE);
    }

}
