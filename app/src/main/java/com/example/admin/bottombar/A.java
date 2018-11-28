package com.example.admin.bottombar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.bottombar.base.BaseFragment;
import com.example.admin.bottombar.fragment.Fragment1;
import com.example.admin.bottombar.fragment.Fragment2;
import com.example.admin.bottombar.fragment.Fragment3;
import com.example.admin.bottombar.widget.bottombar.BottomBar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class A extends AppCompatActivity {
    BottomBar bb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        initFragment();
        switchFragment("1");
        bb = findViewById(R.id.bb);
        bb.setListener(new BottomBar.BottomBarListener() {
            @Override
            public void onClickListener1() {
                switchFragment("1");
            }

            @Override
            public void onClickListener2() {
                switchFragment("2");
            }

            @Override
            public void onClickListener3() {
                switchFragment("3");
            }
        });
    }

    private Map<String, BaseFragment> fragmentMap = new HashMap<>();

    private void initFragment() {
        fragmentMap.put("1", new Fragment1());
        fragmentMap.put("2", new Fragment2());
        fragmentMap.put("3", new Fragment3());
    }

    /**
     * 切换fragment
     *
     * @param fragmentName fragment的名称
     */
    private void switchFragment(String fragmentName) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentName);
        if (fragment == null) {
            ft.add(R.id.fl_act_main, fragmentMap.get(fragmentName), fragmentName);
        } else {
            fragmentMap.put(fragmentName, fragment);
            ft.show(fragmentMap.get(fragmentName));
        }

        Iterator<Map.Entry<String, BaseFragment>> iterator = fragmentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BaseFragment> entry = iterator.next();
            if (entry.getKey().equals(fragmentName)) {
                continue;
            }
            if (getSupportFragmentManager().findFragmentByTag(entry.getKey()) != null) {
                ft.hide(entry.getValue());
            }
        }

        ft.commitAllowingStateLoss();
    }
}
