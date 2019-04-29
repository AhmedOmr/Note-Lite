package com.mecodroid.notelite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "notesdb";
    public static final String TABLE_NOTES = "notesdetails";
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_DATEEN = "dateen";
    public static final String KEY_ShOWDENGLISH = "dateenglish";
    public static final String KEY_ShOWDARABIC = "dateArabic";
    public static final String KEY_COLOR = "color";





    public DbHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_SUBJECT + " TEXT, " + KEY_DATEEN + " TEXT," + KEY_ShOWDENGLISH+ " TEXT,"
                + KEY_ShOWDARABIC + " TEXT," + KEY_COLOR + " INTEGER  )";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        // Create tables again
        onCreate(db);
    }

}