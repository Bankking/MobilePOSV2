package ku.mobilepos.activity;

import java.util.List;

import ku.mobilepos.controller.CartController;
import ku.mobilepos.controller.InventoryController;
import ku.mobilepos.domain.Inventory;


import com.example.mobileposv2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryAddMoreItemActivity extends Activity {
	
	private EditText itemName;
	private EditText itemQntyType;
	private EditText itemBrand;
	private EditText itemPrice;
	private EditText itemProductId;
	private EditText itemBuyPricePerBox;
	
	private TextView itemBuyPriceCal;
	private TextView itemBuyPriceText;
	private TextView itemBuyType;
	private TextView itemBuyBahtPerType;
	private TextView formatTxt;
	private TextView contentTxt;
	
	private Button confirmButton;
	private Button cancelButton;
	private ImageButton scanBtn;
	
	private CartController newItem;
	private Inventory inventory;
	
	private String pricePerPiece;
	
	private TabHost myTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_add_page);
		inventory = InventoryController.getInstance();
		//itemList = inventory.getItemList();
		newItem = new CartController();
		createAllFindViewById();
		setAllEditTextToOneLine();
        /*scanBtn.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v){
	    		//check for scan button
	    		if(v.getId()==R.id.scan_button){
	    			//instantiate ZXing integration class
	    			IntentIntegrator scanIntegrator = new IntentIntegrator(null);
	    			//start scanning
	    			scanIntegrator.initiateScan();
	    		}
	    	}
    	});*/
		
		/*public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			//retrieve result of scanning - instantiate ZXing object
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
			//check we have a valid result
			if (scanningResult != null) {
				//get content from Intent Result
				String scanContent = scanningResult.getContents();
				//get format name of data scanned
				String scanFormat = scanningResult.getFormatName();
				//output to UI
				formatTxt.setText("FORMAT: "+scanFormat);
				contentTxt.setText("CONTENT: "+scanContent);
			}
			else{
				//invalid scan data or scan canceled
				Toast toast = Toast.makeText(getApplicationContext(), 
						"No scan data received!", Toast.LENGTH_SHORT);
				toast.show();
			}
		}*/
        
		/**
		 * when select cancel button it will go back to inventory page
		 */
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent goInventory = new Intent(getApplicationContext(),
				MainActivity.class);
				
				startActivity(goInventory);
				
			}
		});
		
		/**
		 * when select confirm button it will add new item to inventory and go back to inventory page
		 */
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(itemName.getText().toString().equals("") 
				|| itemBrand.getText().toString().equals("") 
				|| itemPrice.getText().toString().equals("") )
				{
					Toast.makeText(getApplicationContext(),"Please fill all blank.", Toast.LENGTH_LONG)
	  		        .show();
				}
				else
				{
					newItem.setItemId(itemProductId.getText().toString());
					newItem.setItemName(itemName.getText().toString());
					newItem.setItemBrand(itemBrand.getText().toString());
					String totalPiece;
					totalPiece = calculateTotalPiece(itemQntyType.getText().toString());
					newItem.setItemQnty(totalPiece);
					newItem.setItemBuyPiece(itemQntyType.getText().toString());
					newItem.setItemPrice(itemPrice.getText().toString());
					newItem.setItemBuyPriceBahtPerBox(itemBuyPricePerBox.getText().toString());
					inventory.addItem(newItem);		
					
					Intent goInventory = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(goInventory);
				}
				
			}
		});

		/**
		 * check if product is already in inventory
		 */
