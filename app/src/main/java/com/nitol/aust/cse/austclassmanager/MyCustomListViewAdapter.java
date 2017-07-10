package com.nitol.aust.cse.austclassmanager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomListViewAdapter extends BaseAdapter{

    CgpaCalculator cg = new CgpaCalculator();

    ArrayList<String> aL1 = new ArrayList<>();
    ArrayList<String> aL2 = new ArrayList<>();
    ArrayList<String> aL3 = new ArrayList<>();
    ArrayList<String> aL4 = new ArrayList<>();

    Context context;
    private static LayoutInflater  inflater = null;

    public MyCustomListViewAdapter(CgpaCalculator mainAct, ArrayList<String> a1, ArrayList<String>
            a2, ArrayList<String> a3, ArrayList<String> a4){
        aL1 = a1;
        aL2 = a2;
        aL3 = a3;
        aL4 = a4;

        context = mainAct;
        inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        MyHolder myHolder = new MyHolder();
        View myView;

        myView = inflater.inflate(R.layout.custom_cgpa, null);

        myHolder.tv1 = (TextView) myView.findViewById(R.id.textView_1);
        myHolder.tv2 = (TextView) myView.findViewById(R.id.textView_2);
        myHolder.tv3 = (TextView) myView.findViewById(R.id.textView_3);
        myHolder.tv4 = (TextView) myView.findViewById(R.id.textView_4);

        myHolder.tv1.setText(aL1.get(position));
        myHolder.tv2.setText(aL2.get(position));
        myHolder.tv3.setText(aL3.get(position));
        myHolder.tv4.setText(aL4.get(position));


        /*myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return myView;
    }


}
