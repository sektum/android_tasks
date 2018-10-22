package com.epam.mykhailo_hrois.task3;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class FirstActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    Fragment1 fragment1 = Fragment1.newInstance(Color.CYAN);
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.first_container, fragment1)
                .add(R.id.second_container, fragment2)
                .addToBackStack(null)
                .commit();
        findViewById(R.id.second_container).setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onFragmentInteraction(int color) {
        if(color == Color.CYAN) {
            fragment1.setmColor(Color.MAGENTA);
        }else {
            fragment1.setmColor(Color.CYAN);
        }
        if(fragment2.getView() != null) {
            fragment2.getView().setBackgroundColor(color);
        }
        if(fragment3.getView() != null) {
            fragment3.getView().setBackgroundColor(color);
        }
    }

    @Override
    public void onPlaceFragments(boolean place) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(place){
            fragmentTransaction.replace(R.id.second_container, fragment2);
        }
        else {
            fragmentTransaction.replace(R.id.second_container, fragment3);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
