package com.nitol.aust.cse.austclassmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomProfileAdapter extends BaseAdapter{

    String[] text, text2;
    Context context;
    private static LayoutInflater  inflater = null;

    public CustomProfileAdapter(Profile_Activity mainAct, String[] name, String[] name2){
        text = name;
        text2 = name2;

        context = mainAct;

        inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return text.length;
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
        TextView tv, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        MyHolder myHolder = new MyHolder();
        View myView;

        myView = inflater.inflate(R.layout.custom_profile, null);

        myHolder.tv = (TextView) myView.findViewById(R.id.textView);
        myHolder.tv2 = (TextView) myView.findViewById(R.id.textView2);


        myHolder.tv.setText(text[position]);
        myHolder.tv2.setText(text2[position]);


        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return myView;
    }
}
