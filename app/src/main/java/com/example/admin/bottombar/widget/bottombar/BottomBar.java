package com.example.admin.bottombar.widget.bottombar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.admin.bottombar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部布局
 * 这是采用的上面是图片，下面是文字的布局方式
 */
public class BottomBar extends FrameLayout implements View.OnClickListener {
    private BottomBarTab mBbt1;
    private BottomBarTab mBbt2;
    private BottomBarTab mBbt3;

    private List<BottomBarTab> bbtList;         //控件
    private List<Integer> tvBottomBars;         //名称
    private List<Integer> imgBottomBarsNormal;  //普通图片
    private List<Integer> imgBottomBarsChecked; //选中的图片
    private List<Boolean> dvBottomBars;         //小红点显示还是隐藏的状态

    private int checkedPosition = 0;            //选中的position

    private BottomBarListener listener;

    public BottomBar(@NonNull Context context) {
        super(context);
        initData();
        init();
        setData();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
        init();
        setData();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        init();
        setData();
    }

    private void initData() {
        tvBottomBars = new ArrayList<>();
        tvBottomBars.add(R.string.home);
        tvBottomBars.add(R.string.message);
        tvBottomBars.add(R.string.me);

        imgBottomBarsNormal = new ArrayList<>();
        imgBottomBarsNormal.add(R.drawable.co_tab_home_unsel);
        imgBottomBarsNormal.add(R.drawable.co_tab_im_unsel);
        imgBottomBarsNormal.add(R.drawable.co_tab_up_unsel);

        imgBottomBarsChecked = new ArrayList<>();
        imgBottomBarsChecked.add(R.drawable.co_tab_home_sel);
        imgBottomBarsChecked.add(R.drawable.co_tab_im_sel);
        imgBottomBarsChecked.add(R.drawable.co_tab_up_sel);

        dvBottomBars = new ArrayList<>();
        dvBottomBars.add(false);
        dvBottomBars.add(false);
        dvBottomBars.add(false);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_bar, this, true);
        mBbt1 = findViewById(R.id.bbt1);
        mBbt2 = findViewById(R.id.bbt2);
        mBbt3 = findViewById(R.id.bbt3);
        mBbt1.setOnClickListener(this);
        mBbt2.setOnClickListener(this);
        mBbt3.setOnClickListener(this);
        bbtList = new ArrayList<>();
        bbtList.add(mBbt1);
        bbtList.add(mBbt2);
        bbtList.add(mBbt3);
    }

    private void setData() {
        int size = bbtList.size();
        for (int i = 0; i < size; i++) {
            BottomBarTab bbt = bbtList.get(i);
            bbt.setBottomTabText(tvBottomBars.get(i));
            if (checkedPosition == i) {
                bbt.setImageResource(imgBottomBarsChecked.get(i));
            } else {
                bbt.setImageResource(imgBottomBarsNormal.get(i));
            }
            if (dvBottomBars.get(i)) {
                bbt.showRedPoint();
            } else {
                bbt.hideRedPoint();
            }
        }
    }

    /**
     * 改变图片状态
     */
    private void changeImageStatus() {
        int size = bbtList.size();
        for (int i = 0; i < size; i++) {
            BottomBarTab bbt = bbtList.get(i);
            if (checkedPosition == i) {
                bbt.setImageResource(imgBottomBarsChecked.get(i));
            } else {
                bbt.setImageResource(imgBottomBarsNormal.get(i));
            }
        }
    }

    /**
     * 改变红点状态
     */
    private void changeRedPointStatus() {
        int size = bbtList.size();
        for (int i = 0; i < size; i++) {
            BottomBarTab bbt = bbtList.get(i);
            if (dvBottomBars.get(i)) {
                bbt.showRedPoint();
            } else {
                bbt.hideRedPoint();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bbt1:
                checkedPosition = 0;
                changeImageStatus();
                if (listener != null) {
                    listener.onClickListener1();
                }
                break;
            case R.id.bbt2:
                checkedPosition = 1;
                changeImageStatus();
                if (listener != null) {
                    listener.onClickListener2();
                }
                break;
            case R.id.bbt3:
                checkedPosition = 2;
                changeImageStatus();
                if (listener != null) {
                    listener.onClickListener3();
                }
                break;
        }
    }

    public void setListener(BottomBarListener listener) {
        this.listener = listener;
    }

    public static interface BottomBarListener {
        void onClickListener1();

        void onClickListener2();

        void onClickListener3();
    }

}
