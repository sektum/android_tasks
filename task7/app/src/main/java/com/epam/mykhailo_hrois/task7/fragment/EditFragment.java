package com.epam.mykhailo_hrois.task7.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.mykhailo_hrois.task7.R;

public class EditFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_edit;

    public static EditFragment getInstance(Context context) {
        Bundle args = new Bundle();
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_edit));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
