package com.nitol.aust.cse.austclassmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetQuiz extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Toolbar toolbar;
    EditText title, details;
    Button save, time;
    TextView s_date, s_time;

    QuizDatabaseHelper myDb;

    String q_title, q_details, h, m, y, mo, d;
    int year, month, day, hour, minute;
    int yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_quiz);

        myDb = new QuizDatabaseHelper(this);


        title = (EditText) findViewById(R.id.quiz_title);
        details = (EditText) findViewById(R.id.quiz_details);
        save = (Button) findViewById(R.id.quiz_save);
        time = (Button) findViewById(R.id.add_time);
        s_date = (TextView) findViewById(R.id.show_date);
        s_time = (TextView) findViewById(R.id.show_time);

        Calendar c = Calendar.getInstance();

        String yy = String.valueOf(c.get(Calendar.YEAR));
        String mm = String.valueOf(c.get(Calendar.MONTH));
        String dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        int hh = c.get(Calendar.HOUR);
        int mmm = c.get(Calendar.MINUTE);

        String timeSet = "";
        if (hh > 12) {
            hh -= 12;
            timeSet = "PM";
        } else if (hh == 0) {
            hh += 12;
            timeSet = "AM";
        } else if (hh == 12){
            timeSet = "PM";
        }else{
            timeSet = "AM";
        }

        String min = "";
        if (mmm < 10)
            min = "0" + mmm;
        else
            min = String.valueOf(mmm);

        String hhh = String.valueOf(hh);
        String mmmm = String.valueOf(min);

        s_date.setText(yy+"-"+mm+"-"+dd);
        s_time.setText(hhh+"."+mmmm+" "+timeSet);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(SetQuiz.this,R.style.DatePickerDialogTheme,
                        SetQuiz.this, year,month, day);
                datePickerDialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q_title = title.getText().toString();
                q_details = details.getText().toString();

                if(q_title.length() == 0 || q_details.length() == 0){
                    Toast.makeText(getApplicationContext(), "please fill the empty field!",
                            Toast.LENGTH_SHORT).show();

                }
                else{
                    boolean isInserted =  myDb.insertData(q_title, q_details, h, m, y, mo, d);

                    if(isInserted == true){
                        Toast.makeText(getApplicationContext(),"Data Inserted !", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Data not Inserted !", Toast.LENGTH_LONG).show();
                    }

                    Intent intent = new Intent(SetQuiz.this, QuizReminder.class);
                    startActivity(intent);
                    finish();
                }



            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        y = String.valueOf(yearFinal);
        mo = String.valueOf(monthFinal);
        d = String.valueOf(dayFinal);

        s_date.setText(d+"-"+mo+"-"+y);

        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog( SetQuiz.this,
                R.style.TimePickerDialogTheme, SetQuiz.this,
                hour, minute , false);

        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourFinal = hourOfDay;
        minuteFinal = minute;

        h = String.valueOf(hourFinal);
        m = String.valueOf(minuteFinal);

        String timeSet = "";
        if (hourFinal > 12) {
            hourFinal -= 12;
            timeSet = "PM";
        } else if (hourFinal == 0) {
            hourFinal += 12;
            timeSet = "AM";
        } else if (hourFinal == 12){
            timeSet = "PM";
        }else{
            timeSet = "AM";
        }

        String min = "";
        if (minuteFinal < 10)
            min = "0" + minute ;
        else
            min = String.valueOf(minuteFinal);

        String hh = String.valueOf(hourFinal);
        String mm = String.valueOf(min);

        String final_time = hh + ":"+mm+" "+timeSet;

        s_time.setText(final_time);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tool,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.tool_settings){
            Intent intent = new Intent(SetQuiz.this, Settings.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Toast.makeText(getApplicationContext(),"This is About !", Toast.LENGTH_SHORT).show();

        }
        else if ( id == android.R.id.home){

            finish();
        }


        return super.onOptionsItemSelected(item);
    }


}
