package ku.mobilepos.dao.jpa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JpaInventoryDao extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "mobilepos";
	
	// Table Name
	private static final String TABLE_PRODUCT = "product";

	public JpaInventoryDao(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE product " +

		"(ProductID INTEGER PRIMARY KEY," +

		" Product_Name TEXT(100)," +

		" Product_Brand TEXT(100)," +

		" Product_Qnty INTEGER," +

		" Product_Price DOUBLE);");

		Log.d("CREATE TABLE", "Create Table inventory Successfully.");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public long InsertData(String strProductID, String strName, String strBrand, String strProductQnty, String strProductPrice) {
		// TODO Auto-generated method stub

		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			ContentValues Val = new ContentValues();
			Val.put("ProductID", strProductID);
			Val.put("Product_Name", strName);
			Val.put("Product_Brand", strBrand);
			Val.put("Product_Qnty", strProductQnty);
			Val.put("Product_Price", strProductPrice);
			long rows = db.insert(TABLE_PRODUCT, null, Val);
			db.close();

			return rows; // return rows inserted.
		} catch (Exception e) {
			return -1;
		}
	}

}
