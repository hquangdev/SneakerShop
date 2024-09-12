package SneakerShop.Entity;

public class Contact {
	
	private int id;
	private String content;
	private String Office_Hotline;
	private String Customer_care_Hotline;
	private String Purchase_Hotline;
	private String address;
	private String email;
	

	
	public Contact() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	public String getOffice_Hotline() {
		return Office_Hotline;
	}
	public void setOffice_Hotline(String office_Hotline) {
		Office_Hotline = office_Hotline;
	}
	public String getCustomer_care_Hotline() {
		return Customer_care_Hotline;
	}
	public void setCustomer_care_Hotline(String customer_care_Hotline) {
		Customer_care_Hotline = customer_care_Hotline;
	}
	public String getPurchase_Hotline() {
		return Purchase_Hotline;
	}
	public void setPurchase_Hotline(String purchase_Hotline) {
		Purchase_Hotline = purchase_Hotline;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", content=" + content + ", Office_Hotline=" + Office_Hotline
				+ ", Customer_care_Hotline=" + Customer_care_Hotline + ", Purchase_Hotline=" + Purchase_Hotline
				+ ", address=" + address + ", email=" + email + "]";
	}
	
	
	
	

}
