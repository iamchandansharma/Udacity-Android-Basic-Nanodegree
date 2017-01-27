package com.example.android.habittrack.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CS on 1/5/2017.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "person_habit.db";
    public static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PERSON_HABIT_ENTRY =
                "CREATE TABLE " + HabitContract.HabitDetail.TABLE_NAME + " (" +
                        HabitContract.HabitDetail._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HabitContract.HabitDetail.COLUMN_PERSON_NAME + " TEXT, " +
                        HabitContract.HabitDetail.COLUMN_PERSON_AGE + " INTEGER, " +
                        HabitContract.HabitDetail.COLUMN_WEEK_DAY + " TEXT, " +
                        HabitContract.HabitDetail.COLUMN_WALKING_HABIT + " INTEGER, " +
                        HabitContract.HabitDetail.COLUMN_READING_HABIT + " INTEGER, " +
                        HabitContract.HabitDetail.COLUMN_NO_FAST_FOOD + " INTEGER" + ")";
        db.execSQL(SQL_CREATE_PERSON_HABIT_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_PERSON_HABIT_ENTRY =
                "DELETE FROM " + HabitContract.HabitDetail.TABLE_NAME;
        db.execSQL(SQL_DELETE_PERSON_HABIT_ENTRY);
    }
}
