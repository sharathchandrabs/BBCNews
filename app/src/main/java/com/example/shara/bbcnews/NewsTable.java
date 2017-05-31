package com.example.shara.bbcnews;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by shara on 3/19/2017.
 */

public class NewsTable {
    static final String TABLENAME = "notes";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_NEWS_TITLE = "note";
    static final String COLUMN_LINK = "newslink";
    static final String COLUMN_IMAGE = "image";
    static final String COLUMN_PUBDATE = "publishedDate";



    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_NEWS_TITLE + " text not null, ");
        sb.append(COLUMN_LINK + " text not null,");
        sb.append(COLUMN_IMAGE + " text not null,");
        sb.append(COLUMN_PUBDATE + " text not null");
        sb.append(")");
        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        NewsTable.onCreate(db);
    }
}
