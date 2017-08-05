package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Settings_Activity extends AppCompatActivity{

    ListView lv;
    Toolbar toolbar;
    AppCompatEditText username;
    LinearLayout LL;
    TextInputLayout userLayout;
    ProfileDatabaseHelper myDb;
    Button update;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    ProfileDepartmentChange dp;
    ProfileYearChange cp;
    ProfileSemesterChange sp;
    ProfileSectionChange sc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myDb = new ProfileDatabaseHelper(this);

        lv = (ListView) findViewById(R.id.settings_listview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String[] item = {"Change Name", "Change Department", "Change Academic Year",
                "Change Semester", "Change Section"};

        ArrayAdapter adapter = new ArrayAdapter(this ,R.layout.settings_list, R.id.textView4, item);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(item[position].equals("Change Name")){

                    AlertDialog.Builder mBuilder = new  AlertDialog.Builder(Settings_Activity.this);
                    View mView = getLayoutInflater().inflate(R.layout.change_name, null);

                    username = (AppCompatEditText) mView.findViewById(R.id.username_TextField);
                    userLayout = (TextInputLayout) mView.findViewById(R.id.usernameTextInput);
                    LL = (LinearLayout) mView.findViewById(R.id.myLayout) ;
                    update = (Button) mView.findViewById(R.id.button);

                    username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {

                            if(username.getText().toString().isEmpty()){

                                userLayout.setErrorEnabled(true);
                                userLayout.setError("Enter Username to Change");

                            }else{
                                userLayout.setErrorEnabled(false);
                            }
                        }
                    });

                    username.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if(username.getText().toString().isEmpty()){

                                userLayout.setErrorEnabled(true);
                                userLayout.setError("Please enter your username first!");

                            }else{
                                userLayout.setErrorEnabled(false);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    userLayout.setCounterEnabled(true);

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(username.getText().toString().isEmpty()){
                                Toast.makeText(getApplicationContext(),"please enter your username",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                getAllData();
                                myName = username.getText().toString();
                                updateAll();
                            }

                        }
                    });

                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();

                }

                else if(item[position].equals("Change Department")){

                    dp = new ProfileDepartmentChange();
                    dp.show(getSupportFragmentManager(),"dp");

                }

                else if(item[position].equals("Change Academic Year")){
                    cp = new ProfileYearChange();
                    cp.show(getSupportFragmentManager(),"cp");
                }

                else if(item[position].equals("Change Semester")){
                    sp = new ProfileSemesterChange();
                    sp.show(getSupportFragmentManager(),"sp");
                }

                else if(item[position].equals("Change Section")){
                    sc = new ProfileSectionChange();
                    sc.show(getSupportFragmentManager(),"sc");
                }


            }
        });
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

    }

    public void updateAll(){
        String id = "1";
        String name = myName;
        String dept = myDept;
        String yr =  myYear;
        String sem = mySemester;
        String sec = mySection;

        boolean isUpdated = myDb.updateData(id, name, dept, yr, sem, sec);

        if(isUpdated) {
            Toast.makeText(getApplicationContext(), "Data Updated !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Settings_Activity.this, Settings_Activity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Data not Updated !", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if ( id == android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
