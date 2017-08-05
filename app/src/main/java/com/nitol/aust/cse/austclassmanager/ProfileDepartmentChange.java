package com.nitol.aust.cse.austclassmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class ProfileDepartmentChange extends DialogFragment {

    final CharSequence[] items = {"Architecture", "EEE", "CSE", "ME", "TE", "CE", "IPE", "BBA"};
    String selection = "";
    ProfileDatabaseHelper myDb;

    String myDept;
    String myYear;
    String mySemester;
    String mySection;
    String myName;

    int choice;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        myDb = new ProfileDatabaseHelper(getContext());
        getAllData();

        if(myDept.trim().equals("Architecture")){
            choice= 0;
        }
        else if(myDept.trim().equals("EEE")){
            choice = 1;
        }
        else if(myDept.trim().equals("CSE")){
            choice = 2;
        }
        else if(myDept.trim().equals("ME")){
            choice = 3;
        }
        else if(myDept.trim().equals("TE")){
            choice = 4;
        }
        else if(myDept.trim().equals("CE")){
            choice = 5;
        }
        else if(myDept.trim().equals("IPE")){
            choice = 6;
        }
        else if(myDept.trim().equals("BBA")){
            choice = 7;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        builder.setTitle("Select your department").setSingleChoiceItems(items, choice,
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

                            case 4:
                                selection = (String) items[which];

                                break;

                            case 5:
                                selection = (String) items[which];

                                break;

                            case 6:
                                selection = (String) items[which];

                                break;

                            case 7:
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
                        myDept = (String) items[choice];
                    }
                    else if(choice == 1){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 2){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 3){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 4){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 5){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 6){
                        myDept = (String) items[choice];
                    }
                    else if(choice == 7){
                        myDept = (String) items[choice];
                    }

                    Toast.makeText(getContext(),"Data not updated!",Toast.LENGTH_SHORT).show();
                }

                else{
                    myDept = selection;
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
