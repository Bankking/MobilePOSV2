package ku.mobilepos.controller;

import java.util.List;




public interface InventoryController {
	public void addItem(CartController i);
	public void removeItem();
	public CartController getItemByName(String itemName);
	public CartController getItemById(String itemId);
	public CartController getItemByPostion(int i);
	public List<CartController> getItemList();
	public boolean isEmpty();

	

}
