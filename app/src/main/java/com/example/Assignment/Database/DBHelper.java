package com.example.Assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ProductRecords.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ProductList(UID INTEGER PRIMARY KEY AUTOINCREMENT,productphoto TEXT, productname TEXT, productdescription TEXT,productprice TEXT,providername TEXT,provideremail TEXT,providerphone TEXT,providerlocation TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists ProductList");

    }

    public Boolean insertProductData(String prodphoto, String prodname, String proddesc, String prodprice,String providername,String provideremail,String providerphone,String providerlocation) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put("productphoto",prodphoto);
        contentValues.put("productname",prodname);
        contentValues.put("productdescription",proddesc);
        contentValues.put("productprice",prodprice);
        contentValues.put("providername",providername);
        contentValues.put("provideremail",provideremail);
        contentValues.put("providerphone",providerphone);
        contentValues.put("productphoto",prodphoto);
        contentValues.put("providerlocation",providerlocation);
        long result = DB.insert("ProductList", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getProductData() {
        String query = "SELECT* FROM ProductList";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }
    public void clearProductList(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM ProductList"); //delete all rows in a table
        db.close();
    }
    public Cursor getProvidersData(){

            String query = "select providername,provideremail,providerphone,providerlocation, count(*) as total FROM ProductList orders GROUP BY providername,provideremail,providerphone,providerlocation";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query, null);
            }
            return cursor;

        }
    public Cursor getProductsfromProvider(String provider){

        String query = "SELECT productphoto,productname,productdescription,productprice FROM ProductList where providername="+"'"+provider+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
    }


