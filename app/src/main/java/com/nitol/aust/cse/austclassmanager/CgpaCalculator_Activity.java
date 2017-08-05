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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CgpaCalculator_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ListView lv;
    Toolbar toolbar;
    Button add_btn;
    TextView serial, credit, gpa, total, final_cgpa;
    EditText credit_in, gpa_in;
    int add_click = 1;
    double cI = 0, tG = 0;
    String my_grade;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ProfileDatabaseHelper myDb;


    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayList<String> arrayList4 = new ArrayList<>();

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_draw_cgpa);

        myDb = new ProfileDatabaseHelper(this);

        lv = (ListView) findViewById(R.id.id_listView);
        add_btn = (Button) findViewById(R.id.id_add_btn);
        credit_in = (EditText) findViewById(R.id.id_credit_input);
        gpa_in = (EditText) findViewById(R.id.id_gpa_input);
        serial = (TextView) findViewById(R.id.textView_1);
        credit = (TextView) findViewById(R.id.textView_2);
        gpa = (TextView) findViewById(R.id.textView_3);
        total = (TextView) findViewById(R.id.textView_4);
        final_cgpa = (TextView) findViewById(R.id.final_cgpa);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);

        addResult();
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
                Intent intent = new Intent(CgpaCalculator_Activity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });


    }


    public void addResult(){

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String s = credit_in.getText().toString().trim();
                String ss = gpa_in.getText().toString().trim();

                if(s.length() == 0 || ss.length() == 0){
                    Toast.makeText(getApplicationContext(), "please fill all the information!",
                            Toast.LENGTH_SHORT).show();

                }

                else{

                    DecimalFormat dF = new DecimalFormat("#.000");

                    double creditInput = Double.parseDouble(credit_in.getText().toString());

                    double gpaInput = Double.parseDouble(gpa_in.getText().toString());

                    if(gpaInput <=4.0 && gpaInput>3.75){
                        my_grade = "A+";
                    }
                    else if(gpaInput <=3.75 && gpaInput>3.5){
                        my_grade = "A";
                    }
                    else if(gpaInput <=3.5 && gpaInput>3.25){
                        my_grade = "A-";
                    }
                    else if(gpaInput <=3.25 && gpaInput>3.0){
                        my_grade = "B+";
                    }
                    else if(gpaInput <=3.0 && gpaInput>2.75){
                        my_grade = "B";
                    }
                    else if(gpaInput <=2.75 && gpaInput>2.5){
                        my_grade = "B-";
                    }
                    else if(gpaInput <=2.5 && gpaInput>2.25){
                        my_grade = "C+";
                    }
                    else if(gpaInput <=2.25 && gpaInput>2.0){
                        my_grade = "C";
                    }
                    else if(gpaInput == 2.0){
                        my_grade = "D";
                    }
                    else{
                        my_grade = "F";
                    }

                    if(gpaInput >4){
                        Toast.makeText(getApplicationContext(), "GPA can't be more than 4!!!",
                                Toast.LENGTH_SHORT).show();
                        gpa_in.setText("");
                    }
                    else{

                        double total_gpa = creditInput * gpaInput;
                        cI += creditInput;
                        tG += total_gpa;

                        Double formattedValue = Double.valueOf(dF.format(tG / cI));
                        String finalCGPA = "CGPA = "+Double.toString(formattedValue);

                        if(finalCGPA.length() == 10)
                            finalCGPA = "CGPA = "+Double.toString(formattedValue)+"0";

                        final_cgpa.setText(finalCGPA);
                        gpa_in.setText("");


                        String my_id = Integer.toString(add_click);

                        String my_credit = Double.toString(creditInput);
                        if(my_credit.trim().length() == 3)
                            my_credit = Double.toString(creditInput)+"0";


                        String my_gpa = Double.toString(gpaInput);
                        if(my_gpa.trim().length() == 3)
                            my_gpa = Double.toString(gpaInput)+"0";



                        arrayList.add(my_id);
                        arrayList2.add(my_credit);
                        arrayList3.add(my_gpa);
                        arrayList4.add(my_grade);

                        CustomCgpaAdapter customAdapter = new CustomCgpaAdapter(CgpaCalculator_Activity.this,
                                arrayList, arrayList2, arrayList3, arrayList4);
                        customAdapter.notifyDataSetChanged();
                        lv.setAdapter(customAdapter);


                        Toast.makeText(getApplicationContext(),"Input Successful!", Toast.LENGTH_SHORT).show();
                        add_click++;
                    }

                }

            }
        });

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

                Intent intent1 = new Intent(CgpaCalculator_Activity.this, Menu_Activity.class);
                startActivity(intent1);
                finish();

                break;

            case R.id.routine:

                Intent intent2 = new Intent(CgpaCalculator_Activity.this, ClassRoutineActivity.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.cgpa:

                break;

            case R.id.my_details:
                Intent intent22 = new Intent(CgpaCalculator_Activity.this, ClassDetails_Activity.class);
                startActivity(intent22);
                finish();

                break;

            case R.id.quiz:
                Intent intent223 = new Intent(CgpaCalculator_Activity.this, QuizReminder_Activity.class);
                startActivity(intent223);
                finish();

                break;

            case R.id.result:
                Intent intent3 = new Intent(CgpaCalculator_Activity.this, ResultInformationActivity.class);
                startActivity(intent3);
                finish();

                break;


            case R.id.profile:
                Intent intent6 = new Intent(CgpaCalculator_Activity.this, Profile_Activity.class);
                startActivity(intent6);
                finish();

                break;

            case R.id.about:
                Intent intent = new Intent(CgpaCalculator_Activity.this, Credit_activity.class);
                startActivity(intent);
                break;

            case R.id.settings:
                Intent intent4 = new Intent(CgpaCalculator_Activity.this, Settings_Activity.class);
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
            Intent intent = new Intent(CgpaCalculator_Activity.this, Settings_Activity.class);
            startActivity(intent);

        }
        else if(id == R.id.tool_about){

            Intent intent = new Intent(CgpaCalculator_Activity.this, Credit_activity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

}
