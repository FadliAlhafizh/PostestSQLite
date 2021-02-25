package com.example.postestsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION= 1;
    private static final String DB_NAME="Notes";
    private static final String TABLE_NAME="tbl_notes";
    private static final String KEY_TANGGAL="tanggal";
    private static final String KEY_JUDUL="judul";
    private static final String KEY_DESKRIPSI="deskripsi";

    public DatabaseHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTable="Create Table " + TABLE_NAME +"("+ KEY_TANGGAL+ " TEXT ,"+ KEY_JUDUL +" TEXT PRIMARY KEY," + KEY_DESKRIPSI+" TEXT"+")";
        db.execSQL(createNoteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String tgl = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        values.put(KEY_TANGGAL,tgl);
        values.put(KEY_JUDUL,note.getJudul());
        values.put(KEY_DESKRIPSI,note.getDeskripsi());

        db.insert(TABLE_NAME,null,values);
    }

    public List<Note> selectNoteData() {
        ArrayList<Note> notes =new ArrayList<Note>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_TANGGAL,KEY_JUDUL,KEY_DESKRIPSI};

        Cursor c = db.query(TABLE_NAME,columns,null,null,null, null, null);

        while (c.moveToNext()){
            String tgl = c.getString(0);
            String judul = c.getString(1);
            String deskripsi = c.getString(2);

            Note note = new Note();
            note.setTanggal(tgl);
            note.setJudul(judul);
            note.setDeskripsi(deskripsi);

            notes.add(note);
        }

        return notes;
    }

    public void delete(String judul){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_JUDUL+"='"+judul+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }

    public void update(Note note){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        String tgl = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        values.put(KEY_TANGGAL,tgl);
        values.put(KEY_JUDUL,note.getJudul());
        values.put(KEY_DESKRIPSI,note.getDeskripsi());

        String whereClause = KEY_JUDUL +" = '" + note.getJudul() +"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }
}
