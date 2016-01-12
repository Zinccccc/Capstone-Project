package com.example.zinc.capstone;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by zinc on 16. 1. 13..
 */
public class DatabaseContract
{
    public static final String MENUS_TABLE = "menus_table";
    public static final class menus_table implements BaseColumns
    {
        //Table data
        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String TYPE = "type";
        public static final String MENU = "menu";
        public static final String PRICE = "price";

        //Types
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static Uri buildMenuWithDate()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("date").build();
        }
    }
    //URI data
    public static final String CONTENT_AUTHORITY = "zinc.capstone";
    public static final String PATH = "menus";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
}
