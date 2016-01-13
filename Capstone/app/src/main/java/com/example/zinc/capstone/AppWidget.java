package com.example.zinc.capstone;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds){
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context){
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context){
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //show a today's menu
        String res = "";
        String mDate = null;
        MenusDBHelper helper = new MenusDBHelper(context);
        SQLiteDatabase db;
        int count = 0;

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        mDate=mformat.format(today);
        String where = DatabaseContract.menus_table.DATE + " = ?";
        String[] where_args = {mDate};
        db = helper.getReadableDatabase();
        Cursor c = db.query(DatabaseContract.MENUS_TABLE,
                null, where, where_args, null, null,
                null);
        Log.d("WidgetProvider", "count=" + String.valueOf(c.getCount()));
        c.moveToNext();
        while(c.moveToNext() && count++ < 1){
            res += (c.getString(c.getColumnIndex(DatabaseContract.menus_table.TIME))
                    + " / "
                    + c.getString(c.getColumnIndex(DatabaseContract.menus_table.TYPE))
                    + "\n"
                    + c.getString(c.getColumnIndex(DatabaseContract.menus_table.MENU))
            );
        }
        c.close();
        helper.close();
        db.close();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, res);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

