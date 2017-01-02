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


public class Assignments extends Activity implements OnClickListener {
	
	//init the buttons, the edittext and the string
	Button edit, delete;
	EditText AssignmentName, AssignmentDue;
	String passedVar = null;
	
	MyDbManager myDb = new MyDbManager(Assignments.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assignment);

		//the edittext for name and due
		AssignmentName = (EditText) findViewById(R.id.etSQLName);
		AssignmentDue = (EditText) findViewById(R.id.etSQLDue);

		//buttons for the edit and delete
		edit = (Button) findViewById(R.id.bEditAssign);
		delete = (Button) findViewById(R.id.bDeleteAssign);
		
		
		//open the database and pass the columns into the edit
		//eg the name of the assignment & when it is due
		myDb.open();
		//getting the id of which row it is in the db
		passedVar = getIntent().getStringExtra(AssignmentList.ID_EXTRA1);
		long l = Long.parseLong(passedVar); //
		String returnedAssignmentName = myDb.getAssign_Name(l);
		String returnedAssignmentDue = myDb.getAssign_Due(l);
		myDb.close();

		//set the text
		AssignmentName.setText(returnedAssignmentName);
		AssignmentDue.setText(returnedAssignmentDue);

		//info.close();

		
		
		
		edit.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		//play a sound when clicked
		MediaPlayer deleteSound = MediaPlayer.create(Assignments.this, R.raw.assigndel);
		
		switch (arg0.getId()) {
			
		case R.id.bEditAssign:
			boolean didItWork = true;
			didItWork = true;
			try {
				String mAssignmentName = AssignmentName.getText().toString();
				String mDue = AssignmentDue.getText().toString();

				passedVar = getIntent().getStringExtra(AssignmentList.ID_EXTRA1);
				long lRow = Long.parseLong(passedVar);

				//MyDbManager edit = new MyDbManager(this);
				myDb.open();
				myDb.updateAssignments(lRow, mAssignmentName, mDue);
				myDb.close();

				//if it worked or not
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
			
			
		case R.id.bDeleteAssign:
			boolean didItWork1 = true;
			try {
				passedVar = getIntent().getStringExtra(AssignmentList.ID_EXTRA1);
				long lRow1 = Long.parseLong(passedVar);
				//MyDbManager ex1 = new MyDbManager(this);
				myDb.open();
				myDb.deleteAssignment(lRow1);
				myDb.close();

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
					d.setTitle("Assignment Deleted");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.show();
				}
			}

			break;
			
		}
		
		
	}

}
