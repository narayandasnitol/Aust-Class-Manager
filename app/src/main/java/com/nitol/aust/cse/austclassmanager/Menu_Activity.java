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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Menu_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ProfileDatabaseHelper myDb;
    TextView tv;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        tv = (TextView) findViewById(R.id.textView18);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_2);
        tv.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv.setAnimation(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //tv.setAnimation(animation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
               // tv.setAnimation(animation);
            }
        });


        myDb = new ProfileDatabaseHelper(this);
        getAllData();

    }




    public void classRoutine(View view) {
        Intent intent = new Intent(Menu_Activity.this, ClassRoutineActivity.class);
        startActivity(intent);
    }

    public void cgpaCalculator(View view){
        Intent intent = new Intent(Menu_Activity.this, CgpaCalculator_Activity.class);
        startActivity(intent);
    }

    public  void quizReminder(View view){
        Intent intent = new Intent(Menu_Activity.this, QuizReminder_Activity.class);
        startActivity(intent);
    }

    public  void Settings(View view){
        Intent intent= new Intent(Menu_Activity.this, Settings_Activity.class);
        startActivity(intent);
    }

    public void Result(View view){
        Intent intent= new Intent(Menu_Activity.this, ResultInformationActivity.class);
        startActivity(intent);
    }

    public void classDetails(View view){
        Intent intent= new Intent(Menu_Activity.this, ClassDetails_Activity.class);
        startActivity(intent);
    }



    public void getAllData() {

        Cursor result = myDb.getAllData();


        String student_department = "", student_year = "", student_semester = "",
                student_section = "", student_name = "";


        result.moveToFirst();
        while (!result.isAfterLast()) {

            if (result.getString(result.getColumnIndex("NAME")) != null) {
                student_name += result.getString(result.getColumnIndex("NAME"));
                student_name += "\n";
            }
            result.moveToNext();

            result.moveToFirst();
            if (result.getString(result.getColumnIndex("DEPARTMENT")) != null) {
                student_department += result.getString(result.getColumnIndex("DEPARTMENT"));
                student_department += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("YEAR")) != null) {
                student_year += result.getString(result.getColumnIndex("YEAR"));
                student_year += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SEMESTER")) != null) {
                student_semester += result.getString(result.getColumnIndex("SEMESTER"));
                student_semester += "\n";
            }
            result.moveToNext();


            result.moveToFirst();
            if (result.getString(result.getColumnIndex("SECTION")) != null) {
                student_section += result.getString(result.getColumnIndex("SECTION"));
                student_section += "\n";
            }
            result.moveToNext();

        }

        myName = student_name.trim();
        myDept = student_department.trim();
        myYear = student_year.trim();
        mySemester = student_semester.trim();
        mySection = student_section.trim();

        View hView =  navigationView.getHeaderView(0);

        TextView nav_name = (TextView)hView.findViewById(R.id.header_name);
        nav_name.setText(myName);


        TextView nav_dept = (TextView)hView.findViewById(R.id.header_dept);
        nav_dept.setText(myDept);

        TextView nav_year = (TextView)hView.findViewById(R.id.header_year);
        nav_year.setText(myYear);

        TextView nav_semester = (TextView)hView.findViewById(R.id.header_semester);
        nav_semester.setText(mySemester);

        TextView nav_section = (TextView)hView.findViewById(R.id.header_Section);
        nav_section.setText(mySection);

        /*CircleImageView circleImageView = (CircleImageView)hView.findViewById(R.id.header_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });*/


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

                break;

            case R.id.routine:
                Intent intent = new Intent(Menu_Activity.this, ClassRoutineActivity.class);
                startActivity(intent);


                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(Menu_Activity.this, CgpaCalculator_Activity.class);
                startActivity(intent2);

                break;

            case R.id.my_details:
                Intent intent32 = new Intent(Menu_Activity.this, ClassDetails_Activity.class);
                startActivity(intent32);


                break;

            case R.id.quiz:
                Intent intent12 = new Intent(Menu_Activity.this, QuizReminder_Activity.class);
                startActivity(intent12);


                break;

            case R.id.result:
                Intent intent3 = new Intent(Menu_Activity.this, ResultInformationActivity.class);
                startActivity(intent3);


                break;

            case R.id.profile:
                Intent intent4 = new Intent(Menu_Activity.this, Profile_Activity.class);
                startActivity(intent4);


                break;

            case R.id.about:
                Intent intent55 = new Intent(Menu_Activity.this, Credit_activity.class);
                startActivity(intent55);

                break;

            case R.id.settings:
                Intent intent5 = new Intent(Menu_Activity.this, Settings_Activity.class);
                startActivity(intent5);


                break;

            case R.id.backup:
                Intent intent6 = new Intent(Menu_Activity.this, BackupMark_Activity.class);
                startActivity(intent6);


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
            Intent intent3 = new Intent(Menu_Activity.this, Settings_Activity.class);
            startActivity(intent3);

        }
        else if(id == R.id.tool_about){

           Intent intent = new Intent(Menu_Activity.this, Credit_activity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}

