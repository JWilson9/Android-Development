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

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	String classNames[] = { "ModuleList", "AssignmentList" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.row_main,
				R.id.classname, classNames));

	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		super.onListItemClick(lv, v, position, id);
		String openClass = classNames[position];
		try {
			// james.assignment
			// Tutorial.this
			Class selected = Class.forName("com.assignment.mobilecourse."
					+ openClass);
			Intent selectedIntent = new Intent(MainActivity.this, selected);
			startActivity(selectedIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// promts if the user wants to leave the app if they hit the back
	// button on the mainActivity (starting page)
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("quit")
					.setMessage("You want to leave already")
					.setPositiveButton("yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									MainActivity.this.finish();
								}

							}).setNegativeButton("no", null).show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

}
