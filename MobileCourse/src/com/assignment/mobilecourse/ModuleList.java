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
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ModuleList extends Activity implements OnClickListener {

	public final static String ID_EXTRA = "com.assignment.mobilecourse._ID";

	SimpleCursorAdapter dataAdapter;
	Button toModules;
	MyDbManager info = new MyDbManager(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);

		toModules=(Button)findViewById(R.id.bModules);     
		toModules.setOnClickListener(this);
        		
		info.open();
		populateListView();
		info.close();

	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		info.close();
		
	}
	
	//Use onrestart so that data can be updated e.g if add it should refresh the activity
	//so that you can see the added module 
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Intent i = new Intent(ModuleList.this, ModuleList.class);
	    startActivity(i);
	    finish();
	}
	
	//open the db when app is resumed
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			info.open();
		}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent open = new Intent(ModuleList.this, ModulesAdd.class);
        startActivity(open);
		
	}

	private void populateListView() {
		//MyDbManager info = new MyDbManager(this);
		info.open();
		Cursor cursor = info.getAllModules();
		String[] from = new String[] { MyDbManager.KEY_ROWID,
				MyDbManager.KEY_NAME, MyDbManager.KEY_CODE,
				MyDbManager.KEY_CREDITS };
		int[] to = new int[] { R.id.rowid, R.id.module_name, R.id.module_code,
				R.id.module_credits };
		SimpleCursorAdapter mAdapter;
		mAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.row,
				cursor, from, to, 0);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);
		////loads up deleted or added, edited module
		mAdapter.notifyDataSetChanged();
		//setListAdapter(mAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor Mycursor = (Cursor) listView.getItemAtPosition(position);
				// get the row id for one output

				//change assignment name
				String Modulename = Mycursor.getString(Mycursor
						.getColumnIndexOrThrow("module_name"));
				Toast.makeText(getApplicationContext(), Modulename,
						Toast.LENGTH_SHORT).show();

				Intent theIndent = new Intent(getApplication(),
						Modules.class);
				theIndent.putExtra(ID_EXTRA, String.valueOf(id));
				startActivity(theIndent);

			}
		});
		info.close();
		

	}

}