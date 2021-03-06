package ku.mobilepos.activity;

import ku.mobilepos.controller.CustomerController;
import ku.mobilepos.dao.jpa.JpaCustomerDao;
import ku.mobilepos.dao.jpa.JpaInventoryDao;
import ku.mobilepos.domain.Customer;
import ku.mobilepos.domain.CustomerList;

import com.example.mobileposv2.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerAddNewCustomerActivity extends Activity {
	

	SQLiteDatabase db;
	/** name of customer */
	private EditText cusName;
	/** customer's phone number */
	private EditText cusPhoneNo;
	/** button to confirm for add product */
	private Button confirmButton;
	/** button to cancel product */
	private Button cancelButton;

	/** new customer **/
	private Customer customerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_page);
		
		final JpaCustomerDao myDb = new JpaCustomerDao(this);
		myDb.getWritableDatabase(); // First method

		customerList = CustomerList.getInstance();
		setupAddNewCustomerActivity();
		
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CustomerController newCustomer = new CustomerController();
				newCustomer.setCusName(cusName.getText().toString());
				newCustomer.setCusPhoneNo(cusPhoneNo.getText().toString());
				newCustomer.setCusId(customerList.getCustomerList().size());
				customerList.addCustomer(newCustomer);
				long flg1 = myDb.InsertData(cusName.getText().toString(), cusPhoneNo.getText().toString());
				if(flg1 > 0)

				{

				Toast.makeText(CustomerAddNewCustomerActivity.this,"Insert NEW CUSTOMER Successfully",
			
				Toast.LENGTH_LONG).show();
				
				}
				
				else
				
				{
				
				Toast.makeText(CustomerAddNewCustomerActivity.this,"Insert NEW CUSTOMER Failed.",
				
				Toast.LENGTH_LONG).show();
				
				}
				
				Intent goCusMain = new Intent(getApplicationContext(),
						MainActivity.class);

						startActivity(goCusMain);
				
			}
		});

		/**
		 * when select cancel button it will go back to inventory page
		 */
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent goCusMain = new Intent(getApplicationContext(),
				MainActivity.class);

				startActivity(goCusMain);
			}
		});
	}
	
	public void setupAddNewCustomerActivity(){
		cusName = (EditText) findViewById(R.id.customer_add_name);
		cusPhoneNo = (EditText) findViewById(R.id.customer_add_phone);
		confirmButton = (Button) findViewById(R.id.customer_add_confirm);
		cancelButton = (Button) findViewById(R.id.customer_add_cancel);
	}

}
