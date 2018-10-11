package com.epam.mykhailo_hrois.task4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment implements View.OnClickListener {

    public static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "param3";

    private boolean placement;

    private OnFragmentInteractionListener mListener;

    public Fragment1() {

    }

    public static Fragment1 newInstance(int param1) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM2, param1);
        args.putInt(ARG_PARAM3, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    if (placement) {
                        mListener.onFragmentInteraction(getArguments().getInt(ARG_PARAM3));
                    } else {
                        mListener.onFragmentInteraction(getArguments().getInt(ARG_PARAM2));
                    }
                    break;
                case R.id.swap_first_to_second:
                    mListener.onPlaceFragments();
                    break;
                default:
                    break;
            }
        }
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
