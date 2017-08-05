package com.nitol.aust.cse.austclassmanager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Credit_activity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv = (TextView) findViewById(R.id.credit_title);
        tv2 = (TextView) findViewById(R.id.credit_dept);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/che.ttf");
        tv.setTypeface(typeface);
        tv2.setTypeface(typeface);
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
