/*
 *TITLE: MOBILE COURSE
 *
 *Author: James Wilson DT211/3 Kevin Street
 *
 *All work is done by James Wilson, and owned by James Wilson
 *
 *Designed, programmed, and tested by James Wilson
 *
 *CopyRight of James Wilson
 *
 *27/11/14
 *
 */

package com.assignment.mobilecourse;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ModuleDetails extends Activity {

	String passedVar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);	
		
		//open the db
		MyDbManager info1 = new MyDbManager(this);
		info1.open();
		populateListView();
		info1.close();

	}
	
	
	//populating the listview
	private void populateListView() {
		MyDbManager info = new MyDbManager(this);
		info.open();
		
		//i.getBundleExtra("_id");
		
		//passes the id via intent on what row was clicked on the db
		passedVar=getIntent().getStringExtra(ModuleList.ID_EXTRA);
		
		long l = Long.parseLong(passedVar);
		Cursor cursor = info.getModule(l);
		
		
		//the columns to be bound
		String[] from = new String[] { MyDbManager.KEY_ROWID,
				MyDbManager.KEY_NAME, MyDbManager.KEY_CODE,
				MyDbManager.KEY_CREDITS };
		//the textviews in the R.layout.row
		int[] to = new int[] { R.id.rowid, R.id.module_name, R.id.module_code,
				R.id.module_credits };
		SimpleCursorAdapter mAdapter;
		mAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.row,
				cursor, from, to, 0);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);

		
		info.close();
	}
	
}
