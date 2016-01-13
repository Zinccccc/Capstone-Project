package com.example.zinc.capstone;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zinc on 16. 1. 13..
 */
public class MenusAdapter extends CursorAdapter
{
    public static final double menu_id = 0;
    public static final int COL_DATE = 1;
    public static final int COL_TIME = 2;
    public static final int COL_TYPE = 3;
    public static final int COL_MENU = 4;
    public static final int COL_PRICE = 5;

    public MenusAdapter(Context context,Cursor cursor,int flags)
    {
        super(context,cursor,flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View mItem = LayoutInflater.from(context).inflate(R.layout.menus_list_item, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
        return mItem;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor)
    {
        final ViewHolder mHolder = (ViewHolder) view.getTag();

        mHolder.menu_date.setText(cursor.getString(COL_DATE));
        mHolder.menu_time.setText(cursor.getString(COL_TIME));
        mHolder.menu_type.setText(cursor.getString(COL_TYPE));
        mHolder.menu_menu.setText(cursor.getString(COL_MENU));
        mHolder.menu_price.setText(cursor.getString(COL_PRICE));
    }
}
