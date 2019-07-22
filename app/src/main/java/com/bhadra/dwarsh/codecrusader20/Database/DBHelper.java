package com.bhadra.dwarsh.codecrusader20.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bhadra.dwarsh.codecrusader20.Pojo.Address;
import com.bhadra.dwarsh.codecrusader20.Pojo.Geo;
import com.bhadra.dwarsh.codecrusader20.Pojo.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CodeCrusdar.db";
    public static final String TABLE_NAME = "favourites";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table favourites " +
                        "(id integer primary key, name text,phone text,email text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS favourites");
        onCreate(db);
    }

    public boolean insertContact (int id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);
        db.insert("favourites", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from favourites where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        db.update("favourites", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("favourites",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Item> getAllFav() {
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from favourites", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            //String addressarr[] = res.getString(res.getColumnIndex(COLUMN_ADDRESS)).split(",");
            //Address address = new Address(addressarr[0],addressarr[1],addressarr[2],addressarr[3],new Geo(Double.parseDouble(addressarr[4]),Double.parseDouble(addressarr[5])));
            Item item = new Item(res.getInt(res.getColumnIndex(COLUMN_ID)),res.getString(res.getColumnIndex(COLUMN_NAME)),res.getString(res.getColumnIndex(COLUMN_EMAIL)));
            array_list.add(item);
            res.moveToNext();
        }
        return array_list;
    }
}
