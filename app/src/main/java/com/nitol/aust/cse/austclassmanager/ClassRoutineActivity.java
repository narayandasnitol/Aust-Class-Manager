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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ClassRoutineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView yr, sem, sec, noRes, dept;
    DatabaseHelper myDb;
    ImageView routinePic;

    ClassRoutineHelper cR;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_class_routine);


        myDb = new DatabaseHelper(this);
        cR = new ClassRoutineHelper();


        yr = (TextView) findViewById(R.id.year_routine);
        sem = (TextView) findViewById(R.id.semester_routine);
        sec = (TextView) findViewById(R.id.section_routine);
        routinePic = (ImageView) findViewById(R.id.myRoutine);
        dept = (TextView) findViewById(R.id.textView_dept);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);


        getAllData();


    }

    public void getAllData(){

        Cursor result = myDb.getAllData();


        String student_department = "", student_year = "", student_semester = "",
                student_section = "", student_name ="";


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

            result.moveToFirst();
            if (result.getString(result.getColumnIndex("NAME")) != null){
                student_name += result.getString(result.getColumnIndex("NAME"));
                student_name += "\n";
            }
            result.moveToNext();

        }

        myDept = student_department.trim();
        myYear = student_year.trim();
        mySemester = student_semester.trim();
        mySection = student_section.trim();
        myName = student_name.trim();

        yr.setText(myYear);
        sem.setText(mySemester);
        sec.setText(mySection);
        dept.setText(myDept);


        String routine = cR.getRoutine(myDept,myYear,mySemester,mySection);

        if(routine.trim().equals("cse_1_1_a")){
            routinePic.setImageResource(R.drawable.cse_1_1_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_1_1_b")){
            routinePic.setImageResource(R.drawable.cse_1_1_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_1_1_c")){
            routinePic.setImageResource(R.drawable.cse_1_1_c);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_1_2_a")){
            routinePic.setImageResource(R.drawable.cse_1_2_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_1_2_b")){
            routinePic.setImageResource(R.drawable.cse_1_2_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_2_1_a")){
            routinePic.setImageResource(R.drawable.cse_2_1_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_2_1_b")){
            routinePic.setImageResource(R.drawable.cse_2_1_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_2_1_c")){
            routinePic.setImageResource(R.drawable.cse_2_1_c);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_2_2_a")){
            routinePic.setImageResource(R.drawable.cse_2_2_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_2_2_b")){
            routinePic.setImageResource(R.drawable.cse_2_2_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_3_1_a")){
            routinePic.setImageResource(R.drawable.cse_3_1_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_3_1_b")){
            routinePic.setImageResource(R.drawable.cse_3_1_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_3_1_c")){
            routinePic.setImageResource(R.drawable.cse_3_1_c);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_3_2_a")){
            routinePic.setImageResource(R.drawable.cse_3_2_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_3_2_b")){
            routinePic.setImageResource(R.drawable.cse_3_2_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_4_1_a")){
            routinePic.setImageResource(R.drawable.cse_4_1_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_4_1_b")){
            routinePic.setImageResource(R.drawable.cse_4_1_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_4_1_c")){
            routinePic.setImageResource(R.drawable.cse_4_1_c);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_4_2_a")){
            routinePic.setImageResource(R.drawable.cse_4_2_a);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else if(routine.trim().equals("cse_4_2_b")){
            routinePic.setImageResource(R.drawable.cse_4_2_b);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(routinePic);
            photoViewAttacher.update();
        }
        else{

        }


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
                Intent intent1 = new Intent(ClassRoutineActivity.this, MenuActivity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.routine:

                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(ClassRoutineActivity.this, CgpaCalculator.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.details:
                Toast.makeText(getApplicationContext(),"Class Details",Toast.LENGTH_SHORT).show();

                break;

            case R.id.quiz:
                Toast.makeText(getApplicationContext(),"Quiz Reminder",Toast.LENGTH_SHORT).show();

                break;

            case R.id.result:
                Intent intent3 = new Intent(ClassRoutineActivity.this, ResultCheckActivity.class);
                startActivity(intent3);
                finish();

                break;


            case R.id.profile:
                Intent intent6 = new Intent(ClassRoutineActivity.this, ProfileActivity.class);
                startActivity(intent6);
                finish();

                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();

                break;

            case R.id.settings:
                Intent intent4 = new Intent(ClassRoutineActivity.this, Settings.class);
                startActivity(intent4);
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
            Intent intent = new Intent(ClassRoutineActivity.this, Settings.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Toast.makeText(getApplicationContext(),"This is About !", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}