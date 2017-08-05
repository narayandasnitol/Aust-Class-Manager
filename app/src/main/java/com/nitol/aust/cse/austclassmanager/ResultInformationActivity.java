package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultInformationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TabLayout t1;
    private ViewPager vp1;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ProfileDatabaseHelper myDb;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_result);

        t1 = (TabLayout) findViewById(R.id.tab1);
        vp1 = (ViewPager) findViewById(R.id.ViewPager1);


        setUpMyViewPager(vp1);
        t1.setupWithViewPager(vp1);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(5).setChecked(true);

        myDb = new ProfileDatabaseHelper(this);


        getAllData();

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

        CircleImageView circleImageView = (CircleImageView)hView.findViewById(R.id.header_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultInformationActivity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });


    }


    void setUpMyViewPager(ViewPager vp){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addMyFragment(new WebViewEvents(),"News & Events");
        viewPagerAdapter.addMyFragment(new WebViewAcademic(),"Academic Calendar");
        viewPagerAdapter.addMyFragment(new WebViewTheory(),"Theory Results");
        viewPagerAdapter.addMyFragment(new WebViewLab(),"Lab Results");
        viewPagerAdapter.addMyFragment(new WebViewTeacher(),"Faculty Members");


        vp.setAdapter(viewPagerAdapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> my_list = new ArrayList<Fragment>();
        private final List<String> my_title = new ArrayList<String>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return my_list.get(position);
        }

        @Override
        public int getCount() {
            return my_list.size();
        }


        void addMyFragment(Fragment f, String title){
            my_list.add(f);
            my_title.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return my_title.get(position);
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
                Intent intent0 = new Intent(ResultInformationActivity.this, Menu_Activity.class);
                startActivity(intent0);
                finish();

                break;

            case R.id.routine:
                Intent intent = new Intent(ResultInformationActivity.this, ClassRoutineActivity.class);
                startActivity(intent);
                finish();

                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(ResultInformationActivity.this, CgpaCalculator_Activity.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.my_details:
                Intent intent12 = new Intent(ResultInformationActivity.this, ClassDetails_Activity.class);
                startActivity(intent12);
                finish();

                break;

            case R.id.quiz:
                Intent intent22 = new Intent(ResultInformationActivity.this, QuizReminder_Activity.class);
                startActivity(intent22);
                finish();

                break;

            case R.id.result:

                break;


            case R.id.profile:
                Intent intent3 = new Intent(ResultInformationActivity.this, Profile_Activity.class);
                startActivity(intent3);
                finish();

                break;

            case R.id.about:
                Intent intent222 = new Intent(ResultInformationActivity.this, Credit_activity.class);
                startActivity(intent222);

                break;

            case R.id.settings:
                Intent intent4 = new Intent(ResultInformationActivity.this, Settings_Activity.class);
                startActivity(intent4);
                finish();

                break;

            case R.id.backup:
                Intent intent66 = new Intent(ResultInformationActivity.this, BackupMark_Activity.class);
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
            Intent intent = new Intent(ResultInformationActivity.this, Settings_Activity.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Intent intent = new Intent(ResultInformationActivity.this, Credit_activity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }




}
