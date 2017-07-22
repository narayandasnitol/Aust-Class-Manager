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
    public static final String COL_4 = "HOUR";
    public static final String COL_5 = "MINUTE";
    public static final String COL_6 = "YEAR";
    public static final String COL_7 = "MONTH";
    public static final String COL_8 = "DAY";

    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, DETAILS TEXT, HOUR TEXT, MINUTE TEXT, YEAR TEXT, MONTH TEXT, DAY TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String details, String hour, String minute,
                              String year, String month, String day){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, title);
        contentValues.put(COL_3, details);
        contentValues.put(COL_4, hour);
        contentValues.put(COL_5, minute);
        contentValues.put(COL_6, year);
        contentValues.put(COL_7, month);
        contentValues.put(COL_8, day);


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
        Cursor result = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);

        return result;
    }

    public Cursor getId(String where){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_2+" = '"+where+"'", null);

        return c;
    }


    public boolean updateData(String id,  String title, String details, String hour,
                              String minute, String year, String month, String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, details);
        contentValues.put(COL_4, hour);
        contentValues.put(COL_5, minute);
        contentValues.put(COL_6, year);
        contentValues.put(COL_7, month);
        contentValues.put(COL_8, day);

        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { id });

        return true;

    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});

    }

}
