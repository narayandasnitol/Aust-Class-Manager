package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_Activity extends AppCompatActivity {

    Button signupButton;
    TextView tv;
    ProfileDatabaseHelper myDb;
    AppCompatEditText username;
    LinearLayout LL;
    TextInputLayout userLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_layout);


        myDb = new ProfileDatabaseHelper(this);
        signupButton = (Button) findViewById(R.id.btn_signup);


        Cursor result = myDb.getAllData();

        if(result.getCount() >= 1){
            Intent intent = new Intent(Signup_Activity.this, Menu_Activity.class);
            startActivity(intent);
            finish();
        }

        else{
            saveData();
        }

    }

    public void saveData(){

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new  AlertDialog.Builder(Signup_Activity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_alert_signup, null);


                final Spinner dept = (Spinner) mView.findViewById(R.id.department_spinner);
                final Spinner year = (Spinner) mView.findViewById(R.id.year_spinner);
                final Spinner semester = (Spinner) mView.findViewById(R.id.semester_spinner);
                final Spinner section = (Spinner) mView.findViewById(R.id.section_spinner);
                final Button signup = (Button) mView.findViewById(R.id.signup_btn);
                final Button cancel = (Button) mView.findViewById(R.id.cancel_btn);
                username = (AppCompatEditText) mView.findViewById(R.id.username_TextField);
                userLayout = (TextInputLayout) mView.findViewById(R.id.usernameTextInput);
                LL = (LinearLayout) mView.findViewById(R.id.myLayout) ;

                username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if(username.getText().toString().isEmpty()){

                            userLayout.setErrorEnabled(true);
                            userLayout.setError("Enter Username");

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


                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(username.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"please enter your username", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            String myName = username.getText().toString();
                            String myDept = String.valueOf(dept.getSelectedItem());
                            String myYear = String.valueOf(year.getSelectedItem());
                            String mySemester = String.valueOf(semester.getSelectedItem());
                            String mySection = String.valueOf(section.getSelectedItem());

                            myDb.insertData(myName, myDept, myYear, mySemester, mySection);

                            Intent intent = new Intent(Signup_Activity.this, Menu_Activity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dialog.dismiss();
                    }
                });
            }
        });

    }


}
