package com.example.android.quizapp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class quiz_question extends AppCompatActivity {

    String nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);
        Bundle bundle = getIntent().getExtras();
        nameField = "Name : ";
        nameField += bundle.getString("nameField");
        TextView textView = (TextView) findViewById(R.id.text_field_name);
        textView.setText(nameField);
    }

    /**
     * All variable are used for store the status of the particular CheckBox or RadioButton
     */
    int questionOneStatus = 0, questionTwoStatus = 0, questionThreeStatus = 0, questionFourStatus = 0, questionFiveStatusOne = 0,
            questionFiveStatusTwo = 0, questionFiveStatusThree = 0, questionFiveStatusFour = 0, questionSixStatus = 0, questionSevenStatus = 0, questionEightStatusOne = 0,
            questionEightStatusTwo = 0, questionEightStatusThree = 0, questionEightStatusFour = 0, questionNineStatus = 0, questionTenStatus = 0;
    int counter = 0;

    /**
     * This method is used for the check the which checkbox is checked in the question number five.
     */
    public void onCheckBoxFiveClicked(View view) {
        boolean checkBoxStatus = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_question_five_id_one:
                if (checkBoxStatus)
                    questionFiveStatusOne = 1;
                else
                    questionFiveStatusOne = 0;
                break;
            case R.id.checkbox_question_five_id_two:
                if (checkBoxStatus)
                    questionFiveStatusTwo = 1;
                else
                    questionFiveStatusTwo = 0;
            case R.id.checkbox_question_five_id_three:
                if (checkBoxStatus)
                    questionFiveStatusThree = 1;
                else
                    questionFiveStatusThree = 0;
                break;
            case R.id.checkbox_question_five_id_four:
                if (checkBoxStatus)
                    questionFiveStatusFour = 1;
                else
                    questionFiveStatusFour = 0;
                break;
        }
    }

    /**
     * This method is used for the check the which checkbox is chcked in the question number eight.
     */

    public void onCheckBoxEightClicked(View view) {
        boolean checkBoxStatus = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_question_eight_id_one:
                if (checkBoxStatus)
                    questionEightStatusOne = 1;
                else
                    questionEightStatusOne = 0;
                break;
            case R.id.checkbox_question_eight_id_two:
                if (checkBoxStatus)
                    questionEightStatusTwo = 1;
                else
                    questionEightStatusTwo = 0;
                break;
            case R.id.checkbox_question_eight_id_three:
                if (checkBoxStatus)
                    questionEightStatusThree = 1;
                else
                    questionEightStatusThree = 0;
                break;
            case R.id.checkbox_question_eight_id_four:
                if (checkBoxStatus)
                    questionEightStatusFour = 1;
                else
                    questionEightStatusFour = 0;
                break;
        }
    }

    /**
     * This all methods is for check the which RadioGroup ic clicked and store it's status in variable.
     */

    public void onRadioGroupOneRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionOneStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_one_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_one_id_two:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_one_id_three:
                if (radioButtonStatus)
                    questionOneStatus = 1;
                break;
            case R.id.radio_group_question_one_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onRadioGroupTwoRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionTwoStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_two_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_two_id_two:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_two_id_three:
                if (radioButtonStatus)
                    questionTwoStatus = 1;
                break;
            case R.id.radio_group_question_two_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onRadioGroupThreeRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionThreeStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_three_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_three_id_two:
                if (radioButtonStatus)
                    questionThreeStatus = 1;
                break;
            case R.id.radio_group_question_three_id_three:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_three_id_four:
                if (radioButtonStatus)
                    break;

        }
    }

    public void onRadioGroupFourRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionFourStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_four_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_four_id_two:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_four_id_three:
                if (radioButtonStatus)
                    questionFourStatus = 1;
                break;
            case R.id.radio_group_question_four_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onRadioGroupSixRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionSixStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_six_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_six_id_two:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_six_id_three:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_six_id_four:
                if (radioButtonStatus)
                    questionSixStatus = 1;
                break;
        }
    }

    public void onRadioGroupSevenRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionSevenStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_seven_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_seven_id_two:
                if (radioButtonStatus)
                    questionSevenStatus = 1;
                break;
            case R.id.radio_group_question_seven_id_three:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_seven_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onRadioGroupNineRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionNineStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_nine_id_one:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_nine_id_two:
                if (radioButtonStatus)
                    questionNineStatus = 1;
                break;
            case R.id.radio_group_question_nine_id_three:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_nine_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onRadioGroupTenRadioButtonClicked(View view) {
        boolean radioButtonStatus = ((RadioButton) view).isChecked();
        questionTenStatus = 0;
        switch (view.getId()) {
            case R.id.radio_group_question_ten_id_one:
                if (radioButtonStatus)
                    questionTenStatus = 1;
                break;
            case R.id.radio_group_question_ten_id_two:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_ten_id_three:
                if (radioButtonStatus)
                    break;
            case R.id.radio_group_question_ten_id_four:
                if (radioButtonStatus)
                    break;
        }
    }

    public void onSubmit(View view) {
        if (questionOneStatus == 1)
            counter++;
        if (questionTwoStatus == 1)
            counter++;
        if (questionThreeStatus == 1)
            counter++;
        if (questionFourStatus == 1)
            counter++;
        if (questionFiveStatusOne == 1 && questionFiveStatusTwo == 1 && questionFiveStatusThree == 1 && questionFiveStatusFour == 0)
            counter++;
        if (questionSixStatus == 1)
            counter++;
        if (questionSevenStatus == 1)
            counter++;
        if (questionEightStatusOne == 1 && questionEightStatusTwo == 1 && questionEightStatusThree == 0 && questionEightStatusFour == 0)
            counter++;
        if (questionNineStatus == 1)
            counter++;
        if (questionTenStatus == 1)
            counter++;

        String result, resultValue;
        resultValue = Integer.toString(counter);
        result = nameField + "\nYour Result is : " + resultValue;
        Intent intent = new Intent(quiz_question.this, ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("resultValue", counter);
        startActivity(intent);
    }
}
