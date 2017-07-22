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
import android.widget.Toast;

import java.util.ArrayList;

public class QuizReminder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    FloatingActionButton fabButton;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView lv;
    QuizDatabaseHelper myDb;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayList<String> arrayList4 = new ArrayList<>();
    ArrayList<String> arrayList5 = new ArrayList<>();
    ArrayList<String> arrayList6 = new ArrayList<>();
    ArrayList<String> arrayList7 = new ArrayList<>();
    ArrayList<Integer> arrayList8 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_quiz_reminder);

        fabButton = (FloatingActionButton) findViewById(R.id.floating_button);

        lv =  (ListView) findViewById(R.id.custom_listView);
        myDb = new QuizDatabaseHelper(this);

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
                Intent intent = new Intent(QuizReminder.this, SetQuiz.class);
                startActivity(intent);
                finish();
            }
        });


        Cursor data = myDb.getAllData();

        if(data.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nothing found!", Toast.LENGTH_SHORT).show();
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

                int[] myImage = {R.drawable.delete,R.drawable.delete,R.drawable.delete,R.drawable.delete,
                        R.drawable.delete,R.drawable.delete,R.drawable.delete,R.drawable.delete,
                        R.drawable.delete,R.drawable.delete,R.drawable.delete,R.drawable.delete,R.drawable.delete,
                        R.drawable.delete, R.drawable.delete, R.drawable.delete, R.drawable.delete, R.drawable.delete};

                Quiz_list_adapter customAdapter = new Quiz_list_adapter(QuizReminder.this,
                        arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7);
                customAdapter.notifyDataSetChanged();
                lv.setAdapter(customAdapter);
            }
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
                Intent intent1 = new Intent(QuizReminder.this, MenuActivity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.routine:
                Intent intent22 = new Intent(QuizReminder.this, ClassRoutineActivity.class);
                startActivity(intent22);
                finish();
                break;

            case R.id.cgpa:
                Intent intent2 = new Intent(QuizReminder.this, CgpaCalculator.class);
                startActivity(intent2);
                finish();

                break;

            case R.id.my_details:
                Toast.makeText(getApplicationContext(),"Class Details",Toast.LENGTH_SHORT).show();

                break;

            case R.id.quiz:

                break;

            case R.id.result:
                Intent intent3 = new Intent(QuizReminder.this, ResultCheckActivity.class);
                startActivity(intent3);
                finish();

                break;


            case R.id.profile:
                Intent intent6 = new Intent(QuizReminder.this, ProfileActivity.class);
                startActivity(intent6);
                finish();

                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();

                break;

            case R.id.settings:
                Intent intent4 = new Intent(QuizReminder.this, Settings.class);
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
            Intent intent = new Intent(QuizReminder.this, Settings.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Toast.makeText(getApplicationContext(),"This is About !", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

}
