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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AssignmentList extends Activity implements OnClickListener {

	// init a var to be the var for the db
	MyDbManager info1 = new MyDbManager(this);

	// this is a way i have grabbed the id from the db
	public final static String ID_EXTRA1 = "com.assignment.mobilecourse._ID";
	SimpleCursorAdapter dataAdapter;
	Button toAssignAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_assignment_main);

		toAssignAdd = (Button) findViewById(R.id.bAssignments);
		toAssignAdd.setOnClickListener(this);

		info1.open();
		populateListView();
		info1.close();

	}

	// when the button is clicked
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent open = new Intent(AssignmentList.this, AssignmentAdd.class);
		startActivity(open);
	}

	// refreshes the page so that it updates the data on the listview
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Intent i = new Intent(AssignmentList.this, AssignmentList.class);
		startActivity(i);
		finish();
	}

	// closes the app when it is paused e.g. phone rings
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		info1.close();

	}

	// open the db when app is resumed
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		info1.open();
	}

	// now we populate the listview:
	// we use a cursor to go through all of the columns in the table
	// it now is stored in the cursor
	// make a string called from and = that to what cols we want
	private void populateListView() {

		info1.open();
		Cursor cursor = info1.getAllNewAssign();
		String[] from = new String[] { MyDbManager.COULMN_NEWNAME,
				MyDbManager.COULMN_NEWDUE };
		int[] to = new int[] { R.id.assign_name, R.id.assign_due };
		SimpleCursorAdapter mAdapter;
		mAdapter = new SimpleCursorAdapter(getBaseContext(),
				R.layout.row_assignment, cursor, from, to, 0);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);

		// when an item on the listvewi is clicked
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor Mycursor = (Cursor) listView.getItemAtPosition(position);

				// just a simple toast message on the item that was clicked
				String Assignmentname = Mycursor.getString(Mycursor
						.getColumnIndexOrThrow("newname"));
				Toast.makeText(getApplicationContext(), Assignmentname,
						Toast.LENGTH_SHORT).show();

				// onclick to another activity, carrying the id
				Intent theIndent = new Intent(getApplication(),
						Assignments.class);
				theIndent.putExtra(ID_EXTRA1, String.valueOf(id));
				startActivity(theIndent);

			}
		});
		info1.close();
	}

}
