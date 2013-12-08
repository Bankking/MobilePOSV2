package ku.mobilepos.dao.jpa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JpaCustomerDao  extends SQLiteOpenHelper{
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "mobilepos";

	// Table Name
	private static final String TABLE_CUSTOMER = "customer";

	public JpaCustomerDao(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE customer" + 
		          "(CustomerID INTEGER PRIMARY KEY AUTOINCREMENT," +
		          " Cus_Name TEXT(100)," +
		          " Cus_Phone TEXT(100));");
		Log.d("CREATE TABLE","Create Table Customer Successfully.");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	};
	
	public long InsertData(String strName, String strTel) {
		// TODO Auto-generated method stub
		
		 try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
	    		
	    
			
	    	   ContentValues Val = new ContentValues();
	    	
	    	   Val.put("Cus_Name", strName);
	    	   Val.put("Cus_Phone", strTel);
	   
			long rows = db.insert(TABLE_CUSTOMER, null, Val);

			db.close();
			return rows; // return rows inserted.
	          
		 } catch (Exception e) {
		    return -1;
		 }

	}

}
