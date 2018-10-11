package com.epam.mykhailo_hrois.task4;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    Fragment1 fragment1 = Fragment1.newInstance(Color.BLACK);
    Fragment2 fragment2 = Fragment2.newInstance(Color.BLACK);
    Fragment3 fragment3 = Fragment3.newInstance(Color.BLACK);
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            fragment1 = (Fragment1) getSupportFragmentManager().getFragment(savedInstanceState, "frag1");
            fragment2 = (Fragment2) getSupportFragmentManager().getFragment(savedInstanceState, "frag2");
            Fragment3 current3 = (Fragment3) getSupportFragmentManager().getFragment(savedInstanceState, "frag3");
            if(current3 != null){
                fragment3 = current3;
            }
        }
        setContentView(R.layout.activity_first);
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.first_container, fragment1);
            fragmentTransaction.replace(R.id.second_container, fragment2);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "frag1", fragment1);
        getSupportFragmentManager().putFragment(outState, "frag2", fragment2);
        if(fragment3.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "frag3", fragment3);
        }
    }

    @Override
    public void onFragmentInteraction(int color) {
        int currentColor = color;
        if (currentColor == Color.CYAN) {
            currentColor = Color.MAGENTA;
        } else {
            currentColor = Color.CYAN;
        }
        if (fragment2.getView() != null) {
            bundle.putInt(Fragment1.ARG_PARAM2, currentColor);
            fragment2.setmColor(currentColor);
            fragment2.getView().setBackgroundColor(currentColor);
            fragment2.setArguments(bundle);
        }
        if (fragment3.getView() != null) {
            bundle.putInt(Fragment1.ARG_PARAM3, currentColor);
            fragment3.setmColor(currentColor);
            fragment3.getView().setBackgroundColor(currentColor);
            fragment3.setArguments(bundle);
        }
        fragment1.setArguments(bundle);
    }

    @Override
    public void onPlaceFragments() {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment1.isPlacement()) {
            fragmentTransaction.replace(R.id.second_container, fragment2);
            fragment1.setPlacement(false);
        } else {
            fragmentTransaction.replace(R.id.second_container, fragment3);
            fragment1.setPlacement(true);
        }
        fragment1.setArguments(bundle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}