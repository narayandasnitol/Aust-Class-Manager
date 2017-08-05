package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClassDetails_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView status, running_class, running_teacher, class_room, running_schedule,
            time_left, next_schedule, next_class, next_teacher;

    ProfileDatabaseHelper myDb;
    ClassRoutineHelper cR;

    String myDept, myYear, mySemester, mySection, current_week;
    int current_hour, current_minute;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_details);

        status = (TextView) findViewById(R.id.status);
        running_class = (TextView) findViewById(R.id.running_class);
        running_teacher = (TextView) findViewById(R.id.running_teacher);
        class_room = (TextView) findViewById(R.id.class_room);
        running_schedule = (TextView) findViewById(R.id.current_schedule);
        time_left = (TextView) findViewById(R.id.time_left);
        next_schedule = (TextView) findViewById(R.id.next_schedule);
        next_class = (TextView) findViewById(R.id.next_class);
        next_teacher = (TextView) findViewById(R.id.next_teacher);

        myDb = new ProfileDatabaseHelper(this);
        cR = new ClassRoutineHelper();

        Calendar c = Calendar.getInstance();

        current_hour = c.get(Calendar.HOUR_OF_DAY);
        current_minute = c.get(Calendar.MINUTE);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        current_week = sdf.format(d);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(4).setChecked(true);


        getAllData();

    }


    public void getAllData(){

        Cursor result = myDb.getAllData();

        String student_department = "", student_year = "", student_semester = "", student_section = "";

        result.moveToFirst();
        while(!result.isAfterLast()){
            if (result.getString(result.getColumnIndex("DEPARTMENT")) != null){
                student_department += result.getString(result.getColumnIndex("DEPARTMENT"));
                student_department += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("YEAR")) != null){
                student_year += result.getString(result.getColumnIndex("YEAR"));
                student_year += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SEMESTER")) != null){
                student_semester += result.getString(result.getColumnIndex("SEMESTER"));
                student_semester += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SECTION")) != null){
                student_section += result.getString(result.getColumnIndex("SECTION"));
                student_section += "\n";
            }
            result.moveToNext();


        }

        myDept = student_department.trim();
        myYear = student_year.trim();
        mySemester = student_semester.trim();
        mySection = student_section.trim();

        String routine = cR.getRoutine(myDept,myYear,mySemester,mySection);




        // CSE 2.2 A start

        if(routine.trim().equals("cse_2_2_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Assembly Language Programming(A2)");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7B03");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Mathematics IV");
                    next_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Mathematics IV");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7A05");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Computer Architecture");
                    next_teacher.setText("Ms. Tahsin Aziz");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Algorithms Lab");
                    next_teacher.setText("FMS + Mahir");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Algorithms Lab(A1)");
                    running_teacher.setText("FMS + Mahir");
                    class_room.setText("7B05");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Software Development III(A1/A2)");
                    running_teacher.setText("MMH + Sadik");
                    class_room.setText("7B01");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7C07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7C07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Computer Architecture");
                    next_teacher.setText("Ms. Tahsin Aziz");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7C07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Digital Electronics and Pulse Techniques Lab(A1/A2)");
                    next_teacher.setText("SMS + MHA");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques Lab(A1/A2)");
                    running_teacher.setText("SMS + MHA");
                    class_room.setText("7B04");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Numerical Methods Lab(A1/A2)");
                    running_teacher.setText("SK + AS");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Algorithms Lab(A2)");
                    running_teacher.setText("FMS + Mahir");
                    class_room.setText("7B05");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Computer Architecture");
                    next_teacher.setText("Ms. Tahsin Aziz");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7C07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Mathematics IV");
                    next_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Mathematics IV");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7C07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7C07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Mathematics");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7C07");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Digital Electronics and Pulse Techniques");
                    next_teacher.setText("Shoeb Mohammad Shahriar");
                }

                else if(time >=13.5 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques");
                    running_teacher.setText("Shoeb Mohammad Shahriar");
                    class_room.setText("7C07");
                    running_schedule.setText("1.50-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Assembly Language Programming (A1)");
                    running_teacher.setText("TAZ + PS");
                    class_room.setText("7B03");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7A05");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Digital Electronics and Pulse Techniques");
                    next_teacher.setText("Shoeb Mohammad Shahriar");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques");
                    running_teacher.setText("Shoeb Mohammad Shahriar");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }

        // CSE 2.2 A end


        // CSE 2.2. B start

        if(routine.trim().equals("cse_2_2_b")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.5){

                    status.setText("Running");
                    running_class.setText("Mathematics IV");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7C07");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Digital Electronics and Pulse Techniques");
                    next_teacher.setText("Shoeb Mohammad Shahriar");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques");
                    running_teacher.setText("Shoeb Mohammad Shahriar");
                    class_room.setText("7C07");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=9.4 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7C07");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Algorithms Lab(B1)");
                    next_teacher.setText("FMS + Mahir");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Algorithms Lab(B1)");
                    running_teacher.setText("FMS + Mahir");
                    class_room.setText("7B05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }




            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7C06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=11.2 && time <=12.1){


                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7C06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7C06");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Numerical Methods Lab(B1/B2)");
                    next_teacher.setText("SK + AS");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Numerical Methods Lab(B1/B2)");
                    running_teacher.setText("SK + AS");
                    class_room.setText("7B05");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques Lab(B1/B2)");
                    running_teacher.setText("SMS + MHA");
                    class_room.setText("7B04");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Mathematics IV");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7A07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");

                }

                else if(time >=11.2 && time <=12.1){


                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7A07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Computer Architecture");
                    next_teacher.setText("Ms. Tahsin Aziz");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7A07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Assembly Language Programming (B2)");
                    next_teacher.setText("TAZ+AIR");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Assembly Language Programming (B2)");
                    running_teacher.setText("TAZ+AIR");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){


                    status.setText("Running");
                    running_class.setText("Algorithms Lab(A2)");
                    running_teacher.setText("FMS + Mahir");
                    class_room.setText("7B05");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Computer Architecture");
                    next_teacher.setText("Ms. Tahsin Aziz");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Computer Architecture");
                    running_teacher.setText("Ms. Tahsin Aziz");
                    class_room.setText("7A07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Mathematics IV");
                    next_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Mathematics IV");
                    running_teacher.setText("Dr. Muhammad Saiful Islam Mallik");
                    class_room.setText("7A07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Digital Electronics and Pulse Techniques");
                    next_teacher.setText("Shoeb Mohammad Shahriar");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques");
                    running_teacher.setText("Shoeb Mohammad Shahriar");
                    class_room.setText("7A07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Assembly Language Programming (B2)");
                    next_teacher.setText("TAZ+AIR");
                }


                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Assembly Language Programming (B2)");
                    running_teacher.setText("TAZ+AIR");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Software Development III(B1/B2)");
                    running_teacher.setText("MMH + Sadik");
                    class_room.setText("7B01");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Digital Electronics and Pulse Techniques");
                    next_teacher.setText("Shoeb Mohammad Shahriar");
                }

                else if(time >=10.3 && time <=11.2){


                    status.setText("Running");
                    running_class.setText("Digital Electronics and Pulse Techniques");
                    running_teacher.setText("Shoeb Mohammad Shahriar");
                    class_room.setText("7A06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Numerical Methods");
                    next_teacher.setText("Ms. Shanjida Khatun");
                }

                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Numerical Methods");
                    running_teacher.setText("Ms. Shanjida Khatun");
                    class_room.setText("7A06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Algorithms");
                    next_teacher.setText("Faisal Muhammad Shah");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Algorithms");
                    running_teacher.setText("Faisal Muhammad Shah");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }

        // CSE 2-2 B end



        // CSE 3-1 A start


        if(routine.trim().equals("cse_3_1_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Microprocessors");
                    running_teacher.setText("Mr. Sujan Sarker");
                    class_room.setText("7A04");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Mathematical Analysis for Computer Science ");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science ");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A04");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Digital System Design");
                    next_teacher.setText("Mr. D. M. Anisuzzaman");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A04");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Digital System Design Lab");
                    next_teacher.setText("DMA + PS");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital System Design Lab (A1/A2)");
                    running_teacher.setText("DMA + PS");
                    class_room.setText("7B04");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A04");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Mathematical Analysis for Computer Science ");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A04");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Database ");
                    next_teacher.setText("NSB ");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Database ");
                    running_teacher.setText("NSB");
                    class_room.setText("7A04");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Microprocessors Lab (A1/A2)");
                    next_teacher.setText("JM +SS ");
                }

                else if(time >=10.30 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Microprocessors Lab (A1/A2)");
                    running_teacher.setText("JM +SS");
                    class_room.setText("7B07");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science ");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A04");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Database");
                    next_teacher.setText("NSB");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A04");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Economics and Accounting ");
                    next_teacher.setText("");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting ");
                    running_teacher.setText("");
                    class_room.setText("7A04");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Database Lab (A1)");
                    next_teacher.setText("DMA + PS");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Database Lab (A1)");
                    running_teacher.setText("FMS+MA");
                    class_room.setText("7B06");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Software Development-IV (A1/A2)");
                    running_teacher.setText("TAB+MAT");
                    class_room.setText("7B03");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Microprocessors");
                    next_teacher.setText("Mr. Sujan Sarker");
                }

                else if(time >=13.5 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Microprocessors");
                    running_teacher.setText("Mr. Sujan Sarker");
                    class_room.setText("7A06");
                    running_schedule.setText("1.50-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A05");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Economics and Accounting ");
                    next_teacher.setText("");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Digital System Design");
                    next_teacher.setText("Mr. D. M. Anisuzzaman ");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A05");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Database Lab (A2)");
                    next_teacher.setText("FMS+MA ");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Database Lab (A2)");
                    running_teacher.setText("FMS+MA");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 3-1 A END


        // CSE 3-1 B START


        if(routine.trim().equals("cse_3_1_b")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting ");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Microprocessors ");
                    next_teacher.setText(" SS ");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Microprocessors");
                    running_teacher.setText("SS");
                    class_room.setText("7A05");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam ");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A05");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A05");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Digital System Design");
                    next_teacher.setText("Mr. D. M. Anisuzzaman ");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A05");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam ");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A05");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Software Development-IV (B1/B2)");
                    next_teacher.setText("TAB+MAT");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Software Development-IV (B1/B2)");
                    running_teacher.setText("TAB+MAT");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Digital System Design Lab (B1/B2)");
                    running_teacher.setText("DMA+PS");
                    class_room.setText("7A04");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Microprocessors Lab (B1/B2)");
                    next_teacher.setText("SS+JM");
                }



                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Microprocessors Lab (B1/B2)");
                    running_teacher.setText("SS+JM");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Database LAB (B1)");
                    running_teacher.setText("MA+NSB");
                    class_room.setText("7B06");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Database");
                    next_teacher.setText("NSB");
                }


                else if(time >=13.0 && time <=13.50){

                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                }

                else if(time >=13.50 && time <=14.4){


                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Microprocessor ");
                    next_teacher.setText("SS");
                }

                else if(time >=14.40 && time <=15.30){

                    status.setText("Running");
                    running_class.setText("Microprocessor ");
                    running_teacher.setText("SS");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Database LAB (B2)");
                    running_teacher.setText("MA+NSB");
                    class_room.setText("7B06");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Microprocessor");
                    next_teacher.setText("SS");
                }


                else if(time >=13.0 && time <=13.50){

                    status.setText("Running");
                    running_class.setText("Microprocessor");
                    running_teacher.setText("SS");
                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Economics and Accounting ");
                    next_teacher.setText("");
                }

                else if(time >=13.50 && time <=14.4){


                    status.setText("Running");
                    running_class.setText("Economics and Accounting ");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Digital System Design ");
                    next_teacher.setText("Mr. D. M. Anisuzzaman");
                }

                else if(time >=14.40 && time <=15.30){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=9.4 && time <=10.30){


                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Economics and Accounting");
                    next_teacher.setText("Mr. D. M. Anisuzzaman ");
                }

                else if(time >=10.30 && time <=11.20){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7C07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Digital System Design");
                    next_teacher.setText("Mr. D. M. Anisuzzaman ");
                }

                else if(time >=11.20 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7C07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 3-1 B END


        // CSE 3-1 C START



        if(routine.trim().equals("cse_3_1_c")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Microprocessors");
                    running_teacher.setText("SS");
                    class_room.setText("7A06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam ");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Digital System Design");
                    next_teacher.setText("Mr. D. M. Anisuzzaman ");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Database LAB (B1)");
                    next_teacher.setText("MA+NSB");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Database LAB (c1)");
                    running_teacher.setText("MA+NSB");
                    class_room.setText("7B07");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Economics and Accounting ");
                    next_teacher.setText("");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Mr. D. M. Anisuzzaman");
                    next_teacher.setText("");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital System Design");
                    running_teacher.setText("Mr. D. M. Anisuzzaman");
                    class_room.setText("7A07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam ");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Database");
                    next_teacher.setText("NSB");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("DATABASE ");
                    running_teacher.setText("NSB");
                    class_room.setText("7A06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Digital System Design  LAB (C1/C2)");
                    next_teacher.setText("DMA+PS");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital System Design  LAB (C1/C2)");
                    running_teacher.setText("DMA+PS");
                    class_room.setText("7B04");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=13.50){

                    status.setText("Running");
                    running_class.setText("Microprocessor ");
                    running_teacher.setText("SS");
                    class_room.setText("7A03");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Database");
                    next_teacher.setText("NSB");
                }

                else if(time >=13.50 && time <=14.4){


                    status.setText("Running");
                    running_class.setText("Database");
                    running_teacher.setText("NSB");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam");
                }

                else if(time >=14.40 && time <=15.30){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Economics and Accounting");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Microprocessor ");
                    next_teacher.setText("SS");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Microprocessor ");
                    running_teacher.setText("SS");
                    class_room.setText("7A06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Mathematical Analysis for Computer Science");
                    next_teacher.setText("Dr. Mohammad Shafiul Alam");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Mathematical Analysis for Computer Science");
                    running_teacher.setText("Dr. Mohammad Shafiul Alam");
                    class_room.setText("7A06");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Database LAB (C2)");
                    next_teacher.setText("MA+NSB");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Database LAB (C2)");
                    running_teacher.setText("MA+NSB");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Microprocessors Lab (C1/C2)");
                    running_teacher.setText("SS+JM");
                    class_room.setText("7B07");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("SD Lab -IV (C1/C2)");
                    next_teacher.setText("SS+JM");
                }



                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("SD Lab -IV (C1/C2)");
                    running_teacher.setText("TAB+MAT");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }

        // CSE 3-1 C END


        // CSE 3-2 A START

        if(routine.trim().equals("cse_3_2_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");
                    class_room.setText("7A06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Operating System");
                    next_teacher.setText("MMH");

                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Operating System");
                    running_teacher.setText("MMH");
                    class_room.setText("7A06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Information System Design and Software Engineering ");
                    next_teacher.setText("TAB ");

                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");
                    class_room.setText("7A06");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Microcontroller Based System Design Lab (A1/A2)");
                    next_teacher.setText("MHA+SMS ");

                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design Lab (A1/A2)");
                    running_teacher.setText("MHA+SMS");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Data Communication ");
                    next_teacher.setText("DR. REZAUL");

                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Information System Design and Software Engineering ");
                    next_teacher.setText("TAB");

                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");
                    class_room.setText("7C06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Microcontroller Based System Design ");
                    next_teacher.setText("MHA");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");
                    class_room.setText("7A03");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Operating System");
                    next_teacher.setText("MMH");

                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Operating System ");
                    running_teacher.setText("MMH");
                    class_room.setText("7A03");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Information System Design and Software Engineering LAB(A1/A2)");
                    next_teacher.setText("EH + TAB");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering LAB(A1/A2)");
                    running_teacher.setText("EH +TAB");
                    class_room.setText("7B05");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7C06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Industrial Law and Safety Management");
                    next_teacher.setText(" ");

                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7C07");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Data Communication ");
                    next_teacher.setText("DR. REZAUL");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C07");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Operating System LAB (A2) ");
                    next_teacher.setText("MMH+ SHARMIN");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Operating System LAB (A2)");
                    running_teacher.setText("MHA+SMS");
                    class_room.setText("7B08");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }





            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Operating System ");
                    running_teacher.setText("MMH");
                    class_room.setText("7A04");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");

                    next_class.setText("Information System Design and Software Engineering ");
                    next_teacher.setText("TAB ");

                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");
                    class_room.setText("7A04");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Microcontroller Based System Design ");
                    next_teacher.setText("MHA");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");
                    class_room.setText("7A04");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Software Development-V (A1/A2)");
                    running_teacher.setText("THM + TUSHAR");
                    class_room.setText("7B05");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Operating System LAB (A1) ");
                    next_teacher.setText("MMH+ SHARMIN");
                }



                else if(time >=10.30 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Operating System Lab (A1) ");
                    running_teacher.setText("MMH+ SHARMIN");
                    class_room.setText("7B03");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 3-2 A END

        // CSE 3-2 B START


        if(routine.trim().equals("cse_3_2_b")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");
                    class_room.setText("7C06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Information System Design and Software Engineering ");
                    next_teacher.setText("TAB");


                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");
                    class_room.setText("7C06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Operating System");
                    next_teacher.setText("MMH");

                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Operating System");
                    running_teacher.setText("MMH");
                    class_room.setText("7C06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");

                    class_room.setText("7C07");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Industrial Law and Safety Management");
                    next_teacher.setText("");

                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7C07");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Data Communication ");
                    next_teacher.setText("DR. REZAUL");

                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C07");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Software Development-V (B1/B2) ");
                    next_teacher.setText("THM + TUSHAR ");
                }


                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Software Development-V (B1/B2)");
                    running_teacher.setText("THM + TUSHAR");
                    class_room.setText("7B05");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Operating System");
                    running_teacher.setText("MMH");
                    class_room.setText("7A05");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Industrial Law and Safety Management");
                    next_teacher.setText("");
                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Microcontroller Based System Design ");
                    next_teacher.setText("MHA");

                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Operating System LAB (B1) ");
                    next_teacher.setText("MMH+ SHARMIN");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Operating System Lab (B1) ");
                    running_teacher.setText("MMH+ SHARMIN");
                    class_room.setText("7B08");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Data Communication ");
                    next_teacher.setText("DR. REZAUL");

                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Data Communication ");
                    running_teacher.setText("DR. REZAUL");
                    class_room.setText("7C06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Industrial Law and Safety Management");
                    next_teacher.setText("");

                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7C06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Microcontroller Based System Design Lab (B1/B2)");
                    next_teacher.setText("MHA+SMS ");
                }


                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design Lab (B1/B2)");
                    running_teacher.setText("MHA+SMS");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }





            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.30 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Operating System Lab (B2) ");
                    running_teacher.setText("MMH+ SHARMIN");
                    class_room.setText("7B08");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Microcontroller Based System Design ");
                    next_teacher.setText("MHA");
                }

                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Microcontroller Based System Design ");
                    running_teacher.setText("MHA");

                    class_room.setText("7A04");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Operating System ");
                    next_teacher.setText("MMH");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Operating System ");
                    running_teacher.setText("MMH");

                    class_room.setText("7A04");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Information System Design and Software Engineering ");
                    next_teacher.setText("TAB ");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering ");
                    running_teacher.setText("TAB");
                    class_room.setText("7A04");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);




                if(time >=10.30 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Information System Design and Software Engineering LAB(B1/B2)");
                    running_teacher.setText("EH +TAB");
                    class_room.setText("7B03");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }



        // CSE 3-2 B END


        // CSE 4-1 A START


        if(routine.trim().equals("cse_4_1_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");

                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("3.30-6.00");
                    next_class.setText("Distributed Database Systems LAB(B1/B2)");
                    next_teacher.setText("MIJ+AS");
                }

                else if(time >=15.3 && time <=18.0){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems LAB(B1/B2)");
                    running_teacher.setText("MIJ+AS");
                    class_room.setText("7B01");
                    running_schedule.setText("3.30-6.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);




                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Networks Lab(A1) ");
                    running_teacher.setText("MA+SAIRA");
                    class_room.setText("7B06");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Formal Languages & Compilers");
                    next_teacher.setText("MUH");
                }


                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM");




                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Industrial Management ");
                    next_teacher.setText(" ");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers  LAB(B1/B2)");
                    running_teacher.setText("MUH+RH");
                    class_room.setText("7B01");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Artificial Intelligence");
                    next_teacher.setText("THM");
                }





                else if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A05");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");

                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");

                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Industrial Law and Safety Management");
                    next_teacher.setText("");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Industrial Law and Safety Management");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7C06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");




                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7C06");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7C06");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence Lab(A1/A2) ");
                    running_teacher.setText("MWUK+THM");
                    class_room.setText("7B08");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);




                if(time >=10.30 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Networks Lab ");
                    running_teacher.setText("TA+SHIMANTO");
                    class_room.setText("7B03");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7A03");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7A03");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 4-1 A END


        // CSE 4-1 B START



        if(routine.trim().equals("cse_4_1_b")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers  LAB(B1/B2)");
                    running_teacher.setText("MUH+RH");
                    class_room.setText("7B07");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Industrial Management ");
                    next_teacher.setText(" ");
                }



                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");

                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Computer Networks ");
                    running_teacher.setText("TA");
                    class_room.setText("7A06");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7A06");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=8.0 && time <=10.30){

                    status.setText("Running");

                    running_class.setText("Distributed Database Systems LAB(B1/B2)");
                    running_teacher.setText("MIJ+MIM");
                    class_room.setText("7B03");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");
                }



                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7A03");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7A03");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A03");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Computer Networks Lab (B2)");
                    running_teacher.setText("TA+SAIRA");
                    class_room.setText("7B03");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");

                }





                else if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7A06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Artificial Intelligence");
                    next_teacher.setText("THM");

                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");

                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");

                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7A06");
                    running_schedule.setText("12.10-1.00");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence Lab(B1/B2) ");
                    running_teacher.setText("MWUK+THM");
                    class_room.setText("7B07");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Computer Networks Lab (B1)");
                    next_teacher.setText("TA+SHIMANTO");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Computer Networks Lab (B1)");
                    running_teacher.setText("TA+SHIMANTO");
                    class_room.setText("7B05");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }






            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7A06");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");


                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");
                    class_room.setText("7A06");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");

                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7A06");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);





                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");
                    class_room.setText("7C06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Industrial Management");
                    next_teacher.setText("");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A03");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 4-1 B END

        // CSE 4-1 C START



        if(routine.trim().equals("cse_4_1_c")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence Lab(C1/C2) ");
                    running_teacher.setText("MIM+THM");

                    class_room.setText("7B05");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");

                }



                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");

                    running_schedule.setText("Distributed Database Systems");
                    running_class.setText("MIJ");

                    class_room.setText("7C06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Industrial Management ");
                    next_teacher.setText(" ");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");
                    class_room.setText("7C06");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Computer Networks  ");
                    next_teacher.setText("TA");
                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Computer Networks ");
                    running_teacher.setText("TA");
                    class_room.setText("7C06");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");
                    class_room.setText("7A04");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Computer Networks ");
                    next_teacher.setText("TA");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7A04");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");



                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_schedule.setText("Formal Languages & Compilers ");
                    running_class.setText("MUH");
                    class_room.setText("7A04");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Computer Networks C1 ");
                    running_teacher.setText("MA+MAT");
                    class_room.setText("7B05");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Computer Networks C2 ");
                    running_teacher.setText("TA+ROBIN");
                    class_room.setText("7B07");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");

                }



                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");

                    running_schedule.setText("Formal Languages & Compilers ");
                    running_class.setText("MUH");

                    class_room.setText("7C07");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM ");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Artificial Intelligence ");
                    running_teacher.setText("THM");
                    class_room.setText("7C07");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Distributed Database Systems");
                    next_teacher.setText("MIJ");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_schedule.setText("Distributed Database Systems");
                    running_class.setText("MIJ");

                    class_room.setText("7C07");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=8.0 && time <=8.50){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems");
                    running_teacher.setText("MIJ");

                    class_room.setText("7A04");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("Industrial Management ");
                    next_teacher.setText("");


                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");

                    class_room.setText("7A04");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Formal Languages & Compilers ");
                    next_teacher.setText("MUH");
                }

                else if(time >=9.40 && time <=10.30){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers ");
                    running_teacher.setText("MUH");

                    class_room.setText("7A04");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Distributed Database Systems LAB(C1/C2)   AND    Formal Languages & Compilers  LAB(C1/C2)");
                    next_teacher.setText("MIJ+AS  AND  MUH+RH ");

                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Distributed Database Systems LAB(C1/C2)");
                    running_teacher.setText("MIJ+AS");
                    class_room.setText("7B06");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Formal Languages & Compilers  LAB(C1/C2)");
                    running_teacher.setText("MUH+RH");
                    class_room.setText("7B07");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);





                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Computer Networks  ");
                    running_teacher.setText("TA");
                    class_room.setText("7C06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Artificial Intelligence ");
                    next_teacher.setText("THM");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Artificial Intelligence");
                    running_teacher.setText("THM");

                    class_room.setText("7A03");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Industrial Management");
                    next_teacher.setText("");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Industrial Management ");
                    running_teacher.setText("");

                    class_room.setText("7A03");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // CSE 4-1 C END

        // CSE 4-2 A START


        if(routine.trim().equals("cse_4_2_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");

                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");


                    class_room.setText("7C06");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Network Programming ");
                    next_teacher.setText("MR.HASAN");

                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");
                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");


                    class_room.setText("7C06");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Pattern Recognition ");
                    next_teacher.setText("Dr. Hasanul ");


                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");
                    class_room.setText("7C06");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Network Programming Lab (A1/A2)");
                    next_teacher.setText("THM + POLLOB");


                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Network Programming Lab (B1/B2)");
                    running_teacher.setText("THM + POLLOB");
                    class_room.setText("7B03");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");
                    class_room.setText("7A06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Pattern Recognition     ");
                    next_teacher.setText("Dr. Hasanul");




                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");
                    class_room.setText("7A06");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Network Programming  ");
                    next_teacher.setText("MR.HASAN");



                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");
                    class_room.setText("7A06");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");


                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("MHWS");


                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("SR");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Telecommunication ");
                    next_teacher.setText("RR");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");

                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=13.5){

                    status.setText("Running");



                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");


                    class_room.setText("7A05");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("SR");



                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");

                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A05");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Digital Image Processing   ");
                    next_teacher.setText("MHWS");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("MHWS");
                    class_room.setText("7A05");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Digital Image Processing  Lab (A1/A2))");
                    running_teacher.setText("SR+MHWS");
                    class_room.setText("7B01");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Pattern Recognition Lab(B1/B2) ");
                    next_teacher.setText("MSA + ROMA");
                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition Lab(B1/B2) ");
                    running_teacher.setText("MSA + ROMA");
                    class_room.setText("7B07");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);




                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Computer Graphics Lab ");
                    running_teacher.setText("MHWS+PS");

                    class_room.setText("7B03");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("SR");

                }


                else if(time >=13.0 && time <=13.5){

                    status.setText("Running");



                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A04");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Telecommunication ");
                    next_teacher.setText("RR");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");
                    class_room.setText("7A04");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Computer Graphics");
                    next_teacher.setText(" MHWS");
                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Computer Graphics");
                    running_teacher.setText("MHWS");
                    class_room.setText("7A04");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }


        // cse 4-2 A END

        // CSE 4-2 B START

        if(routine.trim().equals("cse_4_2_b")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");


                    class_room.setText("7A07");
                    running_schedule.setText("10.30-11.20");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText("Pattern Recognition   ");
                    next_teacher.setText("Dr. Hasanul ");

                }



                else if(time >=11.2 && time <=12.1){

                    status.setText("Running");

                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");

                    class_room.setText("7A07");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("Network Programming  ");
                    next_teacher.setText("MR.HASAN");


                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");
                    class_room.setText("7A07");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Computer Graphics Lab  AND Digital Image Processing  Lab (B/2)*");
                    next_teacher.setText("SR+MHWS");


                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Computer Graphics Lab  AND Digital Image Processing  Lab (B/2)*");
                    running_teacher.setText("SR+MHWS");
                    class_room.setText("7B01");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }



            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");
                    class_room.setText("7A07");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-3.30");
                    next_class.setText("Network Programming");
                    next_teacher.setText("MR.HASAN");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Network Programming  ");
                    running_teacher.setText("MR.HASAN");
                    class_room.setText("7A07");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Pattern Recognition     ");
                    next_teacher.setText("Dr. Hasanul");




                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition   ");
                    running_teacher.setText("Dr. Hasanul ");

                    class_room.setText("7A07");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=13.0 && time <=13.5){

                    status.setText("Running");

                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Telecommunication ");
                    next_teacher.setText("RR");

                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");


                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");
                    class_room.setText("7A06");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("MHWS");

                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("MHWS");
                    class_room.setText("7A06");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=13.5){

                    status.setText("Running");


                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("MHWS");
                    class_room.setText("7A07");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Telecommunication ");
                    next_teacher.setText("RR");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");
                    class_room.setText("7A07");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("SR");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A07");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Pattern Recognition Lab(B/2)*  AND Network Lab (B/2)*)");
                    running_teacher.setText("MSA + ROMA");
                    class_room.setText("7B05");
                    running_schedule.setText("10.30-1.00");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }


            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);



                if(time >=13.0 && time <=13.5){

                    status.setText("Running");
                    running_class.setText("Telecommunication ");
                    running_teacher.setText("RR");
                    class_room.setText("7A06");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Computer Graphics");
                    next_teacher.setText(" MHWS");


                }

                else if(time >=13.5 && time <=14.4){

                    status.setText("Running");
                    running_class.setText("Computer Graphics");
                    running_teacher.setText("MHWS");
                    class_room.setText("7A04");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("Digital Image Processing  ");
                    next_teacher.setText("SR");


                }

                else if(time >=14.4 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Digital Image Processing  ");
                    running_teacher.setText("SR");
                    class_room.setText("7A04");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }

            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }





        // CSE 4-2 END

        // CSE 1-1 A START


        if(routine.trim().equals("cse_1_1_a")){

            if(current_week.trim().equals("Saturday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.5){

                    status.setText("Running");
                    running_class.setText("Physics");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("MATH - I");
                    next_teacher.setText("");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("MATH - I");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("CHEMISTRY");
                    next_teacher.setText("");
                }

                else if(time >=9.4 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("CHEMISTRY");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Physics Lab(A1/A2)");
                    next_teacher.setText("");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Physics Lab(A1/A2)");
                    running_teacher.setText("");
                    class_room.setText("5B03");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }

            }


            if(current_week.trim().equals("Sunday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("English Language Sessional(A2) ");
                    running_teacher.setText("");
                    class_room.setText("5A07");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-11.20");
                    next_class.setText("Elementary Structured Programming");
                    next_teacher.setText("SMAAM");
                }

                else if(time >=10.3 && time <=11.2){

                    status.setText("Running");
                    running_class.setText("Elementary Structured Programming");
                    running_teacher.setText("SMAAM");
                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("11.20-12.10");
                    next_class.setText(" Physics");
                    next_teacher.setText("");
                }

                else if(time >=11.2 && time <=12.1){


                    status.setText("Running");
                    running_class.setText("Physics ");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("11.20-12.10");
                    time_left.setText("");
                    next_schedule.setText("12.10-1.00");
                    next_class.setText("CHEMISTRY");
                    next_teacher.setText("");
                }

                else if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("CHEMISTRY");
                    running_teacher.setText("");
                    class_room.setText("7A05");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Elementary Structured Programming Lab   ");
                    next_teacher.setText("AR+Upoma ");

                }

                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Elementary Structured Programming Lab ");
                    running_teacher.setText(" AR+Upoma ");
                    class_room.setText("7B01");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }


            }


            if(current_week.trim().equals("Monday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);

                if(time >=8.0 && time <=8.5){

                    status.setText("Running");
                    running_class.setText("MATH - I");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("CHEMISTRY");
                    next_teacher.setText("");
                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("CHEMISTRY");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("CHEMISTRY");
                    next_teacher.setText("");
                }



                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Elementary Structured Programming Lab(A2) ");
                    running_teacher.setText(" AR+AS ");
                    class_room.setText("7B03");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }


            }

            if(current_week.trim().equals("Tuesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=8.5){

                    status.setText("Running");
                    running_class.setText("Physics");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.00-8.50");
                    time_left.setText("");
                    next_schedule.setText("8.50-9.40");
                    next_class.setText("English Language Sessional  ");
                    next_teacher.setText("");


                }

                else if(time >=8.5 && time <=9.4){


                    status.setText("Running");
                    running_class.setText("Critical Thinking & Communication ");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("8.50-9.40");
                    time_left.setText("");
                    next_schedule.setText("9.40-10.30");
                    next_class.setText("Elementary Structured Programming ");
                    next_teacher.setText("SMAAM");



                }

                else if(time >=9.4 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("Elementary Structured Programming ");
                    running_teacher.setText("SMAAM");
                    class_room.setText("7A03");
                    running_schedule.setText("9.40-10.30");
                    time_left.setText("");
                    next_schedule.setText("10.30-1.00");
                    next_class.setText("Introduction to Computer Systems ");
                    next_teacher.setText(" SK, SMS ");
                }

                else if(time >=10.3 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Introduction to Computer Systems )");
                    running_teacher.setText(" SK, SMS ");
                    class_room.setText("7B01");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }



            }

            if(current_week.trim().equals("Wednesday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);




                if(time >=12.1 && time <=13.0){

                    status.setText("Running");
                    running_class.setText("Elementary Structured Programming ");
                    running_teacher.setText("SMAAM");
                    class_room.setText("7A03");
                    running_schedule.setText("12.10-1.00");
                    time_left.setText("");
                    next_schedule.setText("1.00-1.50");
                    next_class.setText("MATH-I)");
                    next_teacher.setText("");
                }


                else if(time >=13.0 && time <=13.5){


                    status.setText("Running");
                    running_class.setText("MATH-I");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("1.00-1.50");
                    time_left.setText("");
                    next_schedule.setText("1.50-2.40");
                    next_class.setText("Critical Thinking & Communication ");
                    next_teacher.setText("");
                }

                else if(time >=13.5 && time <=14.4){


                    status.setText("Running");
                    running_class.setText("Critical Thinking & Communication ");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("1.50-2.40");
                    time_left.setText("");
                    next_schedule.setText("2.40-3.30");
                    next_class.setText("CHEMISTRY");
                    next_teacher.setText("");
                }


                else if(time >=14.4 && time <=15.3){


                    status.setText("Running");
                    running_class.setText("CHEMISTRY");
                    running_teacher.setText("");
                    class_room.setText("7A03");
                    running_schedule.setText("2.40-3.30");
                    time_left.setText("");
                    next_schedule.setText("none");
                    next_class.setText("none");
                    next_teacher.setText("none");
                }




            }

            if(current_week.trim().equals("Thursday")){

                String h= String.valueOf(current_hour);
                String m = String.valueOf(current_minute);

                if(current_minute<10){
                    m = "0"+current_minute;
                }

                String hm = h+"."+m;
                float time = Float.valueOf(hm);


                if(time >=8.0 && time <=10.3){

                    status.setText("Running");
                    running_class.setText("English Language Sessional(A1) ");
                    running_teacher.setText("");
                    class_room.setText("5A07");
                    running_schedule.setText("8.00-10.30");
                    time_left.setText("");
                    next_schedule.setText("1.00-3.30");
                    next_class.setText("Introduction to Computer Systems ");
                    next_teacher.setText(" SK, SMS ");
                }



                else if(time >=13.0 && time <=15.3){

                    status.setText("Running");
                    running_class.setText("Introduction to Computer Systems(A2 )");
                    running_teacher.setText(" SK, SMS ");

                    class_room.setText("7B01");
                    running_schedule.setText("1.00-3.30");
                    time_left.setText("");
                    next_schedule.setText("NONE");
                    next_class.setText("NONE");
                    next_teacher.setText("NONE");
                }
            }

            if(current_week.trim().equals("Friday")){
                System.out.println("Friday");
            }

        }



        //CSE 1-1 A END


        // CSE 1-1 B START








    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.home:
                Intent intent1 = new Intent(ClassDetails_Activity.this, Menu_Activity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.routine:

                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(ClassDetails_Activity.this, CgpaCalculator_Activity.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.my_details:

                break;

            case R.id.quiz:
                Intent intent21 = new Intent(ClassDetails_Activity.this, QuizReminder_Activity.class);
                startActivity(intent21);
                finish();

                break;

            case R.id.result:
                Intent intent3 = new Intent(ClassDetails_Activity.this, ResultInformationActivity.class);
                startActivity(intent3);
                finish();

                break;


            case R.id.profile:
                Intent intent6 = new Intent(ClassDetails_Activity.this, Profile_Activity.class);
                startActivity(intent6);
                finish();

                break;

            case R.id.about:
                Intent intent = new Intent(ClassDetails_Activity.this, Credit_activity.class);
                startActivity(intent);
                finish();

                break;

            case R.id.settings:
                Intent intent4 = new Intent(ClassDetails_Activity.this, Settings_Activity.class);
                startActivity(intent4);
                finish();

                break;

            case R.id.backup:
                Intent intent66 = new Intent(ClassDetails_Activity.this, BackupMark_Activity.class);
                startActivity(intent66);
                finish();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
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
            Intent intent = new Intent(ClassDetails_Activity.this, Settings_Activity.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Intent intent = new Intent(ClassDetails_Activity.this, Credit_activity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
