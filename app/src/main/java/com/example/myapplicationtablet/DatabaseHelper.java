package com.example.myapplicationtablet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "saved_articles.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "saved_articles"; // название таблицы в бд
    // названия столбцов  itemId INTEGER, title TEXT, path TEXT
    public static final String itemId = "item_id";
    public static final String title  = "title";
    public static final String path = "path";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" + itemId +"Integer,"+title
                + " TEXT " + path + "  TEXT);");
        // добавление начальных данных
     //   db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
       //         + ", " + COLUMN_YEAR  + ") VALUES ('Том Смит', 1981);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
