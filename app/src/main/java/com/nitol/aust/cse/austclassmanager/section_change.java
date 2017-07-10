package com.nitol.aust.cse.austclassmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class section_change extends DialogFragment {

    final CharSequence[] items = {"Section A", "Section B", "Section C", "Section D"};
    String selection="";
    DatabaseHelper myDb;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    int choice;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        myDb = new DatabaseHelper(getContext());
        getAllData();

        if(mySection.trim().equals("Section A")){
            choice= 0;
        }
        else if(mySection.trim().equals("Section B")){
            choice = 1;
        }
        else if(mySection.trim().equals("Section C")){
            choice = 2;
        }
        else if(mySection.trim().equals("Section D")){
            choice = 3;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        builder.setTitle("Select your Section").setSingleChoiceItems(items, choice,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                selection = (String) items[which];

                                break;

                            case 1:
                                selection = (String) items[which];

                                break;

                            case 2:
                                selection = (String) items[which];

                                break;

                            case 3:
                                selection = (String) items[which];

                                break;
                        }
                    }
                });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(selection.isEmpty()){
                    if(choice == 0){
                        mySection = (String) items[choice];
                    }
                    else if(choice == 1){
                        mySection = (String) items[choice];
                    }
                    else if(choice == 2){
                        mySection = (String) items[choice];
                    }
                    else if(choice == 3){
                        mySection = (String) items[choice];
                    }

                    Toast.makeText(getContext(),"Data not updated!",Toast.LENGTH_SHORT).show();

                }

                else{
                    mySection = selection;
                    Toast.makeText(getContext(),"Data updated!",Toast.LENGTH_SHORT).show();
                }


                updateAll();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });



        return builder.create();
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

        myDb.updateData(id, name, dept, yr, sem, sec);
    }

}
