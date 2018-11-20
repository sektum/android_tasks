package com.epam.mykhailo_hrois.task7;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.epam.mykhailo_hrois.task7.adapter.TabsFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    private void initTabs() {
        viewPager = findViewById(R.id.viewPager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void initNavigationView() {
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionEditItem:
                        showEditTab();
                        break;
                    case R.id.actionConstraintItem:
                        showGoogleTVTab();
                        break;
                    case R.id.actionThirdItem:
                        showThirdTab();
                        break;
                    case R.id.actionFourthItem:
                        showFourthTab();
                        break;
                    case R.id.actionFifthItem:
                        showFifthTab();
                        break;
                }
                return true;
            }
        });
    }

    private void showEditTab() {
        viewPager.setCurrentItem(Constants.TAB_ONE);
    }

    private void showGoogleTVTab() {
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }

    private void showThirdTab() {
        viewPager.setCurrentItem(Constants.TAB_THREE);
    }

    private void showFourthTab() {
        viewPager.setCurrentItem(Constants.TAB_FOUR);
    }

    private void showFifthTab() {
        viewPager.setCurrentItem(Constants.TAB_FIVE);
    }

}
