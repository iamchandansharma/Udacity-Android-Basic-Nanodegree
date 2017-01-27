package com.android.example.tourguide;

public class Word {

    // city name details
    private int mCityName;

    //city state name
    private int mStateName = NO_IMAGE_PROVIDED;
    //Image resource id
    private int mImageResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    //constructor for initialization

    public Word(int cityName, int stateName, int imageResourceId) {
        mCityName = cityName;
        mStateName = stateName;
        mImageResourceId = imageResourceId;
    }

    public Word ( int cityName, int imageResourceId){
        mCityName = cityName;
        mImageResourceId = imageResourceId;
    }
    //getter method for getting data

    public int getmCityName() {
        return mCityName;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmStateName() {
        return mStateName;
    }

    public boolean hasStateName() {
        return mStateName != NO_IMAGE_PROVIDED;
    }
}
