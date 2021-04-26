package com.androidrion.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Pembuatan class helper yang berisi methode create table
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override

    //method on create dibuat saat tidak ada database
    public void onCreate(SQLiteDatabase db) {
        //membuat tabel dalam database
        String sql = "create table biodata (no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " +sql);
        db.execSQL(sql);
        //mengisi data dalam database
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Rizqa', " +
                "'23-07-2000'," +
                " 'Perempuan', 'Jember');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }


}

