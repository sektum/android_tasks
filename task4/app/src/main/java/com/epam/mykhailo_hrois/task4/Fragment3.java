package com.epam.mykhailo_hrois.task4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment {

    private int mColor;

    public Fragment3() {

    }

    public static Fragment3 newInstance(int defaultColor) {
        Fragment3 fragment = new Fragment3();
        fragment.setmColor(defaultColor);
        Bundle args = new Bundle();
        args.putInt(Fragment1.ARG_PARAM3, defaultColor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(Fragment1.ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        view.setBackgroundColor(mColor);
        return view;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }
}
