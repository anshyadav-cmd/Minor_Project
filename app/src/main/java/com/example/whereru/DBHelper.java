package com.example.whereru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";

    public DBHelper( Context context) {
        super(context, "login.db",  null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
     MyDB.execSQL("create table login(username TEXT primary key,  Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
       MyDB.execSQL("drop table if exists login");
    }
    public Boolean insertData(String username, String Password ){
    SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", Password);
         long result= MyDB.insert("login", null, contentValues);
          if(result==1)
              return false;
          else
              return true;
     }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("select * from login where username =?", new String[] { username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkpassword (String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from login where username=? and password =?", new String[]{ username, password});
        if (cursor.getCount() >0)
            return true;
        else
            return false;
    }
}
