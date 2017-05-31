package com.example.shara.bbcnews;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shara on 3/19/2017.
 */

public class NewsDAO {
    private SQLiteDatabase db;

    public NewsDAO(SQLiteDatabase db) {
        this.db = db;

    }

    public long save(News note) {
        ContentValues values = new ContentValues();
        values.put(NewsTable.COLUMN_NEWS_TITLE, note.getTitle());
        values.put(NewsTable.COLUMN_LINK, note.getNewsLink());
        values.put(NewsTable.COLUMN_IMAGE, note.getThumbNailImage());
        values.put(NewsTable.COLUMN_PUBDATE, getDateTime(note.getPublishedDate()));
        return db.insert(NewsTable.TABLENAME, null, values);
    }

    public boolean update(News note) {
        ContentValues values = new ContentValues();
        values.put(NewsTable.COLUMN_NEWS_TITLE, note.getTitle());
        values.put(NewsTable.COLUMN_LINK, note.getNewsLink());
        values.put(NewsTable.COLUMN_IMAGE, note.getThumbNailImage());
        values.put(NewsTable.COLUMN_PUBDATE, getDateTime(note.getPublishedDate()));
        return db.update(NewsTable.TABLENAME, values, NewsTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    public boolean delete(News note) {
        return db.delete(NewsTable.TABLENAME, NewsTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public News getNote(long id) {
        News note = null;

        Cursor cursor = db.query(true, NewsTable.TABLENAME, new String[]{NewsTable.COLUMN_ID, NewsTable.COLUMN_NEWS_TITLE, NewsTable.COLUMN_LINK, NewsTable.COLUMN_IMAGE, NewsTable.COLUMN_PUBDATE}, NewsTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            note = buildNoteFromCursor(cursor);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return note;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<News> getAll() {
        List<News> notes = new ArrayList<>();

        Cursor cursor = db.query(NewsTable.TABLENAME, new String[]{NewsTable.COLUMN_ID, NewsTable.COLUMN_NEWS_TITLE, NewsTable.COLUMN_IMAGE, NewsTable.COLUMN_LINK, NewsTable.COLUMN_PUBDATE}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                News news = buildNoteFromCursor(cursor);
                if (news != null) {
                    notes.add(news);
                }
            } while (cursor.moveToNext());
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return notes;
    }

    private News buildNoteFromCursor(Cursor cursor) {
        News note = null;
        if (cursor != null) {
            note = new News();
            note.set_id(cursor.getLong(0));
            note.setTitle(cursor.getString(1));
            note.setNewsLink(cursor.getString(2));
            note.setThumbNailImage(cursor.getString(3));
            note.setPublishedDate(getDateTime(cursor.getString(4)));
        }
        return note;
    }

    public static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static Date getDateTime(String date) {
        try {
            return (Date) (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}