package com.example.zinc.capstone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by zinc on 16. 1. 13..
 */
public class MenusProvider extends ContentProvider
{
    private static MenusDBHelper mOpenHelper;
    private UriMatcher muriMatcher = buildUriMatcher();
    private static final int MENUS = 100;


    private static final SQLiteQueryBuilder MenuQuery =
            new SQLiteQueryBuilder();
    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.BASE_CONTENT_URI.toString();
        return matcher;
    }
    @Override
    public boolean onCreate()
    {
        mOpenHelper = new MenusDBHelper(getContext());
        return false;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public String getType(Uri uri)
    {
        final int match = muriMatcher.match(uri);
        if (match == MENUS) {
            return DatabaseContract.menus_table.CONTENT_TYPE;
        }else
            throw new UnsupportedOperationException("Unknown uri :" + uri );
    }
    private int match_uri(Uri uri)
    {
        String link = uri.toString();
        if(link.contentEquals(DatabaseContract.menus_table.buildMenu().toString()))
            return MENUS;
        return -1;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor retCursor;
        int match = match_uri(uri);
        retCursor = mOpenHelper.getReadableDatabase().query(DatabaseContract.MENUS_TABLE,
                projection, null, null, null, null, sortOrder);
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values)
    {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.delete(DatabaseContract.MENUS_TABLE,null,null);
//        Log.v(FetchScoreTask.LOG_TAG,String.valueOf(muriMatcher.match(uri)));
        switch (match_uri(uri))
        {
            case MENUS:
                db.beginTransaction();
                int returncount = 0;
                try
                {
                    for(ContentValues value : values)
                    {
                        long _id = db.insertWithOnConflict(DatabaseContract.MENUS_TABLE, null, value,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (_id != -1)
                        {
                            returncount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return returncount;
            default:
                return super.bulkInsert(uri,values);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
}
