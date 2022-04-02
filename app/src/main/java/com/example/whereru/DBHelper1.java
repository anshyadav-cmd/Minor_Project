package com.example.whereru;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DBHelper1 extends SQLiteOpenHelper{
    private static final String DB_NAME = "registration";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "registration";
    private static final String rfullNameText_id = "Name";
    private static final String remailText_id = "Email";
    private static final String rpasswordText_id = "Password";
    private static final String rphoneText_id = "Phone";
    public DBHelper1(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public DBHelper1(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + rfullNameText_id + " String PRIMARY KEY AUTOINCREMENT, "
                + remailText_id + " TEXT,"
                + rphoneText_id + " INT,"
                + rpasswordText_id + " TEXT)";
        db.execSQL(query);
    }
    public void addNewregistration(String Name, String Email, String Phone, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(rfullNameText_id, Name);
        values.put(remailText_id, Email);
        values.put(rphoneText_id, Phone);
        values.put(rpasswordText_id, Password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
