package com.nitol.aust.cse.austclassmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class BackupMark extends AppCompatActivity {

    EditText q1, q2, q3, q4, assi;
    Button cal;
    TextView show_mark;

    int n1, n2, n3, n4, ass, sum=0, final_sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_mark);

        q1 = (EditText) findViewById(R.id.q1);
        q2 = (EditText) findViewById(R.id.q2);
        q3 = (EditText) findViewById(R.id.q3);
        q4 = (EditText) findViewById(R.id.q4);
        assi = (EditText) findViewById(R.id.ass);
        cal = (Button) findViewById(R.id.btn_cal);
        show_mark = (TextView) findViewById(R.id.show_mark);



        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String q11, q22, q33, q44, assi1;
                q11= q1.getText().toString();
                q22 = q2.getText().toString();
                q33 = q3.getText().toString();
                q44 = q4.getText().toString();
                assi1 = assi.getText().toString();


                if(q11.length() == 0 || q22.length() == 0 || q33.length() == 0 || q44.length() == 0 || assi1.length() == 0){

                    Toast.makeText(getApplicationContext(), "Please fill up all the information", Toast.LENGTH_SHORT).show();

                }
                else{

                    n1 = Integer.valueOf(q1.getText().toString());
                    n2 = Integer.valueOf(q2.getText().toString());
                    n3 = Integer.valueOf(q3.getText().toString());
                    n4 = Integer.valueOf(q4.getText().toString());
                    ass = Integer.valueOf(assi.getText().toString());

                    if(n1 >10 || n2>10 || n3 >10 || n4 > 10 || ass > 10){
                        Toast.makeText(getApplicationContext(), "Marks can't be greater then 10", Toast.LENGTH_SHORT).show();
                        sum = 0;
                    }
                    else{
                        int[] array_marks = {n1, n2, n3, n4};

                        Arrays.sort(array_marks);

                        for(int i=1; i<array_marks.length; i++){
                            sum += array_marks[i];
                        }

                        final_sum = ((sum*2)/3)+ass;

                        show_mark.setText("Backup Mark: "+String.valueOf(final_sum));

                        sum = 0;
                    }

                }

            }
        });

    }
}
