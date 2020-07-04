package com.idn99.project.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data";
    public static final String TB_NAME = "tb_buku";
    public static final String KODE_BUKU = "kode_buku";
    public static final String JUDUL_BUKU = "judul_buku";
    public static final String PENGARANG = "pengarang";
    public static final String CREATE_TB = "CREATE TABLE tb_buku(" +
            "kode_buku INTEGER PRIMARY KEY AUTOINCREMENT,judul_buku TEXT NOT NULL, pengarang TEXT NOT NULL);";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_TB);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(db);
    }

    public void addRecord(Buku buku){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KODE_BUKU, buku.getKode_buku());
        values.put(JUDUL_BUKU, buku.getNama_buku());
        values.put(PENGARANG, buku.getPengarang());

        db.insert(TB_NAME, null, values);
        db.close();
    }

    // get All Record
    public ArrayList<Buku> getAllRecord() {
        ArrayList<Buku> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TB_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Buku userModels = new Buku(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                contactList.add(userModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public void deleteModel(Buku buku) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_NAME, KODE_BUKU + " = ?",
                new String[] { String.valueOf(buku.getKode_buku()) });
        db.close();
    }

}
