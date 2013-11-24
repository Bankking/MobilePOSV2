package ku.mobilepos.domain;

import java.util.ArrayList;
import java.util.List;

import ku.mobilepos.controller.CartController;
import android.util.Log;
//cart for add the list of item in.
public class Cart {
	private static Cart cart = null;
	private List<CartController> itemInCart = null;
	private List<Integer> quantityItemInCart = null;
	private double totalSale;
	private Cart(){
		
	}
	
	//if not have cart make cart
	public static Cart getCartInstance() {
		if (cart == null) cart = new Cart();
		return cart;
	}
	
	//check the item in cart
	public List<CartController> getItemListInCart() {
		if (itemInCart == null) { 
			itemInCart = new ArrayList<CartController>();
			quantityItemInCart = new ArrayList<Integer>();
		}
		return itemInCart;
	}
	
	//get the quantity item in cart
	public Integer getItemQuantity(int i){
		return quantityItemInCart.get(i);
	}
	
	/* add item to cart
	 * @param item
	 */
	public void addToCart(CartController item) {
		boolean inCart = false;
		int position = 0;
		for (int i = 0; i < itemInCart.size(); i++) {
			if (item == itemInCart.get(i)){
				inCart = true;
				position = i;
				break;
			}
		}
		if (inCart){
			int j = quantityItemInCart.get(position) + 1;
			quantityItemInCart.set(position, j);
			Log.d("aaaaaa", "aaaaaaaaaaaaaaa");
		}
		else {
			itemInCart.add(item);
			quantityItemInCart.add(1);
		}
		
		calculateSale(item);
		
	}
	
	/*remove item in cart
	 * @param position of item
	 */
	public void removeItem(int position) {
		itemInCart.remove(position);
	}
	
	//reset cart
	public void resetCart(){
		this.totalSale = 0.0;
		this.quantityItemInCart = null;
		this.itemInCart = null;
		this.cart = null;
	}
	
	/*calculate total price
	 * @param item 
	 * @return total price
	*/
	public void calculateSale(CartController item){
		
			totalSale += item.getItemPrice();
		
	}
	
	/*calculate total sale
	 * @return total sale
	 */
	public double getTotalSale(){
		return totalSale;
	}
	

}
