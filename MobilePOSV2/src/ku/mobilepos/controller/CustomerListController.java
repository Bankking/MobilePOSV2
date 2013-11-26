package ku.mobilepos.controller;

import java.util.List;

import ku.mobilepos.domain.Customer;


public interface CustomerListController {
	public void addCustomer(Customer c);
	public void removeCustomer(String cusId);
	public Customer getCustomerByName(String cusName);
	public Customer getCustomerById(String cusId);
	public Customer getCustomerByPhoneNo(String cusPhoneNo);
	public List<Customer> getCustomerList();
	public boolean isEmpty();

}
