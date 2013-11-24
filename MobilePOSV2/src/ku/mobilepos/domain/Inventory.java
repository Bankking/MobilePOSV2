package ku.mobilepos.domain;

import java.util.List;

import ku.mobilepos.controller.CartController;


public interface Inventory {
	public void addItem(CartController i);
	public void removeItem();
	public CartController getItemByName(String itemName);
	public CartController getItemById(String itemId);
	public CartController getItemByPostion(int i);
	public List<CartController> getItemList();
	public boolean isEmpty();

	

}