//		checkButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				if (itemProductId.getText() != null){
//					
//					//newItem.setItemId(itemProductId.getText().toString());
//					if (inventory.getItemById(itemProductId.getText().toString())!=null){
//						CartController item = inventory.getItemById(itemProductId.getText().toString());
//						itemName.setText(item.getItemName());
//						itemBrand.setText(item.getItemBrand());
//						itemQntyType.setText(item.getItemBuyPiece());
//						itemBuyPricePerBox.setText(""+item.getItemBuyPriceBahtPerBox());
//						//itemPiecePerBox.setText(""+item.getItemPiecePerBox());
//						itemBuyPriceCal.setText("  "+item.getItemPricePerPiece()+"  ");
//						itemPrice.setText(""+item.getItemPrice());
//					}
//				}
//			}
//		});
	}
	
	
	
	/**
	 * a = number of box, b = p = cost per box
	 * @param box is number of total box
	 * @param piecePerBox is cost per box
	 * @return a * b or total cost of all box
	 */
	public String calculateTotalPiece(String box){
		int a = Integer.parseInt(box);
		return a+"";
	}
	
	/**
	 * a is box, b is total bath
	 * @param box is number of total box
	 * @param totalBaht is total cost in unit baht
	 * @return a * b or total cost of box in unit baht
	 */
	public String calculateTotalBuyBaht(String box,String totalBaht){
		int a = Integer.parseInt(box),b = Integer.parseInt(totalBaht);
		return a*b+"";
	}
	
	/**
	 * create attribute b for convert string of price to integer
	 * @param piece total quantity of product
	 * @param price is cost of product
	 * @return price / piece
	 */
	public String calculatePricePerPiece(int piece, String price){
		double b = Double.parseDouble(price);
		return b/piece+"";
	}

	/**
	 * create all xx = findViewById(xxx);
	 */
	public void createAllFindViewById() {
		//EditText
		itemName = (EditText) findViewById(R.id.inventory_add_productname);
		itemQntyType = (EditText) findViewById(R.id.inventory_add_quantity);
		itemBrand = (EditText) findViewById(R.id.inventory_add_productbrand);
		itemPrice = (EditText) findViewById(R.id.inventory_add_sellprice);
		//itemPiecePerBox = (EditText) findViewById(R.id.inventory_amp_f_pieceperbox);
		itemProductId = (EditText) findViewById(R.id.inventory_add_productid);
		itemBuyPricePerBox = (EditText) findViewById(R.id.inventory_add_price);
		
		//Button
		confirmButton = (Button) findViewById(R.id.inventory_add_confirm);
		cancelButton = (Button) findViewById(R.id.inventory_add_cancel);
		//checkButton = (Button) findViewById(R.id.inventory_amp_b_check);
		scanBtn = (ImageButton)findViewById(R.id.inventory_add_scan);      
		
	}

	
	/**
	 * Set all EditText to be one single line
	 */
	public void setAllEditTextToOneLine(){
		setMaxLineText(itemName);
		setMaxLineText(itemBrand);
		//setMaxLineText(itemPiecePerBox);
		setMaxLineText(itemPrice);
		setMaxLineText(itemQntyType);
		setMaxLineText(itemProductId);
		
	}
	
	/**
	 * update text price per piece
	 * @param mEditText receive mEditText
	 */
	public void updatePricePerPiece(EditText mEditText){
		mEditText.setOnKeyListener(new View.OnKeyListener() {       
	        public boolean onKey(View v, int keyCode, KeyEvent event) {
	            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
	            	itemBuyPriceCal.setText("  "+pricePerPiece+"  ");	
	            }
	            return false;
	        }
		});
	}
	
	/**
	 * Set EditText to be one single line (cannot press enter to enter the new line) 
	 */
	public void setMaxLineText(EditText mEditText){
		mEditText.setOnKeyListener(new View.OnKeyListener() {
	        public boolean onKey(View v, int keyCode, KeyEvent event) {
	        	if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
	        		String text = ((EditText) v).getText().toString();
	        		int editTextRowCount = text.split("\\n").length;
	                if (editTextRowCount >= 1) 
	                {
	                    int lastBreakIndex = text.lastIndexOf("\n");
	                    String newText = Integer.toString(lastBreakIndex);
	                    newText = text.substring(0);
	                    ((EditText) v).setText("");
	                    ((EditText) v).append(newText);
	                }
	            }
	            return false;
	        }
		});
	}
}
	
