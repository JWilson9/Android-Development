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
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		
		Thread  timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally {
					Intent openStartingPoint = new Intent(Splash.this, MainActivity.class);
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// so the 1st activity destroys itself
		finish();
	}
	

}
