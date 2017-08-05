package com.nitol.aust.cse.austclassmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEPARTMENT";
    public static final String COL_4 = "YEAR";
    public static final String COL_5 = "SEMESTER";
    public static final String COL_6 = "SECTION";

    public ProfileDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, DEPARTMENT TEXT, YEAR TEXT, SEMESTER TEXT, SECTION TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String department, String year, String semester, String section){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,name);
        contentValues.put(COL_3,department);
        contentValues.put(COL_4,year);
        contentValues.put(COL_5,semester);
        contentValues.put(COL_6,section);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return  false;
        }
        else{
            return true;
        }
    }


    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " +TABLE_NAME, null);

        return result;
    }


    public boolean updateData(String id,  String name, String department, String year, String semester, String section){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, department);
        contentValues.put(COL_4, year);
        contentValues.put(COL_5, semester);
        contentValues.put(COL_6, section);


        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { id });

        return true;

    }



}
