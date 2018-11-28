package com.epam.mykhailo_hrois.task7.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.epam.mykhailo_hrois.task7.fragment.ThirdFragment;
import com.epam.mykhailo_hrois.task7.fragment.EditFragment;
import com.epam.mykhailo_hrois.task7.fragment.ConstraintFragment;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> tabs;

    public TabsFragmentAdapter(FragmentManager fm) {
        super(fm);
        initTabsMap();
    }


    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap() {
        tabs = new SparseArray<>();
        tabs.put(0, EditFragment.getInstance());
        tabs.put(1, ConstraintFragment.getInstance());
        tabs.put(2, ThirdFragment.getInstance());
        tabs.put(3, ThirdFragment.getInstance());
        tabs.put(4, ThirdFragment.getInstance());
    }
}
