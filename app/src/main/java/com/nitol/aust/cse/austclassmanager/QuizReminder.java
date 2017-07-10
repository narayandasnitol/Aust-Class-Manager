package com.nitol.aust.cse.austclassmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class QuizReminder extends AppCompatActivity {

    Button setTime, setDate, save;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID_2 = 0;
    int hour_x, minute_x;
    int year_x, month_x, day_x;
    EditText title, details;
    TextView setTextTime, setTextDate;

    QuizDatabaseHelper myQuizDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_reminder);

        save = (Button) findViewById(R.id.btn_save);
        title = (EditText) findViewById(R.id.editText_title);
        details = (EditText) findViewById(R.id.editText_details);
        setTextTime = (TextView) findViewById(R.id.textView_time);
        setTextDate = (TextView) findViewById(R.id.textView_date);

        myQuizDb = new QuizDatabaseHelper(this);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        showTimePickerDialog();
        showDialogOnButtonClick();
        saveData();
    }


    public void showTimePickerDialog(){
        setTime = (Button) findViewById(R.id.btn_time);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }
    public void showDialogOnButtonClick(){
        setDate = (Button) findViewById(R.id.btn_date);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return new TimePickerDialog(QuizReminder.this, kTimePickerListener, hour_x, minute_x, false);
        }
        else if(id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        }
        else{
            return null;
        }
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;

            setTextTime.setText((hour_x+":"+minute_x).toString());

            Toast.makeText(QuizReminder.this,"Time is successfully set!", Toast.LENGTH_SHORT).show();
        }
    };


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x = dayOfMonth;

            Toast.makeText(getApplicationContext(),year_x +" / "+month_x+" / "+day_x,Toast.LENGTH_LONG).show();
        }
    };


    public void saveData(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quiz_title = String.valueOf(title.getText().toString());
                String quiz_details = String.valueOf(details.getText().toString());
                String quiz_time = (hour_x+":"+minute_x).toString();
                String quiz_date = (day_x+":"+month_x+":"+year_x).toString();

                myQuizDb.insertData(quiz_title,quiz_details,quiz_time,quiz_date);

                Intent intent = new Intent(QuizReminder.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }


}
