package com.example.zinc.capstone;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.example.zinc.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by zinc on 16. 1. 12..
 */
//reference : https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
class EndPointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;

    Context mContext = null;
    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        mContext = params[0];

        try {
            return myApiService.getMenuJSON().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Menu menu;
        Gson gson = new Gson();

        System.out.println("MainActivity " + result);

        //reference : http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
        ArrayList<Menu> menuList= (ArrayList<Menu>) fromJson(result,
                new TypeToken<ArrayList<Menu>>() {
                }.getType());

        Vector<ContentValues> values = new Vector <ContentValues> (menuList.size());
        for(int i = 0; i < menuList.size(); i++){
            menu = menuList.get(i);

            ContentValues menu_values = new ContentValues();
            menu_values.put(DatabaseContract.menus_table.DATE, menu.getDate());
            menu_values.put(DatabaseContract.menus_table.TIME, menu.getTime());
            menu_values.put(DatabaseContract.menus_table.TYPE, menu.getCafeteria_type());
            menu_values.put(DatabaseContract.menus_table.MENU, menu.getMenu());
            menu_values.put(DatabaseContract.menus_table.PRICE, menu.getPrice());
            values.add(menu_values);
        }
        int inserted_data = 0;
        ContentValues[] insert_data = new ContentValues[values.size()];
        values.toArray(insert_data);
        inserted_data = mContext.getContentResolver().bulkInsert(
                DatabaseContract.BASE_CONTENT_URI,insert_data);
    }
    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

}
