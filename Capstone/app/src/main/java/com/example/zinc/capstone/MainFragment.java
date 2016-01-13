package com.example.zinc.capstone;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by zinc on 16. 1. 13..
 */
public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    public MenusAdapter mAdapter;
    public static final int MENUS_LOADER = 0;
    private String[] fragmentdate = new String[1];
    private int last_selected_item = -1;

//    private void update_scores()
//    {
//        Intent service_start = new Intent(getActivity(), myFetchService.class);
//        getActivity().startService(service_start);
//    }
    public void setFragmentDate(String date)
    {
        fragmentdate[0] = date;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ListView menu_list = (ListView) rootView.findViewById(R.id.listView);
        mAdapter = new MenusAdapter(getActivity(),null,0);
        menu_list.setAdapter(mAdapter);
        getLoaderManager().initLoader(MENUS_LOADER,null,this);
//        mAdapter.detail_match_id = MainActivity.selected_match_id;
//        score_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                ViewHolder selected = (ViewHolder) view.getTag();
//                mAdapter.detail_match_id = selected.match_id;
//                MainActivity.selected_match_id = (int) selected.match_id;
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle)
    {
        return new CursorLoader(getActivity(),DatabaseContract.menus_table.buildMenu(),
                null,null,fragmentdate,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
    {
        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            i++;
            cursor.moveToNext();
        }
        //Log.v("TEST","Loader query: " + String.valueOf(i));
        mAdapter.swapCursor(cursor);
        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader)
    {
        mAdapter.swapCursor(null);
    }


}
