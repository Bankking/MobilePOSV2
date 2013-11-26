package ku.mobilepos.activity;

import ku.mobilepos.controller.CustomerController;
import ku.mobilepos.domain.Customer;
import ku.mobilepos.domain.CustomerList;

import com.example.mobileposv2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CustomerAddNewCustomerActivity extends Activity {

	private EditText cusName;
	private EditText cusPhoneNo;
	private Button confirmButton;
	private Button cancelButton;

	/** new customer **/
	private Customer customerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_page);

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
