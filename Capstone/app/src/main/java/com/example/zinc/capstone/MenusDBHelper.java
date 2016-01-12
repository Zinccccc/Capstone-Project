package com.example.zinc.capstone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.zinc.capstone.DatabaseContract.menus_table;

/**
 * Created by zinc on 16. 1. 13..
 */
public class MenusDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "SSUmenus.db";
    private static final int DATABASE_VERSION = 1;
    public MenusDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CreateMenusTable = "CREATE TABLE " + DatabaseContract.MENUS_TABLE + " ("
                + menus_table._ID + " INTEGER PRIMARY KEY,"
                + menus_table.DATE + " TEXT NOT NULL,"
                + menus_table.TIME + " TEXT NOT NULL,"
                + menus_table.TYPE + " TEXT NOT NULL,"
                + menus_table.MENU + " TEXT NOT NULL,"
                + menus_table.PRICE + " TEXT"
                + " );";
        db.execSQL(CreateMenusTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Remove old values when upgrading.
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MENUS_TABLE);
    }
}
