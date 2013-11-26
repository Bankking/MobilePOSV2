package ku.mobilepos.domain;

import java.util.ArrayList;
import java.util.List;

import ku.mobilepos.controller.CustomerListController;


public class CustomerList implements CustomerListController{
	
	private static CustomerListController customerList = null;
	private static List<Customer> cusList = null;
	
	private CustomerList(){
		
	}
	
	public static CustomerListController getInstance(){
		if (customerList == null) {
			customerList = new CustomerList();
			cusList = new ArrayList<Customer>();
		}
		return customerList;
	}
	
	@Override
	public void addCustomer(Customer c) {
		// TODO Auto-generated method stub
		cusList.add(c);
	}

	@Override
	public void removeCustomer(String cusId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cusList.size(); i++) {
			if (cusList.get(i).getCusId().equals(cusId)) {
				cusList.remove(i);
				break;
			}
		}
		
	}

	@Override
	public Customer getCustomerByName(String cusName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cusList.size(); i++) {
			if (cusList.get(i).getCusName().equals(cusName)) {
				return cusList.get(i);
			}
		}
		return null;
	}

	@Override
	public Customer getCustomerById(String cusId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cusList.size(); i++) {
			if (cusList.get(i).getCusId().equals(cusId)) {
				return cusList.get(i);
			}
		}
		return null;
	}

	@Override
	public Customer getCustomerByPhoneNo(String cusPhoneNo) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cusList.size(); i++) {
			if (cusList.get(i).getCusPhoneNo().equals(cusPhoneNo)) {
				return cusList.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		return cusList;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (cusList.size() == 0) return true;
		return false;
	}

}
