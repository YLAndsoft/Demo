package com.fyl.demo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by DN on 2017/9/27.
 */

public class FragmentsViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> title;
    public FragmentsViewPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<String> title) {
        super(fm);
        this.fragments = fragments;
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments != null && position < fragments.size() ? title.get(position) : "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

   /* @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        return super.instantiateItem(container, position);
    }*/
}
