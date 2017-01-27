package com.example.android.newsapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    public CategoryAdapter(MainActivity mainActivity, FragmentManager fm) {
        super(fm);
        mContext = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new TopStories();
        }else if (position == 1){
            return new World();
        }else if (position == 2){
            return new Business();
        }else if (position == 3){
            return new Opinion();
        }else if (position == 4){
            return new Politics();
        }else if (position == 5){
            return new Sports();
        }else if (position == 6){
            return new Fashion();
        }else {
            return new Magazine();
        }
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return mContext.getString(R.string.top_stories);
        } else if (position == 1){
            return mContext.getString(R.string.world);
        }else if (position == 2){
            return mContext.getString(R.string.business);
        }else  if (position == 3){
            return mContext.getString(R.string.opinion);
        }else if (position == 4){
            return mContext.getString(R.string.politics);
        }else if (position == 5){
            return mContext.getString(R.string.sports);
        }else if (position == 6){
            return mContext.getString(R.string.fashion);
        }else {
            return mContext.getString(R.string.magazine);
        }
    }
}
