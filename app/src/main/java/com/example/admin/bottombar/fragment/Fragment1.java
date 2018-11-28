package com.example.admin.bottombar.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.bottombar.R;
import com.example.admin.bottombar.base.BaseFragment;

public class Fragment1 extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }

    @Override
    protected void init(View view) {
        Log.d("Fragment1", "init");
        TextView tv = view.findViewById(R.id.tv);
        tv.setText("第一个fragment");
    }
}
