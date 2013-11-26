package ku.mobilepos.domain;

import java.util.List;

import ku.mobilepos.controller.CustomerController;

public interface Customer {
	public void addCustomer(CustomerController c);
	public void removeCustomer(String cusId);
	public CustomerController getCustomerByName(String cusName);
	public CustomerController getCustomerById(String cusId);
	public CustomerController getCustomerByPhoneNo(String cusPhoneNo);
	public List<CustomerController> getCustomerList();
	public boolean isEmpty();

}
