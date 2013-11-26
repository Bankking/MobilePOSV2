package ku.mobilepos.activity;

import ku.mobilepos.controller.InventoryController;
import ku.mobilepos.domain.Cart;
import ku.mobilepos.domain.Inventory;

import com.example.mobileposv2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private ImageButton addToCartButton;
	private ImageButton clearButton;
	private Button confirmButton;
	private ListView itemInCartList;
	private TextView totalPriceTextView;
	private double totalPrice;
	private Cart cart;
	private Inventory inventory;
	private String[] itemInCartListStringArr;
	
	private ImageButton addButton;
	/** list of product */
	private ListView allItemList;
	private String[] inventoryString;
	private String[] inventoryListStringArr;
	private Button scanBtn;
  	private TextView formatTxt, contentTxt;
  	private int itemPosition;
  	
	
	@Override
	public void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.main);
		TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
		
		addToCartButton = (ImageButton) findViewById(R.id.sale_add);
		clearButton = (ImageButton) findViewById(R.id.sale_clear);
		confirmButton = (Button) findViewById(R.id.sale_confirm);
		totalPriceTextView = (TextView) findViewById(R.id.sale_tatalprice);
		itemInCartList = (ListView) findViewById(R.id.sale_itemlist);
		
		addButton = (ImageButton)findViewById(R.id.inventory_add);
	
		tabHost.setup();
		TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1");
		
		tabSpec.setContent(R.id.tab1);
		tabSpec.setIndicator("Sale");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tab2");
		tabSpec.setContent(R.id.tab2);
		tabSpec.setIndicator("Inventory");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tab3");
		tabSpec.setContent(R.id.tab3);
		tabSpec.setIndicator("Sale-History");
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tab4");
		tabSpec.setContent(R.id.tab4);
		tabSpec.setIndicator("Customer");
		tabHost.addTab(tabSpec);
			
		inventory = InventoryController.getInstance();
		cart = Cart.getCartInstance();
		
		createItemSaleListStringArr();
		totalPriceTextView.setText(cart.getTotalSale()+" .-");
		
		addToCartButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (inventory.isEmpty()) {
					Toast.makeText(
							getApplicationContext(),
							"Your inventory is empty\nPlease go to inventory to an item first",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent goSaleSelectItem = new Intent(
							getApplicationContext(),
							SaleSelectItemActivity.class);
					startActivity(goSaleSelectItem);
				}
				//showSaleAddDialog();
			}
		});
		
		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*// TODO Auto-generated method stub
				cart.resetCart();
				Intent refreshCart = new Intent(getApplicationContext(),MainActivity.class);
    			startActivity(refreshCart);
				*/
				cart.resetCart();
				itemInCartList.refreshDrawableState();
				Toast.makeText(
						getApplicationContext(),
						"Sale List is reset.",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//inventory = InventoryController.getInstance();
        createItemListStringArr();
        
        addButton.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		
        		Intent goAddMoreProduct = new Intent(getApplicationContext(), InventoryAddMoreItemActivity.class);
        		startActivity(goAddMoreProduct);
        		
        	}
        });
		
	}
	
	public void createItemSaleListStringArr() {
		if (cart.getItemListInCart().size() != 0) {
			itemInCartListStringArr = new String[cart.getItemListInCart()
				.size()];
			for (int i = 0; i < itemInCartListStringArr.length; i++) {
				itemInCartListStringArr[i] = "Product name: " + cart.getItemListInCart().get(i)
						.getItemName()+"\nPrice: "+cart.getItemListInCart().get(i).getItemPrice()+"\nQuantity: "+cart.getItemQuantity(i);
				
			}
		
			itemInCartList = (ListView)findViewById(R.id.sale_itemlist);
			ArrayAdapter<String> itemListAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_list_item_1,
					android.R.id.text1, itemInCartListStringArr);
			itemInCartList.setAdapter(itemListAdapter);
			
			
			itemInCartList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub

					int itemPosition = position;
					String itemValue = (String) itemInCartList
							.getItemAtPosition(position);
					// Show Alert
					Toast.makeText(getApplicationContext(), "click",
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}
	
	public void createItemListStringArr(){
    	if (inventory.getItemList().size()!=0){
        	inventoryListStringArr = new String[inventory.getItemList().size()];
        	for (int i = 0; i < inventoryListStringArr.length; i++) {
    			inventoryListStringArr[i] =  "\nProduct Name: "+inventory.getItemList().get(i).getItemName().toString() +"\nSell Price: "
    										+inventory.getItemList().get(i).getItemPrice();
    		}
        	
        	allItemList = (ListView)findViewById(R.id.inventory_itemlist);
    		ArrayAdapter<String> itemListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, inventoryListStringArr);
    		allItemList.setAdapter(itemListAdapter); 
    		allItemList.setOnItemClickListener(new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    				// TODO Auto-generated method stub
    				
    				int itemPosition = position;
    				String  itemValue = (String)allItemList.getItemAtPosition(position);            
    		        // Show Alert 
    		        Toast.makeText(getApplicationContext(),
    		        "Position :"+ (itemPosition+1) +"\nQuantity : "+inventory.getItemByPostion(position).getItemQnty()+"\nBrand : "
    		        +inventory.getItemByPostion(position).getItemBrand() , Toast.LENGTH_LONG)
    		        .show();
    			}
    		});
        }
    }
	
	public void showSaleAddDialog()
	{
        final AlertDialog.Builder saleAddDialog = new AlertDialog.Builder(this);
        final EditText saleId = new EditText(this);

        saleAddDialog.setMessage("Input product ID");
        saleAddDialog.setView(saleId);
    	// Add
        saleAddDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			String value = saleId.getText().toString();
    			
    			
    			
    		  }
    		});
        
    	
    	// Done
        saleAddDialog.setNegativeButton("Done", new DialogInterface.OnClickListener() {
    		  public void onClick(DialogInterface dialog, int whichButton) {
    			  String value = saleId.getText().toString();
    		  }
    		});
    	
        saleAddDialog.show();
    	
	}
	
	//Menu Activity
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Exit dialog
	@Override
	public void onBackPressed() {
		AlertDialog.Builder exitBuilder = new AlertDialog.Builder(MainActivity.this);
		exitBuilder.setTitle("Exit this application?")
		.setPositiveButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}
		})
		.setNegativeButton("Yes", new DialogInterface.OnClickListener( ) {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
				
			}
		});
		
		AlertDialog alert = exitBuilder.create();
		alert.show();

	}
}
