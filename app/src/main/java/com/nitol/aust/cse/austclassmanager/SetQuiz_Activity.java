package com.nitol.aust.cse.austclassmanager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class SetQuiz_Activity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Toolbar toolbar;
    EditText title, details;
    Button save, time;
    TextView s_date, s_time;
    boolean check = false;


    QuizDatabaseHelper myDb;

    String q_title, q_details, h, m, y, mo, d, dd, mm, yy, yr, mn, dy, hr, mnt;
    int year, month, day, hour, minute, hh, mmm, alarm_id;
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

        yy = String.valueOf(c.get(Calendar.YEAR));
        mm = String.valueOf(c.get(Calendar.MONTH)+1);
        dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));


        hh = c.get(Calendar.HOUR_OF_DAY);
        mmm = c.get(Calendar.MINUTE);

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



        yr = String.valueOf(c.get(Calendar.YEAR));
        mn = String.valueOf(c.get(Calendar.MONTH)+1);
        dy = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        hr = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        mnt = String.valueOf(c.get(Calendar.MINUTE));

        Log.d("data", yr+" "+dy+" "+mn+" "+hr+" "+mnt+" "+check);

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

                check = true;

                DatePickerDialog datePickerDialog = new DatePickerDialog(SetQuiz_Activity.this,R.style.DatePickerDialogTheme,
                        SetQuiz_Activity.this, year,month, day);
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
                    if(check == true){
                        alarm_id =  myDb.insertData(q_title, q_details, h, m, y, mo, d);
                    }
                    else{
                        alarm_id =  myDb.insertData(q_title, q_details, hr, mnt, yr, mn, dy);
                    }


                    if(check ==  true){
                        if(Integer.valueOf(m) <=9) {
                            m = "0" + m;
                        }
                    }
                    else {
                        if (Integer.valueOf(mnt) <= 9) {
                            mnt = "0" + mnt;
                        }
                    }

                    Calendar calendar2 = Calendar.getInstance();

                    if(check == true) {
                        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
                        calendar2.set(Calendar.MINUTE, Integer.parseInt(m));
                        calendar2.set(Calendar.YEAR, Integer.parseInt(y));
                        calendar2.set(Calendar.MONTH, Integer.parseInt(mo) - 1);
                        calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d) - 1);

                        Log.d("data", y+" "+d+" "+m+" "+h+" "+mo);
                    }
                    else{
                        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt((hr)));
                        calendar2.set(Calendar.MINUTE, Integer.parseInt((mnt)));
                        calendar2.set(Calendar.YEAR, Integer.parseInt(yr));
                        calendar2.set(Calendar.MONTH, Integer.parseInt(mn) - 1);
                        calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dy) - 1);

                        Log.d("data", yr+" "+dy+" "+mn+" "+hr+" "+mnt);
                    }


                    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                    intent.putExtra("ID", alarm_id);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarm_id, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                            AlarmManager.INTERVAL_HOUR, pendingIntent);


                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(SetQuiz_Activity.this, QuizReminder_Activity.class);
                    startActivity(intent2);
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

        TimePickerDialog timePickerDialog = new TimePickerDialog( SetQuiz_Activity.this,
                R.style.TimePickerDialogTheme, SetQuiz_Activity.this,
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
            Intent intent = new Intent(SetQuiz_Activity.this, Settings_Activity.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Intent intent = new Intent(SetQuiz_Activity.this, Credit_activity.class);
            startActivity(intent);

        }
        else if ( id == android.R.id.home){

            finish();
        }


        return super.onOptionsItemSelected(item);
    }


}
