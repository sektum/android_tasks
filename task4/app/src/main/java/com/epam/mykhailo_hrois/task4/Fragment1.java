package com.epam.mykhailo_hrois.task4;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment implements View.OnClickListener {

    public static final String ARG_PARAM1 = "param1";

    private int mColor;
    private boolean placement = true;

    private OnFragmentInteractionListener mListener;

    public Fragment1() {

    }

    public static Fragment1 newInstance(int param1) {
        Fragment1 fragment = new Fragment1();
        fragment.setmColor(param1);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(ARG_PARAM1, Color.BLACK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        view.findViewById(R.id.color_button).setOnClickListener(this);
        view.findViewById(R.id.swap_first_to_second).setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.color_button:
                    mListener.onFragmentInteraction(this.getmColor());
                    break;
                case R.id.swap_first_to_second:
                    mListener.onPlaceFragments();
                    break;
                default:
                    break;
            }
        }
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public boolean isPlacement() {
        return placement;
    }

    public void setPlacement(boolean placement) {
        this.placement = placement;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int color);

        void onPlaceFragments();
    }
}
