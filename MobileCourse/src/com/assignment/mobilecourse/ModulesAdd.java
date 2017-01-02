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

public class ModulesAdd extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView;
	EditText sqlName, sqlCode, sqlCredits, sqlRow;

	// MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_module_add);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlCode = (EditText) findViewById(R.id.etSQLCode);
		sqlCredits = (EditText) findViewById(R.id.etSQLCredits);

		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		//sqlView = (Button) findViewById(R.id.bSQLView);

		sqlUpdate.setOnClickListener(this);
		//sqlView.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		MediaPlayer ourSong = MediaPlayer.create(ModulesAdd.this, R.raw.madd);
		
		switch (arg0.getId()) {

		case R.id.bSQLUpdate:
			boolean didItWork = true;
			try {

				String module_name = sqlName.getText().toString();
				String module_code = sqlCode.getText().toString();
				String module_credits = sqlCredits.getText().toString();

				MyDbManager entry = new MyDbManager(ModulesAdd.this);
				entry.open();

				entry.insertModule(module_name, module_code, module_credits);
				entry.close();

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
					ourSong.start();
					Dialog d = new Dialog(this);
					d.setTitle("Module Added");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.show();
				}
			}

			break;

		
		}
	}

}// end
