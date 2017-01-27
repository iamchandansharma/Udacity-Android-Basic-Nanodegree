package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
;

public class MoreOption extends AppCompatActivity {

    //to get the toolbar title
    private String toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_option);
        Bundle data = getIntent().getExtras();
        toolbarTitle = data.getString("toolbarTitle");
        //get the custome Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set the title on toolbar
        getSupportActionBar().setTitle(toolbarTitle);

        //show the back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (toolbarTitle.equals(getString(R.string.national))) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new National());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.technology))) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Technology());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.ny_region))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new NewYorkRegion());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.ny_region))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Science());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.health))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Health());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.arts))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Arts());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.food))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Food());
            ft.commit();
        }else if (toolbarTitle.equals(getString(R.string.travel))){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, new Travel());
            ft.commit();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
