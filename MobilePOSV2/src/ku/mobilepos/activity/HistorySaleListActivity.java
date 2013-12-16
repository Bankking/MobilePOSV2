package ku.mobilepos.activity;

import java.util.List;

import ku.mobilepos.controller.CartController;
import ku.mobilepos.controller.InventoryController;
import ku.mobilepos.domain.Cart;
import ku.mobilepos.domain.Inventory;


import com.example.mobileposv2.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class HistorySaleListActivity extends Activity {
	private MainActivity mainactivity;
	private ListView history;
	private List phoneNO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Listview
		history = (ListView) findViewById(R.id.history_itemlist);
	}
	
//	public void AddItemHistory(String phone)
	//{
		//if (.getCustomerList().size()!=0){
	//}
}
