package ku.mobilepos.domain;

public class Customer {
	private String cusId;
	private String cusName;
	private String cusPhoneNo;
	
	public void setCusId(int i){
		this.cusId = ""+i;
	}
	public void setCusName(String cusName){
		this.cusName = cusName;
	}
	public void setCusPhoneNo(String cusPhoneNo){
		this.cusPhoneNo = cusPhoneNo;
	}
	public String getCusId(){
		return cusId;
	}
	public String getCusName(){
		return cusName;
	}
	public String getCusPhoneNo(){
		return cusPhoneNo;
	}

}
