package ku.mobilepos.domain;

import java.util.ArrayList;
import java.util.List;

import ku.mobilepos.controller.CartController;
import ku.mobilepos.controller.InventoryController;


public class Inventory implements InventoryController {
	private List<CartController> itemList = null;
	private static InventoryController inventory = null;
	
	private Inventory(){
		
	}
	public static InventoryController getInstance(){
		
		if (inventory == null) inventory = new Inventory();
		return inventory;
		
	}
	
	public List<CartController> getItemList(){
		if (itemList == null) itemList = new ArrayList<CartController>();
		return itemList;
	}
	
	@Override
	public void addItem(CartController i) {
		// TODO Auto-generated method stub
		itemList.add(i);
	}

	@Override
	public void removeItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CartController getItemByName(String itemName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(itemName)){
				return itemList.get(i);
			}
		}
		return null;
	}
	
	@Override
	public CartController getItemById(String itemId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemId().equals(itemId)){
				return itemList.get(i);
			}
		}
		return null;
	}
	
	public CartController getItemByPostion(int i) {
		// TODO Auto-generated method stub
		
		return itemList.get(i);
			
	}

	

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (itemList.size()!=0){
			return false;
		}		
		return  true;
	}

	
}
