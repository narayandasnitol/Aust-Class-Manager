package com.nitol.aust.cse.austclassmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "quiz.db";
    public static final String TABLE_NAME = "quiz_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "DETAILS";
    public static final String COL_4 = "TIME";
    public static final String COL_5 = "DATE";

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, DETAILS TEXT, TIME TEXT, DATE TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String details, String time, String date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,title);
        contentValues.put(COL_3,details);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,date);


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


    public boolean updateData(String id,  String title, String details, String time, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, details);
        contentValues.put(COL_4, time);
        contentValues.put(COL_5, date);

        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { id });

        return true;

    }

}
