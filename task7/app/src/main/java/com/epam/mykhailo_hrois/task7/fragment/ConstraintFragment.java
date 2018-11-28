package com.epam.mykhailo_hrois.task7.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.mykhailo_hrois.task7.R;


public class ConstraintFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_constraint;

    public static ConstraintFragment getInstance() {
        Bundle args = new Bundle();
        ConstraintFragment fragment = new ConstraintFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT, container, false);
    }


}
