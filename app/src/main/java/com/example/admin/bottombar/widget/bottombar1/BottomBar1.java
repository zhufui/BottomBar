package com.example.admin.bottombar.widget.bottombar1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.admin.bottombar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部布局
 * 这是采用的上面是图片，下面是文字的布局方式
 */
public class BottomBar1 extends FrameLayout {
    private BottomBarTab1 mBbt1;
    private BottomBarTab1 mBbt2;
    private BottomBarTab1 mBbt3;

    private List<BottomBarTab1> bbtList;        //控件
    private List<Integer> tvBottomBars;         //名称
    private List<Integer> tvBottomBarsTextColor;//名称
    private List<Integer> imgBottomBars;        //普通图片
    private List<Boolean> dvBottomBars;         //小红点显示还是隐藏的状态

    private int checkedPosition = 0;            //选中的position

    private BottomBarListener listener;

    public BottomBar1(@NonNull Context context) {
        super(context);
        initData();
        init();
        setData();
    }

    public BottomBar1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
        init();
        setData();
    }

    public BottomBar1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        imgBottomBars = new ArrayList<>();
        imgBottomBars.add(R.drawable.sel_home);
        imgBottomBars.add(R.drawable.sel_im);
        imgBottomBars.add(R.drawable.sel_up);

        tvBottomBarsTextColor = new ArrayList<>();
        tvBottomBarsTextColor.add(R.drawable.sel_bottom_bar_text_color);
        tvBottomBarsTextColor.add(R.drawable.sel_bottom_bar_text_color);
        tvBottomBarsTextColor.add(R.drawable.sel_bottom_bar_text_color);

        dvBottomBars = new ArrayList<>();
        dvBottomBars.add(false);
        dvBottomBars.add(false);
        dvBottomBars.add(false);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_bar1, this, true);
        mBbt1 = findViewById(R.id.bbt1);
        mBbt2 = findViewById(R.id.bbt2);
        mBbt3 = findViewById(R.id.bbt3);
        mBbt1.setRadioButtonListener((v) -> {
            checkedPosition = 0;
            changeImageStatus();
            if (listener != null) {
                listener.onClickListener1();
            }
        });
        mBbt2.setRadioButtonListener((v) -> {
            checkedPosition = 1;
            changeImageStatus();
            if (listener != null) {
                listener.onClickListener2();
            }
        });
        mBbt3.setRadioButtonListener((v) -> {
            checkedPosition = 2;
            changeImageStatus();
            if (listener != null) {
                listener.onClickListener3();
            }
        });
        bbtList = new ArrayList<>();
        bbtList.add(mBbt1);
        bbtList.add(mBbt2);
        bbtList.add(mBbt3);

        changeImageStatus();
    }

    private void setData() {
        int size = bbtList.size();
        for (int i = 0; i < size; i++) {
            BottomBarTab1 bbt = bbtList.get(i);
            bbt.setBottomTabText(tvBottomBars.get(i));
            bbt.setBottomTabTextColor(tvBottomBarsTextColor.get(i));
            bbt.setDrawableTop(imgBottomBars.get(i));
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
            BottomBarTab1 bbt = bbtList.get(i);
            bbt.setChecked(i == checkedPosition);
        }
    }

    /**
     * 改变红点状态
     */
    private void changeRedPointStatus() {
        int size = bbtList.size();
        for (int i = 0; i < size; i++) {
            BottomBarTab1 bbt = bbtList.get(i);
            if (dvBottomBars.get(i)) {
                bbt.showRedPoint();
            } else {
                bbt.hideRedPoint();
            }
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
