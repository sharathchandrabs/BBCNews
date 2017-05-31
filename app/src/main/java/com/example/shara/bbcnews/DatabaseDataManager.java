package com.example.shara.bbcnews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

/**
 * Created by shara on 3/19/2017.
 */

public class DatabaseDataManager {
    private Context context;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NewsDAO noteDAO;

    public DatabaseDataManager(Context context) {
        this.context = context;
        dbOpenHelper = new DatabaseOpenHelper(this.context);
        db = dbOpenHelper.getWritableDatabase();
//        dbOpenHelper.onCreate(db);
        noteDAO = new NewsDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public NewsDAO getNoteDAO() {
        return noteDAO;
    }

    public long saveNote(News note) {
        return this.noteDAO.save(note);
    }

    public boolean updateNote(News note) {
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(News note) {
        return this.noteDAO.delete(note);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public News getNote(long id) {
        return this.noteDAO.getNote(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<News> getAllNotes() {
        return this.noteDAO.getAll();
    }
}
