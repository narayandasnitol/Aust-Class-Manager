package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultCheckActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TabLayout t1;
    private ViewPager vp1;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

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
                Intent intent0 = new Intent(ResultCheckActivity.this, MenuActivity.class);
                startActivity(intent0);
                finish();

                break;

            case R.id.routine:
                Intent intent = new Intent(ResultCheckActivity.this, ClassRoutineActivity.class);
                startActivity(intent);
                finish();

                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(ResultCheckActivity.this, CgpaCalculator.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.my_details:
                Toast.makeText(getApplicationContext(),"Class Details",Toast.LENGTH_SHORT).show();

                break;

            case R.id.quiz:
                Toast.makeText(getApplicationContext(),"Quiz Reminder",Toast.LENGTH_SHORT).show();

                break;

            case R.id.result:

                break;


            case R.id.profile:
                Intent intent3 = new Intent(ResultCheckActivity.this, ProfileActivity.class);
                startActivity(intent3);
                finish();

                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();

                break;

            case R.id.settings:
                Intent intent4 = new Intent(ResultCheckActivity.this, Settings.class);
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
            Intent intent = new Intent(ResultCheckActivity.this, Settings.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Toast.makeText(getApplicationContext(),"This is About !", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }




}
