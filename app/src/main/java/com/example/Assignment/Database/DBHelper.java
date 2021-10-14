package com.example.Assignment.Database;

import static com.example.Assignment.SupplierDetail.onlyProductDataList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.Assignment.ModelData.OnlyProductData;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ProductRecords.db", null, 4);
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

            String query = "select DISTINCT providername,provideremail,providerphone FROM ProductList orders GROUP BY providername,provideremail,providerphone,providerlocation";
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

            while (cursor.moveToNext()) {
                OnlyProductData data = new OnlyProductData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                onlyProductDataList.add(data);
            }
db.close();
cursor.close();
        return cursor;
    }
public Cursor tablecheck(){

    String query = "SELECT COUNT(*) FROM ProductList";
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = null;
    if (db != null) {
        cursor = db.rawQuery(query, null);
    }

    return cursor;



}
    public void deleteRowFromProducts(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "ProductList" + " WHERE " + "productname" + "='" + value + "'");
        db.close();
    }
    public void UpdateRowFromProductswithoutimage(String newname,String newdesc,String newprice,String newpname,String newpemail,String newpphone,String wherecond) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE  " + "ProductList SET productname="+"'"+newname+"',productdescription='"+newdesc+"',productprice='"+newprice+"',providername='"+newpname+"',provideremail='"+newpemail+"',providerphone='"+newpphone+"'" + " WHERE " + "productname" + "='" + wherecond + "'");
        db.close();
    }
    public void UpdateRowFromProducts(String newname,String newdesc,String newprice,String newimg,String newpname,String newpemail,String newpphone,String wherecond) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + "ProductList SET productname="+"'"+newname+"',productdescription='"+newdesc+"',productprice='"+newprice+"',productphoto='"+newimg+"',providername='"+newpname+"',provideremail='"+newpemail+"',providerphone='"+newpphone+"'" + " WHERE " + "productname" + "='" + wherecond + "'");
        db.close();
    }
    public void deleteRowFromProviders(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "ProductList" + " WHERE " + "providername" + "='" + value + "'");
        db.close();
    }
    public void UpdateRowFromProviderswithoutimage(String newname,String newdesc,String newprice,String wherecond) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE  " + "ProductList SET providername="+"'"+newname+"',provideremail='"+newdesc+"',providerphone='"+newprice+"'" + " WHERE " + "providername" + "='" + wherecond + "'");
        db.close();
    }
    public boolean checkfromProvider(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + "ProductList" + " where " + "providername" + "='" + value + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean checkfromProduct(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + "ProductList" + " where " + "productname" + "='" + value + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public Cursor getEmailandPhoneProvider(String wherecond){

        String query = "Select  DISTINCT provideremail,providerphone FROM ProductList  Where providername="+"'"+wherecond+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;



    }
    public int getProviderssize(String provider){
int send=0;
        String query = "SELECT COUNT(productname) FROM ProductList WHERE providername="+"'"+provider+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {

            cursor = db.rawQuery(query, null);
        }

        while (cursor.moveToNext()){

            send=Integer.parseInt(cursor.getString(0));
        }

        return send;

    }
}


