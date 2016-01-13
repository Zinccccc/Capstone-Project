package com.example.zinc.capstone;

import android.view.View;
import android.widget.TextView;

/**
 * Created by zinc on 16. 1. 13..
 */
public class ViewHolder
{
    public TextView menu_date;
    public TextView menu_time;
    public TextView menu_type;
    public TextView menu_menu;
    public TextView menu_price;
    public double menu_id;
    public ViewHolder(View view)
    {
        menu_date = (TextView) view.findViewById(R.id.menu_date);
        menu_time = (TextView) view.findViewById(R.id.menu_time);
        menu_type = (TextView) view.findViewById(R.id.menu_type);
        menu_menu = (TextView) view.findViewById(R.id.menu_menu);
        menu_price = (TextView) view.findViewById(R.id.menu_price);
    }
}
