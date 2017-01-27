package com.example.android.newsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //Variable for the drawerLayout and actionBarDrawerToggle
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Find the id of the DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        //set the drawerListener
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        //get the id of the navigation View
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final String TITLE = "toolbarTitle";
                String mTitle = "";
                Intent fragmentSendIntent;
                switch (item.getItemId()){
                    case R.id.nav_top_stories :
                        tabLayout.getTabAt(0).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_world :
                        tabLayout.getTabAt(1).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_business :
                        tabLayout.getTabAt(2).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_opinion :
                        tabLayout.getTabAt(3).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_politics :
                        tabLayout.getTabAt(4).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_sports :
                        tabLayout.getTabAt(5).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_fashion :
                        tabLayout.getTabAt(6).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_magazine :
                        tabLayout.getTabAt(7).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_national :
                        mTitle = getString(R.string.national);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_technology :
                        mTitle = getString(R.string.technology);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_ny_region :
                        mTitle = getString(R.string.ny_region);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_science :
                        mTitle = getString(R.string.science);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_health :
                        mTitle = getString(R.string.health);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_arts :
                        mTitle = getString(R.string.arts);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_food :
                        mTitle = getString(R.string.food);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_travel :
                        mTitle = getString(R.string.travel);
                        fragmentSendIntent = new Intent(MainActivity.this,MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE,mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_share :
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"Enter Your App Link");
                        startActivity(shareIntent);
                        break;
                    case R.id.nav_about :
                        Intent aboutIntent = new Intent(MainActivity.this,AboutPage.class);
                        startActivity(aboutIntent);
                        break;
                }
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
