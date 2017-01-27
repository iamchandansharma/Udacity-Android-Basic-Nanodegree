package com.example.android.habittrack.data;

import android.provider.BaseColumns;

/**
 * Created by CS on 1/5/2017.
 */
public final class HabitContract {

    private HabitContract(){}

    public static final class HabitDetail implements BaseColumns{

        public static final String TABLE_NAME = "person_habit_detail";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PERSON_NAME = "name";
        public static final String COLUMN_PERSON_AGE = "age";
        public static final String COLUMN_WEEK_DAY = "week";
        public static final String COLUMN_WALKING_HABIT = "walking_habit";
        public static final String COLUMN_READING_HABIT = "reading_habit";
        public static final String COLUMN_NO_FAST_FOOD = "no_fast_food";

        public static final int HABIT_VALUE_FALSE = 0;
        public static final int HABIT_VALUE_TRUE = 1;
    }
}
