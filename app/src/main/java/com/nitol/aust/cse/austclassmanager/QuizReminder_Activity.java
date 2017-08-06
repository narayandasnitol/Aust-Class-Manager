package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizReminder_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    FloatingActionButton fabButton;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView lv;
    TextView tv;
    QuizDatabaseHelper myDb;
    ProfileDatabaseHelper myDb2;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayList<String> arrayList4 = new ArrayList<>();
    ArrayList<String> arrayList5 = new ArrayList<>();
    ArrayList<String> arrayList6 = new ArrayList<>();
    ArrayList<String> arrayList7 = new ArrayList<>();

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_quiz_reminder);

        fabButton = (FloatingActionButton) findViewById(R.id.floating_button);

        lv =  (ListView) findViewById(R.id.custom_listView);
        myDb = new QuizDatabaseHelper(this);
        myDb2 = new ProfileDatabaseHelper(this);
        tv = (TextView) findViewById(R.id.textView111);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(3).setChecked(true);




        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizReminder_Activity.this, SetQuiz_Activity.class);
                startActivity(intent);
                finish();
            }
        });


        Cursor data = myDb.getAllData();

        if(data.getCount() == 0){
            tv.setText("Nothing Found!");
        }
        else{
            while(data.moveToNext()){
                arrayList.add(data.getString(1));
                arrayList2.add(data.getString(2));
                arrayList3.add(data.getString(3));
                arrayList4.add(data.getString(4));
                arrayList5.add(data.getString(5));
                arrayList6.add(data.getString(6));
                arrayList7.add(data.getString(7));


                CustomQuizAdapter customAdapter = new CustomQuizAdapter(QuizReminder_Activity.this,
                        arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7);
                customAdapter.notifyDataSetChanged();
                lv.setAdapter(customAdapter);
            }
        }

        getAllData();


    }


    public void getAllData() {

        Cursor result = myDb2.getAllData();


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
                Intent intent = new Intent(QuizReminder_Activity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });
*/

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
                Intent intent1 = new Intent(QuizReminder_Activity.this, Menu_Activity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.routine:
                Intent intent22 = new Intent(QuizReminder_Activity.this, ClassRoutineActivity.class);
                startActivity(intent22);
                finish();
                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(QuizReminder_Activity.this, CgpaCalculator_Activity.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.my_details:
                Intent intent222 = new Intent(QuizReminder_Activity.this, ClassDetails_Activity.class);
                startActivity(intent222);
                finish();

                break;

            case R.id.quiz:

                break;

            case R.id.result:
                Intent intent3 = new Intent(QuizReminder_Activity.this, ResultInformationActivity.class);
                startActivity(intent3);
                finish();

                break;


            case R.id.profile:
                Intent intent6 = new Intent(QuizReminder_Activity.this, Profile_Activity.class);
                startActivity(intent6);
                finish();

                break;

            case R.id.about:
                Intent intent = new Intent(QuizReminder_Activity.this, Credit_activity.class);
                startActivity(intent);

                break;

            case R.id.settings:
                Intent intent4 = new Intent(QuizReminder_Activity.this, Settings_Activity.class);
                startActivity(intent4);
                finish();

                break;

            case R.id.backup:
                Intent intent66 = new Intent(QuizReminder_Activity.this, BackupMark_Activity.class);
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
            Intent intent = new Intent(QuizReminder_Activity.this, Settings_Activity.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Intent intent = new Intent(QuizReminder_Activity.this, Credit_activity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}
