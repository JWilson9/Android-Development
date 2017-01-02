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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbManager {

	private static final String DATABASE_PATH = "/data/data/com.assignment.mobilecourse/databases";

	private static final String DATABASE_NAME = "Modules10";

	private static final String DATABASE_TABLE = "Module_Details";
	private static final String NEW_TABLE = "new_table";

	private static final String ASSIGNMENT_TABLE = "Assignment_Details";

	//
	private static final int DATABASE_VERSION = 1;

	// These are the names of the columns the table will contain
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "module_name";
	public static final String KEY_CODE = "module_code";
	public static final String KEY_CREDITS = "module_credits";

	// make columns for assignment table
	public static final String COULMN_ROW = "_id";
	public static final String COULMN_NEWNAME = "newname";
	public static final String COULMN_NEWDUE = "newdue";

	// This is the string containing the SQL database create statement
	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + "(_id integer primary key autoincrement, "
			+ "module_name text not null, " + "module_code text not null, "
			+ "module_credits text not null);";

	// now create for assignment table
	private static final String DATABASE_CREATE_NEW = "create table "
			+ NEW_TABLE + "(_id integer primary key autoincrement, "
			+ "newname text not null, " + "newdue text not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	// constructor for your class
	public MyDbManager(Context ctx) {
		// Context is a way that Android transfers info about Activities and
		// apps.
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		// constructor for your dB helper class. This code is standard. You’ve
		// set
		// up the parameter values for the constructor already…database name,etc
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
			// db.execSQL(DATABASE_CREATE_A);
			Log.i("test", DATABASE_CREATE_NEW);
			db.execSQL(DATABASE_CREATE_NEW);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// db.execSQL("DROP TABLE IF EXISTS DATABASE_TABLE");
			// db.execSQL("DROP TABLE IF EXISTS DATABASE_TABLE_A");
			// db.execSQL("DROP TABLE IF EXISTS NEW_TABLE");

		}

	}// end help class

	// ---opens the database--- any activity that uses the dB will need to do
	// this
	public MyDbManager open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database--- any activity that uses the dB will need to do
	// this
	public void close() {
		DBHelper.close();
	}

	// ---insert a module into the database---
	public long insertModule(String module_name, String module_code,
			String module_credits) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, module_name);
		initialValues.put(KEY_CODE, module_code);
		initialValues.put(KEY_CREDITS, module_credits);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// INSERT INTO NEW TABLE
	// ---insert a module into the database---
	public long insertNewAssign(String name_assign, String due_assign) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COULMN_NEWNAME, name_assign);
		initialValues.put(COULMN_NEWDUE, due_assign);
		return db.insert(NEW_TABLE, null, initialValues);
	}

	// ---retrieves all the rows---

	// this is the cursor where im returning the columns
	public Cursor getAllModules() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_CODE, KEY_CREDITS }, null, null, null, null, null);
	}

	public Cursor getAllNewAssign() {
		return db.query(NEW_TABLE, new String[] { COULMN_ROW, COULMN_NEWNAME,
				COULMN_NEWDUE }, null, null, null, null, null);
	}

	// /////////////////getAllAssignments

	// ---retrieves a particular row---
	// long rowId
	public Cursor getModule(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_CODE, KEY_CREDITS }, KEY_ROWID + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public String getName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_CODE,
				KEY_CREDITS };
		Cursor c = db.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public String getCode(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_CODE,
				KEY_CREDITS };
		Cursor c = db.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
			String code = c.getString(2);
			return code;
		}
		return null;
	}

	public String getCredits(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_CODE,
				KEY_CREDITS };
		Cursor c = db.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
			String credits = c.getString(3);
			return credits;
		}
		return null;
	}

	// ////////////GET ASSIGNMENT DETAILS/////////////////////////
	public String getAssign_Name(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COULMN_ROW, COULMN_NEWNAME,
				COULMN_NEWDUE };
		Cursor c = db.query(NEW_TABLE, columns, COULMN_ROW + "=" + l, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public String getAssign_Due(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { COULMN_ROW, COULMN_NEWNAME,
				COULMN_NEWDUE };
		Cursor c = db.query(NEW_TABLE, columns, COULMN_ROW + "=" + l, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
			String due = c.getString(2);
			return due;
		}
		return null;
	}

	// ----------------update tables -----------------------------
	public void updateEntry(long lRow, String mName, String mCode,
			String mCredits) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_CODE, mCode);
		cvUpdate.put(KEY_CREDITS, mCredits);
		db.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}

	public void updateAssignments(long lRow, String mAssignmentName,
			String mAssignmentDue) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(COULMN_NEWNAME, mAssignmentName);
		cvUpdate.put(COULMN_NEWDUE, mAssignmentDue);
		db.update(NEW_TABLE, cvUpdate, COULMN_ROW + "=" + lRow, null);
	}

	// ------------------DELETE DATA FROM THE DATABASE---------------
	public void deleteEntry(long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		db.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);

	}

	public void deleteAssignment(long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		db.delete(NEW_TABLE, COULMN_ROW + "=" + lRow1, null);

	}

	// this is were we search and if name is same it displays it
	public Cursor filterModulesName(String inputTxt) throws SQLException {

		Cursor theCursor = null;
		if (inputTxt == null || inputTxt.length() == 0) {
			theCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
					KEY_NAME, KEY_CODE, KEY_CREDITS }, null, null, null, null,
					null);

		} else {
			theCursor = db.query(true, DATABASE_TABLE, new String[] {
					KEY_ROWID, KEY_NAME, KEY_CODE, KEY_CREDITS }, KEY_NAME
					+ " like '%" + inputTxt + "%'", null, null, null, null,
					null);
		}
		if (theCursor != null) {
			theCursor.moveToFirst();
		}
		return theCursor;

	}

}
