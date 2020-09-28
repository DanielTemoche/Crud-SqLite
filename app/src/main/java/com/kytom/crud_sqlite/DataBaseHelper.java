package com.kytom.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Alumno.db";
    public static final String TABLE_NAME = "Alumno";

    public static final String COL_1 = "ID_ALUMNO";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "CORREO";
    public static final String COL_4 = "FECHA";

    public DataBaseHelper (Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID_ALUMNO INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CORREO TEXT, FECHA DATE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
    }
    public boolean insertData(String nombre,String correo,String fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,correo);
        contentValues.put(COL_4,fecha);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        //To Check Whether Data is Inserted in DataBase
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
}

