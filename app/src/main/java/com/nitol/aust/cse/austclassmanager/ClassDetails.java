package com.nitol.aust.cse.austclassmanager;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClassDetails extends AppCompatActivity {

    TextView status, running_class, running_teacher, class_room, running_schedule,
            time_left, next_schedule, next_class, next_teacher;

    DatabaseHelper myDb;
    ClassRoutineHelper cR;

    String myDept, myYear, mySemester, mySection, current_week;
    int current_hour, current_minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        status = (TextView) findViewById(R.id.status);
        running_class = (TextView) findViewById(R.id.running_class);
        running_teacher = (TextView) findViewById(R.id.running_teacher);
        class_room = (TextView) findViewById(R.id.class_room);
        running_schedule = (TextView) findViewById(R.id.current_schedule);
        time_left = (TextView) findViewById(R.id.time_left);
        next_schedule = (TextView) findViewById(R.id.next_schedule);
        next_class = (TextView) findViewById(R.id.next_class);
        next_teacher = (TextView) findViewById(R.id.next_teacher);

        myDb = new DatabaseHelper(this);
        cR = new ClassRoutineHelper();

        Calendar c = Calendar.getInstance();

        current_hour = c.get(Calendar.HOUR_OF_DAY);
        current_minute = c.get(Calendar.MINUTE);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        current_week = sdf.format(d);


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





    }
}
