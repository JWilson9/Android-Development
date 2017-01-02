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
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Modules extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, edit, delete;
	EditText sqlName, sqlCode, sqlCredits, sqlRow;
	String passedVar = null;
	TextView passedView;

	MyDbManager info = new MyDbManager(Modules.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_module);

		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlCode = (EditText) findViewById(R.id.etSQLCode);
		sqlCredits = (EditText) findViewById(R.id.etSQLCredits);
		
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		//sqlView = (Button) findViewById(R.id.bSQLView);

		edit = (Button) findViewById(R.id.bEdit);
		delete = (Button) findViewById(R.id.bDelete);

		edit.setOnClickListener(this);
		delete.setOnClickListener(this);

		
		info.open();

		passedVar = getIntent().getStringExtra(ModuleList.ID_EXTRA);
		long l = Long.parseLong(passedVar); //
		String returnedName = info.getName(l);
		String returnedCode = info.getCode(l);
		String returnedCredits = info.getCredits(l);
		info.close();

		sqlName.setText(returnedName);
		sqlCode.setText(returnedCode);
		sqlCredits.setText(returnedCredits);

		//info.close();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		MediaPlayer deleteSound = MediaPlayer.create(Modules.this, R.raw.moddel);
		
		switch (arg0.getId()) {

		case R.id.bEdit:
			boolean didItWork = true;
			didItWork = true;
			try {
				String mName = sqlName.getText().toString();
				String mCode = sqlCode.getText().toString();
				String mCredits = sqlCredits.getText().toString();

				passedVar = getIntent().getStringExtra(ModuleList.ID_EXTRA);
				long lRow = Long.parseLong(passedVar);

				MyDbManager edit = new MyDbManager(this);
				edit.open();
				edit.updateEntry(lRow, mName, mCode, mCredits);
				edit.close();

			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("oh dear");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.show();
			} finally {
				if (didItWork) {
					
					Dialog d = new Dialog(this);
					d.setTitle("Updated Successfully");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.show();
				}
			}
			break;

		case R.id.bDelete:
			boolean didItWork1 = true;
			try {
				passedVar = getIntent().getStringExtra(ModuleList.ID_EXTRA);
				long lRow1 = Long.parseLong(passedVar);
				MyDbManager ex1 = new MyDbManager(this);
				ex1.open();
				ex1.deleteEntry(lRow1);
				ex1.close();

			} catch (Exception e) {

				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("oh dear");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.show();
			} finally {
				if (didItWork1) {
					deleteSound.start();
					Dialog d = new Dialog(this);
					d.setTitle("Module Deleted");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.show();
				}
			}

			break;

		}// end the switch

	}// end the onclick

}// end
