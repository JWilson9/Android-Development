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

public class AssignmentAdd extends Activity implements OnClickListener {

	Button Add_assignment;
	EditText Assignment_name, Assignment_due;

	// assigning edittext and buttons to where they are located
	// in the xml file
	// setting the content view
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assignment_add);

		Assignment_name = (EditText) findViewById(R.id.AssignmentName);
		Assignment_due = (EditText) findViewById(R.id.AssignmentDue);

		Add_assignment = (Button) findViewById(R.id.bAddAssignment);

		Add_assignment.setOnClickListener(this);
	}

	// when a button it clicked it does the following
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		// added sound to my button so when it clicks it executes the following
		MediaPlayer addSound = MediaPlayer.create(AssignmentAdd.this,
				R.raw.assadded);

		switch (arg0.getId()) {

		// if the add button is clickd it does the following
		case R.id.bAddAssignment:
			boolean didItWork = true;
			try {

				// getting the text from the assignment name and due
				// and putting them into AssignmentName, AssignmentDue
				String AssignmentName = Assignment_name.getText().toString();
				String AssignmentDue = Assignment_due.getText().toString();

				//AssignmentEntry is now the db var
				//opening to insert
				MyDbManager AssignmentEntry = new MyDbManager(
						AssignmentAdd.this);
				AssignmentEntry.open();

				AssignmentEntry.insertNewAssign(AssignmentName, AssignmentDue);
				AssignmentEntry.close();

				
				//catch and exeception
				//if it works or if it didnt, it prints out
				//what is appropriate
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("oh dear");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.show();
			} finally {
				if (didItWork) {
					addSound.start();
					Dialog d = new Dialog(this);
					d.setTitle("Assignment Added");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.show();

				}
			}

			break;

		}
	}
}
