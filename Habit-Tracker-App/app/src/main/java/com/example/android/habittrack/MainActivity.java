package com.example.android.habittrack;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.habittrack.data.HabitContract;
import com.example.android.habittrack.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mHabitDbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHabitDbHelper = new HabitDbHelper(this);
        insertDataIntoDB();
        Cursor resultSet = readDataFromDb();
    }

    private void insertDataIntoDB() {

        database = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitDetail.COLUMN_PERSON_NAME,"Peter");
        values.put(HabitContract.HabitDetail.COLUMN_PERSON_AGE,32);
        values.put(HabitContract.HabitDetail.COLUMN_WEEK_DAY,"MONDAY");

        //For Follow Habit Set 1 that is true value and unFollow the
        //habit value set to 0 that is false value
        values.put(HabitContract.HabitDetail.COLUMN_WALKING_HABIT, HabitContract.HabitDetail.HABIT_VALUE_TRUE);
        values.put(HabitContract.HabitDetail.COLUMN_READING_HABIT, HabitContract.HabitDetail.HABIT_VALUE_FALSE);
        values.put(HabitContract.HabitDetail.COLUMN_NO_FAST_FOOD, HabitContract.HabitDetail.HABIT_VALUE_TRUE);

        long newRowID = database.insert(HabitContract.HabitDetail.TABLE_NAME,null,values);

        if(newRowID != -1)
            Toast.makeText(this,"Person Habit Details Saved",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Error with saving Person Habit Details",Toast.LENGTH_SHORT).show();
    }

    private Cursor readDataFromDb() {

        String[] projection= {
                HabitContract.HabitDetail._ID,
                HabitContract.HabitDetail.COLUMN_PERSON_NAME,
                HabitContract.HabitDetail.COLUMN_PERSON_AGE,
                HabitContract.HabitDetail.COLUMN_WEEK_DAY,
                HabitContract.HabitDetail.COLUMN_WALKING_HABIT,
                HabitContract.HabitDetail.COLUMN_READING_HABIT,
                HabitContract.HabitDetail.COLUMN_NO_FAST_FOOD
        };
        Cursor cursor = database.query(
                HabitContract.HabitDetail.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        //figure out index of each column

        int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_PERSON_NAME);
        int ageColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_PERSON_AGE);
        int weekColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_WEEK_DAY);
        int walkingHabitColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_WALKING_HABIT);
        int readingColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_READING_HABIT);
        int noFastFoodColumnIndex = cursor.getColumnIndex(HabitContract.HabitDetail.COLUMN_NO_FAST_FOOD);

        while (cursor.moveToNext()){
            int currentId = cursor.getInt(idColumnIndex);
            String currentPersonName = cursor.getString(nameColumnIndex);
            int currentPersonAge = cursor.getInt(ageColumnIndex);
            String currentWeekDay = cursor.getString(weekColumnIndex);
            int currentWalkingHabitStatus = cursor.getInt(walkingHabitColumnIndex);
            int currentReadingGabitStatus = cursor.getInt(readingColumnIndex);
            int currentNoFastFoodStatus = cursor.getInt(noFastFoodColumnIndex);

            //For the data display purpose only
            Toast.makeText(this,"Id: " + currentId,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Name: " + currentPersonName,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Age: " + currentPersonAge,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"WeekDay: " + currentWeekDay,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"WalkingHabit: " + currentWalkingHabitStatus,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"RedingHabit: " + currentReadingGabitStatus,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"NoFastFoodHabit: " + currentNoFastFoodStatus,Toast.LENGTH_SHORT).show();
        }

        return cursor;
    }
}
