package ku.mobilepos.controller;

import java.util.ArrayList;
import java.util.List;

import ku.mobilepos.domain.Inventory;


public class InventoryController implements Inventory {
	private List<CartController> itemList = null;
	private static Inventory inventory = null;
	
	private InventoryController(){
		
	}
	public static Inventory getInstance(){
		
		if (inventory == null) inventory = new InventoryController();
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
