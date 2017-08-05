package com.nitol.aust.cse.austclassmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz_list_adapter extends BaseAdapter{


    Context context;
    ArrayList<String> aL1 = new ArrayList<>();
    ArrayList<String> aL2 = new ArrayList<>();
    ArrayList<String> aL3 = new ArrayList<>();
    ArrayList<String> aL4 = new ArrayList<>();
    ArrayList<String> aL5 = new ArrayList<>();
    ArrayList<String> aL6 = new ArrayList<>();
    ArrayList<String> aL7 = new ArrayList<>();

    QuizDatabaseHelper myDb;
    Intent intent;
    AlarmManager alarmManager;



    private static LayoutInflater  inflater = null;

    public Quiz_list_adapter(QuizReminder mainAct, ArrayList<String> a1,ArrayList<String> a2,ArrayList<String> a3,
                             ArrayList<String> a4,ArrayList<String> a5,ArrayList<String> a6,
                             ArrayList<String> a7){
        aL1 = a1;
        aL2 = a2;
        aL3 = a3;
        aL4 = a4;
        aL5 = a5;
        aL6 = a6;
        aL7 = a7;

        context = mainAct;

        inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        myDb = new QuizDatabaseHelper(context);
    }


    @Override
    public int getCount() {
        return aL1.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MyHolder{
        TextView tv1, tv2, tv3, tv4;
        ImageView iv, iv2;


    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final MyHolder myHolder = new MyHolder();
        final View myView;

        myView = inflater.inflate(R.layout.custom_quiz_layout, null);


        myHolder.tv1 = (TextView) myView.findViewById(R.id.my_title);
        myHolder.tv2 = (TextView) myView.findViewById(R.id.my_details);
        myHolder.tv3 = (TextView) myView.findViewById(R.id.my_time);
        myHolder.tv4 = (TextView) myView.findViewById(R.id.my_date);
        myHolder.iv = (ImageView) myView.findViewById(R.id.delete_image);
        //myHolder.iv2 = (ImageView) myView.findViewById(R.id.imageView3);


        int hourFinal, minuteFinal;

        hourFinal = Integer.valueOf(aL3.get(position));
        minuteFinal = Integer.valueOf(aL4.get(position));


        String timeSet = "";
        if (hourFinal > 12) {
            hourFinal -= 12;
            timeSet = "PM";
        } else if (hourFinal == 0) {
            hourFinal += 12;
            timeSet = "AM";
        } else if (hourFinal == 12){
            timeSet = "PM";
        }else{
            timeSet = "AM";
        }

        String min = "";
        if (minuteFinal < 10)
            min = "0" + aL4.get(position) ;
        else
            min = String.valueOf(minuteFinal);

        String hh = String.valueOf(hourFinal);
        String mm = String.valueOf(min);

        String final_time = hh + ":"+mm+" "+timeSet;


        int yearFinal = Integer.valueOf(aL5.get(position));
        int monthFinal = Integer.valueOf(aL6.get(position));
        int dayFinal = Integer.valueOf(aL7.get(position));

        String y = String.valueOf(yearFinal);
        String mo = String.valueOf(monthFinal);
        String d = String.valueOf(dayFinal);


        myHolder.tv1.setText("Subject: "+aL1.get(position));
        myHolder.tv2.setText("Details: "+aL2.get(position));
        myHolder.tv3.setText("Time: "+final_time);
        myHolder.tv4.setText("Date: "+d+"-"+mo+"-"+y);
        myHolder.iv.setImageResource(R.drawable.delete);
       // myHolder.iv2.setImageResource(R.drawable.alerm2);


        myHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = aL1.get(position);
                Cursor data = myDb.getId(String.valueOf(s));

                while(data.moveToNext()) {
                    String d = data.getString(0);
                    Integer deletedRow = myDb.deleteData(String.valueOf(d));


                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent myIntent = new Intent(context, NotificationReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                           context, Integer.parseInt(d), myIntent,     PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.cancel(pendingIntent);





                    aL1.remove(position);
                    notifyDataSetChanged();

                    if (deletedRow > 0) {
                        Toast.makeText(context, "Data Deleted !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Data not Deleted !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        return myView;
    }
}
