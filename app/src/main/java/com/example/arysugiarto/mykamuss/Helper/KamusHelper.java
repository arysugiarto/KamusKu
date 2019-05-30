package com.example.arysugiarto.mykamuss.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.arysugiarto.mykamuss.Db.DatabaseHelper;
import com.example.arysugiarto.mykamuss.Model.KamusModel;
import com.example.arysugiarto.mykamuss.R;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.ENG_TO_IND;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.IND_TO_ENG;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.KamusColumns.F_ARTI;
import static com.example.arysugiarto.mykamuss.Db.DatabaseContract.KamusColumns.F_KATA;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<KamusModel> getDataByName(String cari, String selection){

        String Kategory = null;
        Cursor cursor = null;
        if(selection == "Eng"){
            cursor = database.query(ENG_TO_IND,null,F_KATA+" LIKE ?",new String[]{cari.trim()+"%"},null,null,_ID + " ASC",null);
            Kategory = context.getResources().getString(R.string.ing_ind);
        }else if (selection == "Ind"){
            cursor = database.query(IND_TO_ENG,null,F_KATA+" LIKE ?",new String[]{cari.trim()+"%"},null,null,_ID + " ASC",null);
            Kategory = context.getResources().getString(R.string.ind_ing);
        }
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKoskata(cursor.getString(cursor.getColumnIndexOrThrow(F_KATA)));
                kamusModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(F_ARTI)));
                kamusModel.setKategori(Kategory);

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getAllData(String selection){
        Cursor cursor;
        String category = null;
        if(selection == "Eng"){
            cursor = database.query(ENG_TO_IND,null,null,null,null,null,_ID+ " ASC",null);
            category = context.getResources().getString(R.string.ing_ind);
        }else{
            cursor = database.query(IND_TO_ENG,null,null,null,null,null,_ID+ " ASC",null);
            category = context.getResources().getString(R.string.ind_ing);
        }
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount()>0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setKoskata(cursor.getString(cursor.getColumnIndexOrThrow(F_KATA)));
                kamusModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(F_ARTI)));
                kamusModel.setKategori(category);

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamusModel, String selection){
        String table = null;
        if(selection == "Eng"){
            table = ENG_TO_IND;
        }else{
            table = IND_TO_ENG;
        }

        ContentValues initialValues =  new ContentValues();
        initialValues.put(F_KATA, kamusModel.getKoskata());
        initialValues.put(F_ARTI, kamusModel.getDeskripsi());
        return database.insert(table, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(KamusModel kamusModel, String selection){
        String table = null;
        if(selection == "Eng"){
            table = ENG_TO_IND;
        }else{
            table = IND_TO_ENG;
        }

        String sql = "INSERT INTO "+table+" ("+F_KATA+", "+F_ARTI
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getKoskata());
        stmt.bindString(2, kamusModel.getDeskripsi());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(KamusModel kamusModel, String selection){
        String table = null;
        if(selection == "Eng"){
            table = ENG_TO_IND;
        }else{
            table = IND_TO_ENG;
        }

        ContentValues args = new ContentValues();
        args.put(F_KATA, kamusModel.getKoskata());
        args.put(F_ARTI, kamusModel.getDeskripsi());
        return database.update(table, args, _ID + "= '" + kamusModel.getId() + "'", null);
    }

    public int delete(int id, String selection){
        String table = null;
        if(selection == "Eng"){
            table =ENG_TO_IND;
        }else{
            table = IND_TO_ENG;
        }
        return database.delete(table, _ID + " = '"+id+"'", null);
    }
}
