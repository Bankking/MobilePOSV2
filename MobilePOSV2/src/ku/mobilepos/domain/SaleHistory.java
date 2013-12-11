package ku.mobilepos.domain;

import java.util.ArrayList;
import java.util.List;

import ku.mobilepos.controller.CartController;

public class SaleHistory {
	
	private static SaleHistory saleHistory = null;
	private static List<List<CartController>> itemBoughtList = null;
	private static List<String> cusPhone = null; 

	private SaleHistory(){
		
	}
	
	//if not have cart make cart
	public static SaleHistory getHisInstance() {
		if (saleHistory == null){
			saleHistory = new SaleHistory();
			itemBoughtList = new ArrayList<List<CartController>>();
			cusPhone = new ArrayList<String>();
			
		}
		return saleHistory;
	}
	
	//check the item in cart
	public List<List<CartController>> getItemBoughtList() {
		return itemBoughtList;
	}
	
	public List<String> getCusPhone(){
		return cusPhone;
	}
	
	public String getAllBought(int index){
		String bought="";
		for (int i = 0; i < itemBoughtList.get(index).size(); i++) {
			bought = itemBoughtList.get(index).get(i).getItemName()+"\n";
		}
		return bought;
	}
	
	public void setItemBoughtList(List<CartController> itemBoughtList, String cusPhoneNo){
		cusPhone.add(cusPhoneNo);
		this.itemBoughtList.add(itemBoughtList);
		
		
	}

}
