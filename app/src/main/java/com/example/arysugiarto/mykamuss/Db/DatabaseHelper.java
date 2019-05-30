package com.example.arysugiarto.mykamuss.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.ENG_TO_IND;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.IND_TO_ENG;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.KamusColumns.F_ARTI;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.KamusColumns.F_KATA;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME         = "dbkamus";
    private static final int DATABASE_VERSION   = 1;

    public static String CREATE_TABLE_IND_ENG = "create table "+IND_TO_ENG+
            " ("+_ID+" integer primary key autoincrement, " +
            F_KATA+" text not null, " +
            F_ARTI+" text not null);";

    public static String CREATE_TABLE_ENG_IND = "create table "+ENG_TO_IND+
            " ("+_ID+" integer primary key autoincrement, " +
            F_KATA+" text not null, " +
            F_ARTI+" text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IND_ENG);
        db.execSQL(CREATE_TABLE_ENG_IND);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+IND_TO_ENG);
        db.execSQL("DROP TABLE IF EXISTS "+ENG_TO_IND);
        onCreate(db);
    }
}

