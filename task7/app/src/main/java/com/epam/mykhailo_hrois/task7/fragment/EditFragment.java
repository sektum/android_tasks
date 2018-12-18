package com.epam.mykhailo_hrois.task7.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.mykhailo_hrois.task7.R;

public class EditFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_edit;

    public static EditFragment getInstance() {
        Bundle args = new Bundle();
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT, container, false);
    }

}
