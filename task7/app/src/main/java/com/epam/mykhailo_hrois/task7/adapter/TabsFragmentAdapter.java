package com.epam.mykhailo_hrois.task7.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.epam.mykhailo_hrois.task7.fragment.AbstractTabFragment;
import com.epam.mykhailo_hrois.task7.fragment.ThirdFragment;
import com.epam.mykhailo_hrois.task7.fragment.EditFragment;
import com.epam.mykhailo_hrois.task7.fragment.ConstraintFragment;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private SparseArray<AbstractTabFragment> tabs;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new SparseArray<>();
        tabs.put(0, EditFragment.getInstance(context));
        tabs.put(1, ConstraintFragment.getInstance(context));
        tabs.put(2, ThirdFragment.getInstance(context));
        tabs.put(3, ThirdFragment.getInstance(context));
        tabs.put(4, ThirdFragment.getInstance(context));
    }
}
